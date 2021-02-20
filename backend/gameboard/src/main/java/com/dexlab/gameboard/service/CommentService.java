package com.dexlab.gameboard.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

@Service
public class CommentService {

    public List<QueryDocumentSnapshot> getComments() throws InterruptedException, ExecutionException {

        Firestore firestore = FirestoreClient.getFirestore();

        CollectionReference comments = firestore.collection("Comments");
        ApiFuture<QuerySnapshot> future = comments.get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        return documents;

    }

    public List<QueryDocumentSnapshot> findCommentByGameId(int gameId) throws InterruptedException, ExecutionException {

        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshot = null;
        List<DocumentSnapshot> commentSnapShots = null;

        CollectionReference commentsCollection = firestore.collection("Comments");

        List<QueryDocumentSnapshot> commentListFiltered = commentsCollection.whereEqualTo("game_id", gameId).get().get().getDocuments();

        return commentListFiltered;
    }

    public void createComment(Map<String, Object> commentData) {
        Firestore firestore = FirestoreClient.getFirestore();

        ApiFuture<DocumentReference> future = firestore.collection("Comments").add(commentData);

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
}
