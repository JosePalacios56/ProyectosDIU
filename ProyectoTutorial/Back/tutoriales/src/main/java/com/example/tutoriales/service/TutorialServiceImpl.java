package com.example.tutoriales.service;

import com.example.tutoriales.model.TutorialVO;
import com.example.tutoriales.model.dto.TutorialDTO;
import com.example.tutoriales.repository.TutorialRepository;
import com.example.tutoriales.service.converter.TutorialConverterToDTO;
import com.example.tutoriales.service.converter.TutorialConverterToVO;
import com.example.tutoriales.service.impl.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TutorialServiceImpl  implements TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private TutorialConverterToVO tutorialConverterToVO;

    @Autowired
    private TutorialConverterToDTO tutorialConverterToDTO;


    public final ApplicationEventPublisher eventPublisher;

    public TutorialServiceImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }


    @Override
    public List<TutorialDTO> getAll() {
        return tutorialRepository.findAll()
                .stream()
                .map( tutorialConverterToDTO::convert )
                .collect( Collectors.toList());
    }

    @Override
    public TutorialDTO create(TutorialDTO tutorial) {
        TutorialVO tutorialVO = tutorialConverterToVO.convert(tutorial);

        try {
            TutorialDTO tutorial1=tutorialConverterToDTO.convert(tutorialRepository.insert(tutorialVO));
            publishJobNotifications();
            return tutorial1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return tutorialConverterToDTO.convert(tutorialRepository.insert(tutorialVO));
    }

    @Override
    public TutorialDTO update(TutorialDTO tutorial) {
        TutorialVO tutorialVO = tutorialConverterToVO.convert( tutorial );
        return tutorialConverterToDTO.convert( tutorialRepository.save( tutorialVO ) );
    }

    @Override
    public Boolean delete(String id) {
        try{
            tutorialRepository.deleteById( id );
            return Boolean.TRUE;
        } catch (Exception e){
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean deleteAllTutorials() {
        try{
            tutorialRepository.deleteAll();
            return Boolean.TRUE;
        } catch (Exception e){
            return Boolean.FALSE;
        }
    }

    @Override
    public List<TutorialDTO> findByPublished() {
        return tutorialRepository.findAll()
                .stream()
                .map(tutorialConverterToDTO::convert)
                .filter(tutorial -> tutorial.isPublicado()==true )
                .collect(Collectors.toList());
    }

    @Override
    public TutorialDTO updateTutorial(String id, TutorialDTO tutorial) {
        Optional<TutorialVO> datoVO=tutorialRepository.findById(id);
        TutorialVO tutorialVO = datoVO.get();
        tutorialVO.setTitulo(tutorial.getTitulo());
        tutorialVO.setDescripcion(tutorial.getDescripcion());
        tutorialVO.setPublicado(tutorial.isPublicado());
        return tutorialConverterToDTO.convert(tutorialRepository.save(tutorialVO));

    }
    public void publishJobNotifications() throws InterruptedException {

        Integer jobId = Notification.getNextJobId();
        Notification nStarted = new Notification("Job No. " + jobId + " started.", new Date());
        this.eventPublisher.publishEvent(nStarted);
       /* Thread.sleep(2000);
        Notification nFinished = new Notification("Job No. " + jobId + " finished.", new Date());
        this.eventPublisher.publishEvent(nFinished);*/

    }



}
