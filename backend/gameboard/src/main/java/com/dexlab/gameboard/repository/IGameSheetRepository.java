package com.dexlab.gameboard.repository;

import com.dexlab.gameboard.model.GameSheet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameSheetRepository extends JpaRepository <GameSheet, Integer>{

   /* @Query("UPDATE hibernate_sequence SET next_val =:id")
    public void resetId(int id);*/
}