package com.dexlab.gameboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import org.springframework.http.HttpStatus;
import com.dexlab.gameboard.model.GameSheet;
import com.dexlab.gameboard.model.Studio;
import com.dexlab.gameboard.model.GameSheet.AgeRestriction;
import com.dexlab.gameboard.service.GameSheetService;
import com.dexlab.gameboard.service.StudioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;

@RestController
@CrossOrigin
public class GameSheetController {

    @Autowired
    GameSheetService gameSheetService;

    @Autowired
    StudioService studioService;

    @GetMapping(path = "/")
    @ResponseBody
    public String rootTest() {

        return "Welcom Tonton youssouf ";
    }

    @GetMapping(path = "/gamesheet/sheets")
    @ResponseBody
    public Iterable<GameSheet> getAllSheet() {

        gameSheetService.getAllSheetIterable().forEach(s ->{
            
            File file = new File(s.getJacketPathRef());
            try {
                s.setJacketPathRef(Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file)));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // System.out.println(s.getStudio().getDirector());
        });

        return gameSheetService.getAllSheetIterable();
    }

    @PostMapping(path = "/gamesheet/create")
    @ResponseBody
    public String createSheet(
        @RequestParam("title") String title, 
        @RequestParam("platform") String platform,
        @RequestParam("age_restriction") AgeRestriction ageRestriction, 
        @RequestParam("jacket") MultipartFile jacket,
        @RequestParam("studio_name") String studioName
    ) {

        GameSheet gameSheet = new GameSheet();
        String pathFolder = "C:\\Users\\dexbe\\Documents\\GameBoard\\backend\\gameboard\\src\\main\\resources\\static\\";
        System.out.println(jacket.getOriginalFilename());

        Studio studio = studioService.findByName(studioName);
        
        try {
            
            jacket.transferTo(new File(pathFolder+jacket.getOriginalFilename()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        gameSheet.setTitle(title);
        gameSheet.setPlatform(platform);
        gameSheet.setAgeRestriction(ageRestriction);
        gameSheet.setJacketPathRef(pathFolder+jacket.getOriginalFilename());
        gameSheet.setStudio(studio);


        gameSheetService.createGameSheet(gameSheet);
        return "sheet registered";
    }

    @PostMapping(path ="/gamesheet/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteSheetById(@PathVariable("id") String id){

        int gameSheetId = Integer.parseInt(id);

        GameSheet gs = gameSheetService.findById(gameSheetId);
        //gameSheetService.resetId(gs.getId());
        
        gameSheetService.deleteSheetById(gs.getId());
        return "ok";
    }



}