package com.dexlab.gameboard.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Blob;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AssetService {

    //private StorageOptions storageOptions;

    public CollectionReference getJacketCollection() {
        Firestore firestore = FirestoreClient.getFirestore();
        try {
            firestore = FirestoreClient.getFirestore();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return firestore.collection("game_jacket");
    }

    public List<QueryDocumentSnapshot> getDocuments() throws InterruptedException, ExecutionException {

        Firestore firestore = FirestoreClient.getFirestore();

        CollectionReference gameJacket = firestore.collection("game_jacket");
        ApiFuture<QuerySnapshot> future = gameJacket.get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        return documents;

    }

    public void createDocument(String documentName, Map<String, Object> documentData) {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = firestore.collection("game_jacket").document(documentName).set(documentData);

        try {
            future.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public DocumentSnapshot getDocumentByRef(String documentRef){
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentSnapshot docSnapShot = null;
        ApiFuture<DocumentSnapshot> future =  firestore.collection("game_jacket").document(documentRef).get();

        try {
           docSnapShot =  future.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return docSnapShot;
    }
}
