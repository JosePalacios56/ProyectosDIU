package com.example.tutoriales.service.converter;

import com.example.tutoriales.model.TutorialVO;
import com.example.tutoriales.model.dto.TutorialDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TutorialConverterToVO implements Converter<TutorialDTO, TutorialVO> {


    @Override
    public TutorialVO convert( TutorialDTO tutorialDTO ) {
        return TutorialVO.builder()
                .titulo( tutorialDTO.getTitulo() )
                .descripcion( tutorialDTO.getDescripcion() )
                .id( tutorialDTO.getId() )
                .publicado(tutorialDTO.isPublicado())
                .build();
    }
}
