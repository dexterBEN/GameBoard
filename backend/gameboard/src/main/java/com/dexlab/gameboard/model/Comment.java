package com.dexlab.gameboard.model;

import java.util.Objects;

public class Comment {
    
    private String author;
    private String content;
    private int gameID;



    public Comment() {
    }

    public Comment(String author, String content, int gameID) {
        this.author = author;
        this.content = content;
        this.gameID = gameID;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getGameID() {
        return this.gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public Comment author(String author) {
        setAuthor(author);
        return this;
    }

    public Comment content(String content) {
        setContent(content);
        return this;
    }

    public Comment gameID(int gameID) {
        setGameID(gameID);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Comment)) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(author, comment.author) && Objects.equals(content, comment.content) && gameID == comment.gameID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, content, gameID);
    }

    @Override
    public String toString() {
        return "{" +
            " author='" + getAuthor() + "'" +
            ", content='" + getContent() + "'" +
            ", gameID='" + getGameID() + "'" +
            "}";
    }
    

}
