package com.dexlab.gameboard.model;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "gameSheet")
public class GameSheet {

    public GameSheet() {

    }

    public static enum AgeRestriction {
        PEGI_3("PEGI_3"), PEGI_7("PEGI_7"), PEGI_12("PEGI_3"), PEGI_16("PEGI_16"), PEGI_18("PEGI_18");

        private String value;

        private AgeRestriction(String value) {
            this.value = value;
        }

        public static AgeRestriction fromValue(String value) {
            for (AgeRestriction pegi : values()) {
                if (pegi.value.equalsIgnoreCase(value)) {
                    return pegi;
                }
            }
            throw new IllegalArgumentException(
                    "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String platform;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_restriction", nullable = true)
    private AgeRestriction AgeRestriction;

    
    @Column(name = "jacketRef", nullable = false)
    private String jacketPathRef;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updatedDate;


    public GameSheet(int id, String title, String platform, AgeRestriction AgeRestriction, String jacketPathRef, Date creationDate, Date updatedDate) {
        this.id = id;
        this.title = title;
        this.platform = platform;
        this.AgeRestriction = AgeRestriction;
        this.jacketPathRef = jacketPathRef;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public AgeRestriction getAgeRestriction() {
        return this.AgeRestriction;
    }

    public void setAgeRestriction(AgeRestriction AgeRestriction) {
        this.AgeRestriction = AgeRestriction;
    }

    public String getJacketPathRef() {
        return this.jacketPathRef;
    }

    public void setJacketPathRef(String jacketPathRef) {
        this.jacketPathRef = jacketPathRef;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdatedDate() {
        return this.updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public GameSheet id(int id) {
        this.id = id;
        return this;
    }

    public GameSheet title(String title) {
        this.title = title;
        return this;
    }

    public GameSheet platform(String platform) {
        this.platform = platform;
        return this;
    }

    public GameSheet AgeRestriction(AgeRestriction AgeRestriction) {
        this.AgeRestriction = AgeRestriction;
        return this;
    }

    public GameSheet jacketPathRef(String jacketPathRef) {
        this.jacketPathRef = jacketPathRef;
        return this;
    }

    public GameSheet creationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public GameSheet updatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GameSheet)) {
            return false;
        }
        GameSheet gameSheet = (GameSheet) o;
        return id == gameSheet.id && Objects.equals(title, gameSheet.title) && Objects.equals(platform, gameSheet.platform) && Objects.equals(AgeRestriction, gameSheet.AgeRestriction) && Objects.equals(jacketPathRef, gameSheet.jacketPathRef) && Objects.equals(creationDate, gameSheet.creationDate) && Objects.equals(updatedDate, gameSheet.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, platform, AgeRestriction, jacketPathRef, creationDate, updatedDate);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", platform='" + getPlatform() + "'" +
            ", AgeRestriction='" + getAgeRestriction() + "'" +
            ", jacketPathRef='" + getJacketPathRef() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            "}";
    }
    

    
    //private List <String> editors; //NDT: create entity editor

    /*public List<String> getEditors() {
        return this.editors;
    }

    public void setEditors(List<String> editors) {
        this.editors = editors;
    }*/

    /*@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date realeaseDate;*/

}