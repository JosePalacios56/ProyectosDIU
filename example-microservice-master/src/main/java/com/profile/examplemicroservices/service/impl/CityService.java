package com.profile.examplemicroservices.service.impl;

import com.profile.examplemicroservices.model.dto.CityDTO;

import java.util.List;

public interface CityService {

    List < CityDTO > getAll();

    CityDTO create( final CityDTO city );

    CityDTO update( final CityDTO city );

    boolean delete( final String id );


}
