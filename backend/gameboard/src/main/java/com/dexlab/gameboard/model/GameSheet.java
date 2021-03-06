package com.dexlab.gameboard.model;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "gameSheet")
public class GameSheet {

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

    @ManyToOne
    private Studio studio;

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
    @Column(name = "age_restriction", nullable = false)
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



    public GameSheet() {
    }

    public GameSheet(Studio studio, int id, String title, String platform, AgeRestriction AgeRestriction, String jacketPathRef, Date creationDate, Date updatedDate) {
        this.studio = studio;
        this.id = id;
        this.title = title;
        this.platform = platform;
        this.AgeRestriction = AgeRestriction;
        this.jacketPathRef = jacketPathRef;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
    }

    public Studio getStudio() {
        return this.studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
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

    public GameSheet studio(Studio studio) {
        this.studio = studio;
        return this;
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
        return Objects.equals(studio, gameSheet.studio) && id == gameSheet.id && Objects.equals(title, gameSheet.title) && Objects.equals(platform, gameSheet.platform) && Objects.equals(AgeRestriction, gameSheet.AgeRestriction) && Objects.equals(jacketPathRef, gameSheet.jacketPathRef) && Objects.equals(creationDate, gameSheet.creationDate) && Objects.equals(updatedDate, gameSheet.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studio, id, title, platform, AgeRestriction, jacketPathRef, creationDate, updatedDate);
    }

    @Override
    public String toString() {
        return "{" +
            " studio='" + getStudio() + "'" +
            ", id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", platform='" + getPlatform() + "'" +
            ", AgeRestriction='" + getAgeRestriction() + "'" +
            ", jacketPathRef='" + getJacketPathRef() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            "}";
    }
    

}