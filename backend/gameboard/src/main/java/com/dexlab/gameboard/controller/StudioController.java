package com.dexlab.gameboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dexlab.gameboard.model.Studio;
import com.dexlab.gameboard.service.StudioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class StudioController {
    
    @Autowired
    StudioService studioService;

    @GetMapping(path = "/gamesheet/studios")
    @ResponseBody
    public Iterable<Studio> getAllStudio() {

        List<String> sheets = new ArrayList<>();

        return studioService.getAllStudio();
    }

    @PostMapping(path="/studio/create")
    @ResponseBody
    public String createStudio(
        @RequestParam("name") String name, 
        @RequestParam("director") String director,
        @RequestParam("headQuarter") String headQuarter,
        @RequestParam("description") String description,
        @RequestParam("logoPathRef") MultipartFile logo
    ){

        Studio studio = new Studio();
        String pathFolder = "C:\\Users\\dexbe\\Documents\\GameBoard\\backend\\gameboard\\src\\main\\resources\\static\\";

        try {
            
            logo.transferTo(new File(pathFolder+logo.getOriginalFilename()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        studio.setName(name);
        studio.setDirector(director);
        studio.setHeadQuarter(headQuarter);
        studio.setDescription(description);
        studio.setLogoPathRef(pathFolder+logo.getOriginalFilename());

        studioService.createStudio(studio);

        return "studio created";
    }
}
