package com.example.tutoriales.controller.impl;

import com.example.tutoriales.controller.TutorialAPI;
import com.example.tutoriales.service.Notification;
import com.example.tutoriales.model.TutorialVO;
import com.example.tutoriales.model.dto.TutorialDTO;
import com.example.tutoriales.repository.TutorialRepository;
import com.example.tutoriales.service.impl.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@CrossOrigin
public class TutorialController implements TutorialAPI {

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @Autowired
    private TutorialService tutorialService;

    @Autowired
    private TutorialRepository tutorialRepository;

    @Override
    public ResponseEntity<List<TutorialDTO>> getAll() {
       return  ResponseEntity.ok( tutorialService.getAll());
    }

    @Override
    public ResponseEntity<TutorialDTO> create(TutorialDTO tutorialDTO) {
        return new ResponseEntity<>(tutorialService.create(tutorialDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TutorialDTO> update(TutorialDTO tutorialDTO) {
        return new ResponseEntity<>(tutorialService.update(tutorialDTO),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Boolean> delete(String id) {
        return tutorialService.delete( id )
                ? ResponseEntity.ok( true )
                : new ResponseEntity <>( false, HttpStatus.NOT_FOUND );
    }

    @Override
    public ResponseEntity<Boolean> deleteAllTutorials() {
        return tutorialService.deleteAllTutorials()
                ? ResponseEntity.ok( true )
                : new ResponseEntity <>( false, HttpStatus.NOT_FOUND );
    }

    @Override
    public ResponseEntity<List<TutorialDTO>> findByPublished() {
        return ResponseEntity.ok(tutorialService.findByPublished());
    }

    @Override
    public ResponseEntity<Optional<TutorialDTO>> getTutorialById(String id) {
        Optional<TutorialVO> dataVO= tutorialRepository.findById(id);

        if(dataVO.isPresent())
        return new ResponseEntity(dataVO.get(),HttpStatus.OK);
        return null;
    }

    @Override
    public ResponseEntity<TutorialDTO> updateTutorial(String id, TutorialDTO tutorialDTO) {
        return new ResponseEntity<>(tutorialService.updateTutorial(id,tutorialDTO),HttpStatus.CREATED);

    }


    public SseEmitter getNewNotification() {
        SseEmitter emitter = new SseEmitter();
        this.emitters.add(emitter);
        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> {
            emitter.complete();
            this.emitters.remove(emitter);
        });
        return emitter;
    }
    @EventListener
    public void onNotification(Notification notification) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {
                emitter.send(notification);
            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        });
        this.emitters.remove(deadEmitters);

    }



}
