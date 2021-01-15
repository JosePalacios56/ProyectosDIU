package com.profile.examplemicroservices.service;

import com.profile.examplemicroservices.model.CityVO;
import com.profile.examplemicroservices.model.dto.CityDTO;
import com.profile.examplemicroservices.repository.CityRepository;
import com.profile.examplemicroservices.service.converter.CityConverterToDTO;
import com.profile.examplemicroservices.service.converter.CityConverterToVO;
import com.profile.examplemicroservices.service.impl.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityConverterToDTO cityConverterToDTO;

    @Autowired
    private CityConverterToVO cityConverterToVO;


    @Override
    public List < CityDTO > getAll() {
        return cityRepository.findAll()
                .stream()
                .map( cityConverterToDTO::convert )
                .collect( Collectors.toList());
    }

    @Override
    public CityDTO create( CityDTO city ) {
        CityVO cityVO = cityConverterToVO.convert( city );
        return cityConverterToDTO.convert( cityRepository.insert( cityVO ) );
    }

    @Override
    public CityDTO update( CityDTO city ) {
        CityVO cityVO = cityConverterToVO.convert( city );
        return cityConverterToDTO.convert( cityRepository.save( cityVO ) );
    }

    @Override
    public boolean delete( String id ) {
        try{
            cityRepository.deleteById( id );
            return Boolean.TRUE;
        } catch (Exception e){
            return Boolean.FALSE;
        }
    }
}
