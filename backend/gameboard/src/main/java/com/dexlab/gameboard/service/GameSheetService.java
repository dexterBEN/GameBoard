package com.dexlab.gameboard.service;

import com.dexlab.gameboard.model.GameSheet;
import com.dexlab.gameboard.repository.IGameSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameSheetService{

    @Autowired
    IGameSheetRepository gameSheetRepository;

    public void createGameSheet(GameSheet gameSheet){
        gameSheetRepository.save(gameSheet);
    }

    public Iterable<GameSheet> getAllSheetIterable(){
        return gameSheetRepository.findAll();
    }

    public GameSheet findById(int id){
        return gameSheetRepository.getOne(id);
    }

    public void deleteSheetById(int id){
        gameSheetRepository.deleteById(id);
    }

    // public void resetId(int id){
    //     gameSheetRepository.resetId(id);
    // }

}