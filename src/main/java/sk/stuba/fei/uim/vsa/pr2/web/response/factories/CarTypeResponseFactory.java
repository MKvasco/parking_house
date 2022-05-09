package sk.stuba.fei.uim.vsa.pr2.web.response.factories;

import sk.stuba.fei.uim.vsa.pr2.domain.CarType;
import sk.stuba.fei.uim.vsa.pr2.web.response.CarTypeDto;

public class CarTypeResponseFactory implements ResponseFactory<CarType, CarTypeDto> {

    @Override
    public CarTypeDto transformToDto(CarType entity) {
        CarTypeDto carType = new CarTypeDto();
        carType.setId(entity.getId());
        carType.setName(entity.getName());
        return carType;
    }

    @Override
    public CarType transformToEntity(CarTypeDto dto) {
        return null;
    }
}
