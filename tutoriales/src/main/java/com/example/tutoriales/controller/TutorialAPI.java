package com.example.tutoriales.controller;

import com.example.tutoriales.controller.constant.EndPointUris;
import com.example.tutoriales.model.dto.TutorialDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping( EndPointUris.TUTORIALES )
public interface TutorialAPI {

    @GetMapping
    ResponseEntity<List<TutorialDTO>> getAll();

    @PostMapping
    ResponseEntity< TutorialDTO > create( @RequestBody final TutorialDTO tutorialDTO );

    @PutMapping
    ResponseEntity< TutorialDTO > update( @RequestBody final TutorialDTO tutorialDTO );

    @DeleteMapping( EndPointUris.ID )
    ResponseEntity< Boolean > delete( @PathVariable final String id );

    @DeleteMapping
    ResponseEntity<Boolean> deleteAllTutorials();

    @GetMapping(EndPointUris.PUBLICADO)
    ResponseEntity<List<TutorialDTO>> findByPublished();

    @GetMapping(EndPointUris.ID)
    ResponseEntity<Optional<TutorialDTO>> getTutorialById(@PathVariable final String id);

    @PutMapping(EndPointUris.ID)
    ResponseEntity< TutorialDTO> updateTutorial(@PathVariable final String id,@RequestBody final TutorialDTO tutorialDTO);


}
