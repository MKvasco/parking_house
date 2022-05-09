package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.Car;
import sk.stuba.fei.uim.vsa.pr2.domain.User;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarDto;
import sk.stuba.fei.uim.vsa.pr2.web.response.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserResponseFactory implements ResponseFactory<User, UserDto>{
    @Override
    public UserDto transformToDto(User entity) {
        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setFirstname(entity.getName());
        userDto.setLastname(entity.getSurname());
        userDto.setEmail(entity.getEmail());
        List<Car> cars = entity.getCars();
        if(!cars.isEmpty()){
            CarResponseFactory carResponseFactory = new CarResponseFactory();
            userDto.setCars(cars.stream().map(carResponseFactory::transformToDto).collect(Collectors.toList()));
        }else{
            userDto.setCars(new ArrayList<>());
        }
        return userDto;
    }

    @Override
    public User transformToEntity(UserDto dto) {
        return null;
    }
}
