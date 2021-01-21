package com.example.tutoriales.service.impl;

import com.example.tutoriales.model.dto.TutorialDTO;

import java.util.List;

public interface TutorialService {

    List<TutorialDTO> getAll();

    TutorialDTO create (final TutorialDTO tutorial);

    TutorialDTO update( final TutorialDTO tutorial);

    Boolean delete ( final String id);

    Boolean deleteAllTutorials();

    List<TutorialDTO> findByPublished();

    TutorialDTO updateTutorial( final String id, final TutorialDTO tutorial);



}
