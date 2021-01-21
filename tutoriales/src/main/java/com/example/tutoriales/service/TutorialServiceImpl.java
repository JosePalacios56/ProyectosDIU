package com.example.tutoriales.service;

import com.example.tutoriales.model.TutorialVO;
import com.example.tutoriales.model.dto.TutorialDTO;
import com.example.tutoriales.repository.TutorialRepository;
import com.example.tutoriales.service.converter.TutorialConverterToDTO;
import com.example.tutoriales.service.converter.TutorialConverterToVO;
import com.example.tutoriales.service.impl.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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




    @Override
    public List<TutorialDTO> getAll() {
        return tutorialRepository.findAll()
                .stream()
                .map( tutorialConverterToDTO::convert )
                .collect( Collectors.toList());
    }

    @Override
    public TutorialDTO create(TutorialDTO tutorial) {
        TutorialVO tutorialVO = tutorialConverterToVO.convert( tutorial );
        return tutorialConverterToDTO.convert( tutorialRepository.insert( tutorialVO ) );
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
}
