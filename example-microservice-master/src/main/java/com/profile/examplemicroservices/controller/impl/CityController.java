package com.profile.examplemicroservices.controller.impl;

import com.profile.examplemicroservices.controller.CityApi;
import com.profile.examplemicroservices.model.dto.CityDTO;
import com.profile.examplemicroservices.service.impl.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController implements CityApi {

    @Autowired
    private CityService cityService;

    @Override
    public ResponseEntity < List < CityDTO > > getAll() {
        return ResponseEntity.ok( cityService.getAll() );
    }

    @Override
    public ResponseEntity < CityDTO > create( CityDTO cityDTO ) {
        return new ResponseEntity <>( cityService.create( cityDTO ), HttpStatus.CREATED );
    }

    @Override
    public ResponseEntity < CityDTO > update( CityDTO cityDTO ) {
        return new ResponseEntity <>( cityService.update( cityDTO ), HttpStatus.CREATED );
    }

    @Override
    public ResponseEntity < Boolean > delete( String id ) {
        return cityService.delete( id ) 
                ? ResponseEntity.ok( true )
                : new ResponseEntity <>( false, HttpStatus.NOT_FOUND );
    }
}
