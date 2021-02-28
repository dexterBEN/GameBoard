package com.dexlab.gameboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.dexlab.gameboard.service.AssetService;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AssetController {
    
    @Autowired
    AssetService assetService;

    @GetMapping(path = "gameboard/asset/{game_id}/assets")
    @ResponseBody
    public Iterable<String> getAssetByGame(@PathVariable ("game_id") String game_id) {

        List<String> assetList = new ArrayList<>();
        int  gameId = Integer.parseInt(game_id);

        System.out.println(gameId);

        try {
            
            for(QueryDocumentSnapshot assetSnapShot: assetService.findAssetByGameId(gameId)){
                assetList.add(assetSnapShot.getString("gameplay1"));
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return assetList;
    }
    
}
