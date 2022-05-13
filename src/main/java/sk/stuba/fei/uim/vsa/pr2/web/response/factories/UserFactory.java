package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.Car;
import sk.stuba.fei.uim.vsa.pr2.domain.Reservation;
import sk.stuba.fei.uim.vsa.pr2.domain.User;
import sk.stuba.fei.uim.vsa.pr2.web.controllers.service.UserService;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarWithUsersDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.ReservationDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.UserDto;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserFactory implements Factory<User, UserDto> {
    @Override
    public UserDto transformToDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setFirstname(entity.getName());
        userDto.setLastname(entity.getSurname());
        userDto.setEmail(entity.getEmail());
        List<Car> cars = entity.getCars();
        if(!cars.isEmpty()){
            CarWithUsersFactory carWithUsersFactory = new CarWithUsersFactory();
            userDto.setCars(cars.stream().map(carWithUsersFactory::transformToDto).collect(Collectors.toList()));
        }else{
            userDto.setCars(new ArrayList<>());
        }
        return userDto;
    }

    @Override
    public User transformToEntity(UserDto dto) {
       String firstname = dto.getFirstname();
       String lastname = dto.getLastname();
       String email = dto.getEmail();

       User user = new UserService().createUser(firstname,lastname,email);
       if(user == null) return null;

       List<CarWithUsersDto> carsDto = dto.getCars();
       if(carsDto == null) carsDto = new ArrayList<>();
       if(!carsDto.isEmpty()){
           CarWithUsersFactory carWithUsersFactory = new CarWithUsersFactory();
           user.setCars(carsDto.stream().map(carWithUsersFactory::transformToEntity).collect(Collectors.toList()));
           for(Car car : user.getCars()){
        System.out.println(car);
               if(car == null) return null;
               for(CarWithUsersDto tmpCar : dto.getCars()){
                   if(!Objects.equals(car.getUser().getName(), tmpCar.getOwner().getFirstname())) return null;
                   if(!Objects.equals(car.getUser().getSurname(), tmpCar.getOwner().getLastname())) return null;
               }
           }
       }
       return user;
    }
}
