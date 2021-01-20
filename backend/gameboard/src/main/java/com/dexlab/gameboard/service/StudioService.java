package com.dexlab.gameboard.service;

import com.dexlab.gameboard.model.Studio;
import com.dexlab.gameboard.repository.IStudioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudioService {
    
    @Autowired
    IStudioRepository studioRepository;

    public void createStudio(Studio studio){
        studioRepository.save(studio);
    }

    public Studio findByName(String studioName){
        return studioRepository.findByStudioName(studioName);
    }

    public Iterable<Studio> getAllStudio(){
        return studioRepository.findAll();
    }
}
