package com.dexlab.gameboard.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

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

    public void createDocument(String collectionName,String documentName, Map<String, Object> documentData) {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = firestore.collection(collectionName).document(documentName).set(documentData);

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

    public DocumentSnapshot getDocumentByRef(String collectionName, String documentRef){
        Firestore firestore = FirestoreClient.getFirestore();

        DocumentSnapshot docSnapShot = null;
        ApiFuture<DocumentSnapshot> future =  firestore.collection(collectionName).document(documentRef).get();

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

    public List<QueryDocumentSnapshot> findAssetByGameId(int gameId) throws InterruptedException, ExecutionException {

        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshot = null;
        List<DocumentSnapshot> assetSnapShots = null;

        CollectionReference assetCollection = firestore.collection("Assets");

        List<QueryDocumentSnapshot> assetListFiltered = assetCollection.whereEqualTo("game_id", gameId).get().get().getDocuments();

        return assetListFiltered;
    }
}
