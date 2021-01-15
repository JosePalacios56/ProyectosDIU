package com.profile.examplemicroservices.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CityDTO {

    private String id;
    private String code;
    private String name;

}
