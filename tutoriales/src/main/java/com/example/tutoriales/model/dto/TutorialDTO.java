package com.example.tutoriales.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TutorialDTO {

    private String id;
    private String titulo;
    private String descripcion;
    private boolean publicado;
}
