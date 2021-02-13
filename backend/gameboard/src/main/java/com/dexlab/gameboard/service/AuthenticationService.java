package com.dexlab.gameboard.service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import com.dexlab.gameboard.model.User;
import com.dexlab.gameboard.model.dto.AuthentUser;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    
    UserService userService;

    public AuthentUser login(String email, String password){
        
        userService = new UserService();

        User user = new User();
        AuthentUser authentUser = new AuthentUser();

        DocumentSnapshot userSnapshot = userService.findByEmailAndPassword(email, password);

        if(userSnapshot != null){
            user.setEmail(userSnapshot.getString("mail"));
            user.setName(userSnapshot.getString("name"));
            user.setPassWord(userSnapshot.getString("password"));

            authentUser.setUser(user);
            authentUser.setToken(UUID.randomUUID().toString().replace("-", ""));
            return authentUser;
        }

        return null;
    }

    public void createAccount(Map<String, Object> userDocument){

        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> future = firestore.collection("Users").add(userDocument);
    }
}
