package com.dexlab.gameboard.repository;

import com.dexlab.gameboard.model.GameSheet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IGameSheetRepository extends CrudRepository <GameSheet, Integer>{

}