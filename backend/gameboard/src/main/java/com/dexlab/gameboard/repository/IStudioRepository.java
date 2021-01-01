package com.dexlab.gameboard.repository;

import com.dexlab.gameboard.model.Studio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudioRepository extends CrudRepository <Studio, Integer>{

    @Query("SELECT s FROM Studio s WHERE s.name =:studioName")
    public Studio findByStudioName(String studioName);
}
