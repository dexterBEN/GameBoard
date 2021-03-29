package com.dexlab.gameboard;

import org.apache.http.client.methods.RequestBuilder;
import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.dexlab.gameboard.controller.GameSheetController;
import com.dexlab.gameboard.model.GameSheet;
import com.dexlab.gameboard.service.GameSheetService;

@SpringBootTest
class GameSheetControllerTest extends AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    protected MockMvc mvc;

    @Autowired
    GameSheetController gameSheetController;

    @MockBean
    GameSheetService gameSheetService;

    @Test
    public void assertGameSheetListNotNull(){
        assertTrue(gameSheetController.getAllSheet()!=null);
    }

    @Test
    public void assertGameSheetListNotEmpty(){
        try {
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("gameboard/gamesheets")).andReturn();
            int status = mvcResult.getResponse().getStatus();
            String content = mvcResult.getResponse().getContentAsString();
            GameSheet [] gameSheets = super.mapFromJson(content, GameSheet[].class); 
            assertTrue(gameSheets.length > 0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}