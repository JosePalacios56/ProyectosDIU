package com.profile.examplemicroservices.service.converter;

import com.profile.examplemicroservices.model.CityVO;
import com.profile.examplemicroservices.model.dto.CityDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CityConverterToDTO implements Converter< CityVO, CityDTO > {

    @Override
    public CityDTO convert( CityVO cityVO ) {
        return CityDTO.builder()
                .code( cityVO.getCode() )
                .name( cityVO.getName() )
                .id( cityVO.getId() )
                .build();
    }
}
