package com.dexlab.gameboard.repository;

import com.dexlab.gameboard.model.GameSheet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IGameSheetRepository extends JpaRepository <GameSheet, Integer>{

}