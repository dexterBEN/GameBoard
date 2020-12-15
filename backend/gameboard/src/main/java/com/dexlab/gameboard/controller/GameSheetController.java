package com.dexlab.gameboard.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.awt.Image;

import com.dexlab.gameboard.model.GameSheet;
import com.dexlab.gameboard.model.GameSheet.AgeRestriction;
import com.dexlab.gameboard.service.GameSheetService;
import com.sun.tools.javac.Main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

@RestController
@CrossOrigin
public class GameSheetController {

    @Autowired
    GameSheetService gameSheetService;

    @GetMapping(path = "/")
    @ResponseBody
    public String rootTest() {

        return "Welcom Tonton youssouf ";
    }

    @GetMapping(path = "/gamesheet/sheets")
    @ResponseBody
    public Iterable<GameSheet> getAllSheet() {

        List<String> sheets = new ArrayList<>();
        gameSheetService.getAllSheetIterable().forEach(s ->{
            
            File file = new File(s.getJacketPathRef());

            try {
               s.setJacketPathRef(Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file)));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        });

        return gameSheetService.getAllSheetIterable();
    }

    @PostMapping(path = "/gamesheet/create")
    @ResponseBody
    public String createSheet(
        @RequestParam("title") String title, @RequestParam("platform") String platform,
        @RequestParam("age_restriction") AgeRestriction ageRestriction, @RequestParam("jacket") MultipartFile jacket
    ) {

        GameSheet gameSheet = new GameSheet();
        String pathFolder = "C:\\Users\\dexbe\\Documents\\GameBoard\\backend\\gameboard\\src\\main\\resources\\static\\";
        System.out.println(jacket.getOriginalFilename());
        

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


        gameSheetService.createGameSheet(gameSheet);

        

        // try {
            
        //     blob = new SerialBlob(jacket.getBytes());
        //     gameSheet.setJacketFile(blob);
    
        //     gameSheetService.createGameSheet(gameSheet);
        // } catch (Exception e) {
        //     //TODO: handle exception
        //     System.out.println(e);
        // }

        return "sheet registered";
    }



}