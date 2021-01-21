package com.example.tutoriales.service.converter;

import com.example.tutoriales.model.TutorialVO;
import com.example.tutoriales.model.dto.TutorialDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TutorialConverterToDTO implements Converter<TutorialVO, TutorialDTO> {


    @Override
    public TutorialDTO convert( TutorialVO tutorialVO ) {
        return TutorialDTO.builder()
                .titulo( tutorialVO.getTitulo() )
                .descripcion(tutorialVO.getDescripcion() )
                .publicado(tutorialVO.isPublicado())
                .id( tutorialVO.getId() )
                .build();
    }


}
