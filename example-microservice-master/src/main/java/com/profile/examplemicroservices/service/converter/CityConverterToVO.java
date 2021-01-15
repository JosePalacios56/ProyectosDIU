package com.profile.examplemicroservices.service.converter;

import com.profile.examplemicroservices.model.CityVO;
import com.profile.examplemicroservices.model.dto.CityDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CityConverterToVO implements Converter< CityDTO, CityVO > {

    @Override
    public CityVO convert( CityDTO cityDTO ) {
        return CityVO.builder()
                .code( cityDTO.getCode() )
                .name( cityDTO.getName() )
                .id( cityDTO.getId() )
                .build();
    }
}
