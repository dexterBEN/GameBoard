package com.dexlab.gameboard;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GameboardApplication {

	static String FB_BASE_URL="https://gameboard-b011e.firebaseio.com"; 

	public static void main(String[] args) {
		SpringApplication.run(GameboardApplication.class, args);

		try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\dexbe\\Documents\\GameBoard\\backend\\gameboard\\gameboard-b011e-firebase-adminsdk-7wetp-25d5ff18a4.json")))
                    .setDatabaseUrl(FB_BASE_URL)
                    .build();
            if(FirebaseApp.getApps().isEmpty()) { 
                FirebaseApp.initializeApp(options);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}