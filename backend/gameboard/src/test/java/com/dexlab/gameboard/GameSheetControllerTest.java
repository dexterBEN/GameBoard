package com.dexlab.gameboard;

//import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import com.dexlab.gameboard.model.GameSheet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


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

    @Test
    public void assertWrongEndPointReturn405() throws Exception{
        String randomEndpoint = "/gameboard/gamesheet";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(randomEndpoint)
        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertEquals(mvcResult.getResponse().getStatus(), 405);
    }
}