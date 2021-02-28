package com.dexlab.gameboard.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.dexlab.gameboard.model.Comment;
import com.dexlab.gameboard.service.CommentService;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping(path = "gameboard/comment/create")
    @ResponseBody
    String createComment(@RequestParam("author") String author, @RequestParam("content") String content,
            @RequestParam("game_id") int gameId) {

        Map<String, Object> docData = new HashMap<>();

        docData.put("author", author);
        docData.put("content", content);
        docData.put("game_id", gameId);

        commentService.createComment(docData);

        return "ok";
    }

    @GetMapping(path = "gameboard/gamesheet/{game_id}/comments")
    @ResponseBody
    public Iterable<Comment> getCommentByGame(@PathVariable ("game_id") String game_id) {

        List<Comment> comments = new ArrayList<>();
        Comment comment = null;
        int  gameId = Integer.parseInt(game_id);

        System.out.println(gameId);

        try {
            
            for(QueryDocumentSnapshot commentSnapShot: commentService.findCommentByGameId(gameId)){
                comment = new Comment();

                comment.setAuthor(commentSnapShot.getString("author"));
                comment.setContent(commentSnapShot.getString("content"));

                comment.setGameID(gameId);
                comments.add(comment);
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

        return comments;
    }

}
