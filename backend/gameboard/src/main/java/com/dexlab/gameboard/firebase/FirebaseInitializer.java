package com.dexlab.gameboard.firebase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

@Service
public class FirebaseInitializer {

    @PostConstruct
    public void initialize() {
        // String path = "C:\\Users\\dexbe\\Documents\\GameBoard\\backend\\gameboard\\gameboard-b011e-firebase-adminsdk-7wetp-25d5ff18a4.json";
        
       
        
        // FirebaseOptions options = null;
        // try {
        //     InputStream serviceAccount = this.getClass().getResourceAsStream(path);

        //         options = new FirebaseOptions.Builder()
        //             .setCredentials(GoogleCredentials.getApplicationDefault())
        //             .setDatabaseUrl("https://gameboard-b011e.firebaseio.com")
        //             .build();

                
        //             FirebaseApp.initializeApp(options);
        //     } catch (Exception e) {
        //         e.printStackTrace(); 
        //     }
    }
}
