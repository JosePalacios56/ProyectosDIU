package com.example.tutoriales.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TutorialDTO {

    private String id;
    private String titulo;
    private String descripcion;
    private boolean publicado;
}
