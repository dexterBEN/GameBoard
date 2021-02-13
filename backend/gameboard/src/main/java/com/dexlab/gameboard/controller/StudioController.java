package com.dexlab.gameboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dexlab.gameboard.helpers.Helpers;
import com.dexlab.gameboard.model.Studio;
import com.dexlab.gameboard.service.AssetService;
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

    @Autowired
    AssetService assetService;

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
        String pathFolder = "C:\\Users\\dexbe\\Pictures\\";
        String strBase64 = null;
        Map<String, Object> docData = new HashMap<>();

        try {
            
            logo.transferTo(new File(pathFolder+logo.getOriginalFilename()));
            strBase64 = Helpers.fileToBase64String(pathFolder+logo.getOriginalFilename());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        docData.put("base64_value", strBase64);

        assetService.createDocument("studio_logo", name+"_logo", docData);

        studio.setName(name);
        studio.setDirector(director);
        studio.setHeadQuarter(headQuarter);
        studio.setDescription(description);
        studio.setLogoPathRef(name+"_logo");

        studioService.createStudio(studio);

        return "studio created";
    }
}
