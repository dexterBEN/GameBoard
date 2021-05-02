package com.dexlab.gameboard.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.dexlab.gameboard.helpers.Helpers;
import com.dexlab.gameboard.model.GameSheet;
import com.dexlab.gameboard.model.Studio;
import com.dexlab.gameboard.model.GameSheet.AgeRestriction;
import com.dexlab.gameboard.service.AssetService;
import com.dexlab.gameboard.service.GameSheetService;
import com.dexlab.gameboard.service.StudioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class GameSheetController {

    @Autowired
    GameSheetService gameSheetService;

    @Autowired
    StudioService studioService;

    @Autowired
    AssetService assetService;

    @GetMapping(path = "/")
    @ResponseBody
    public String rootTest() {

        return "Welcom Tonton youssouf ";
    }

    @GetMapping(path = "/gameboard/gamesheets")
    @ResponseBody
    public Iterable<GameSheet> getAllSheet() {

        List <String> jacketRefs = new ArrayList<>();

        int i = 0;

        gameSheetService.getAllSheetIterable().forEach(gs ->{
            gs.setJacketPathRef(assetService.getDocumentByRef("game_jacket", gs.getJacketPathRef()).getString("hexa_value"));
            jacketRefs.add(gs.getStudio().getLogoPathRef());
        });

        for(GameSheet gs: gameSheetService.getAllSheetIterable()){
            gs.getStudio().setLogoPathRef(assetService.getDocumentByRef("studio_logo", jacketRefs.get(i)).getString("base64_value"));
            i++;
        }

        return gameSheetService.getAllSheetIterable();
    }

    @PostMapping(path = "gameboard/gamesheet")
    @ResponseBody
    public String createSheet(
        @RequestParam("title") String title, 
        @RequestParam("platform") String platform,
        @RequestParam("age_restriction") AgeRestriction ageRestriction, 
        @RequestParam("jacket") MultipartFile jacket,
        @RequestParam("studio_name") String studioName
    ) {

        GameSheet gameSheet = new GameSheet();
        String strBase64 = null;
        Map<String, Object> docData = new HashMap<>();
        Studio studio = studioService.findByName(studioName);

        try {

            jacket.transferTo(new File("C:\\Users\\dexbe\\Pictures\\"+jacket.getOriginalFilename()));
            strBase64 = Helpers.fileToBase64String("C:\\Users\\dexbe\\Pictures\\"+jacket.getOriginalFilename());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        docData.put("hexa_value", strBase64);

        assetService.createDocument("game_jacket",title+"_jacket", docData);

        gameSheet.setTitle(title);
        gameSheet.setPlatform(platform);
        gameSheet.setAgeRestriction(ageRestriction);
        gameSheet.setJacketPathRef(title+"_jacket");
        gameSheet.setStudio(studio);


        gameSheetService.createGameSheet(gameSheet);
        return "sheet registered";
    }

    @DeleteMapping(path ="gameboard/gamesheet/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteSheetById(@PathVariable("id") String id){

        int gameSheetId = Integer.parseInt(id);

        GameSheet gs = gameSheetService.findById(gameSheetId);
        //gameSheetService.resetId(gs.getId());
        
        gameSheetService.deleteSheetById(gs.getId());
        return "ok";
    }



}