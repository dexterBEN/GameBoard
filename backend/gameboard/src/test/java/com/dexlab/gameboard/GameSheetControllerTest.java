package com.dexlab.gameboard;

//import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.dexlab.gameboard.controller.GameSheetController;
import com.dexlab.gameboard.model.GameSheet;
import com.dexlab.gameboard.service.GameSheetService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


class GameSheetControllerTest extends AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private Collection<GameSheet> gameList = new ArrayList<>();

    // @Test
    // public void assertGameSheetListNotNull(){
    //     assertTrue(gameSheetController.getAllSheet()!=null);
    // }

    @Test
    public void assertGameSheetListNotEmpty()throws Exception{

        String uri = "/gameboard/gamesheets";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
        .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();

        gameList =  objectMapper.readValue(content, new TypeReference<Collection<GameSheet>>(){});
        //GameSheet[] gameArray =  super.mapFromJson(content, GameSheet[].class);

        //List<GameSheet> gameList = Arrays.asList(gameArray);
        assertTrue(!gameList.isEmpty());
    
    }
}