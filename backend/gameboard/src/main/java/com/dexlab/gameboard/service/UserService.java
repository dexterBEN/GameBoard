package com.dexlab.gameboard.service;

import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public DocumentSnapshot findByEmail(String email) {
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentSnapshot docSnapShot = null;
        ApiFuture<QuerySnapshot> querySnapshot = null;

        CollectionReference users = firestore.collection("Users");
        Query query = users.whereEqualTo("email", email);

        querySnapshot = query.get();

        try {
            docSnapShot = querySnapshot.get().getDocuments().get(0);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return docSnapShot;
    }

    public DocumentSnapshot findByPassword(String password) {
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentSnapshot docSnapShot = null;
        ApiFuture<QuerySnapshot> querySnapshot = null;

        CollectionReference users = firestore.collection("Users");
        Query query = users.whereEqualTo("password", password);

        querySnapshot = query.get();

        try {
            docSnapShot = querySnapshot.get().getDocuments().get(0);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return docSnapShot;
    }

    public DocumentSnapshot findByEmailAndPassword(String email, String password) {

        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshot = null;
        DocumentSnapshot userSnapShot = null;

        CollectionReference users = firestore.collection("Users");
        Query query = users.whereEqualTo("mail", email).whereEqualTo("password", password);

        try {
            querySnapshot = query.get();
            userSnapShot = querySnapshot.get().getDocuments().get(0);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return userSnapShot;
    }
}
