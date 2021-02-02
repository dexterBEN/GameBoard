package com.dexlab.gameboard.firebase;

import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class FirebaseConfig {
    
    // @Bean
    // public Firestore getDb(){
    //     Firestore db = null;
    //     try {
    //         db = FirestoreClient.getFirestore();
    //     } catch (Exception e) {
    //         //TODO: handle exception
    //         System.out.println("FBConfig===========>"+e);
    //     }
    //     return db;
    // }
}
