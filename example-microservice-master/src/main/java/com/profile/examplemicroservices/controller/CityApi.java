package com.profile.examplemicroservices.controller;

import com.profile.examplemicroservices.controller.constant.EndPointUris;
import com.profile.examplemicroservices.model.dto.CityDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping( EndPointUris.API + EndPointUris.V1 + EndPointUris.CITIES )
public interface CityApi {

    @GetMapping
    ResponseEntity< List < CityDTO > > getAll();

    @PostMapping
    ResponseEntity< CityDTO > create( @RequestBody final CityDTO cityDTO );

    @PutMapping
    ResponseEntity< CityDTO > update( @RequestBody final CityDTO cityDTO );

    @DeleteMapping( EndPointUris.ID )
    ResponseEntity< Boolean > delete( @PathVariable final String id );

}
