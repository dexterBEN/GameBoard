package com.dexlab.gameboard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


import java.util.Objects;
import javax.persistence.Column;

@Entity
@Table(name="studio")
public class Studio {

    // @OneToMany(mappedBy = "studio", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JsonManagedReference
    // private Set<GameSheet> gameSheets;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String director;

    @NotBlank
    @Column(nullable = false)
    private String headQuarter;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @NotBlank
    @Column(nullable = false)
    private String logoPathRef;


    public Studio() {
    }

    public Studio(int id, String name, String director, String headQuarter, String description, String logoPathRef) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.headQuarter = headQuarter;
        this.description = description;
        this.logoPathRef = logoPathRef;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return this.director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getHeadQuarter() {
        return this.headQuarter;
    }

    public void setHeadQuarter(String headQuarter) {
        this.headQuarter = headQuarter;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoPathRef() {
        return this.logoPathRef;
    }

    public void setLogoPathRef(String logoPathRef) {
        this.logoPathRef = logoPathRef;
    }

    public Studio id(int id) {
        this.id = id;
        return this;
    }

    public Studio name(String name) {
        this.name = name;
        return this;
    }

    public Studio director(String director) {
        this.director = director;
        return this;
    }

    public Studio headQuarter(String headQuarter) {
        this.headQuarter = headQuarter;
        return this;
    }

    public Studio description(String description) {
        this.description = description;
        return this;
    }

    public Studio logoPathRef(String logoPathRef) {
        this.logoPathRef = logoPathRef;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Studio)) {
            return false;
        }
        Studio studio = (Studio) o;
        return id == studio.id && Objects.equals(name, studio.name) && Objects.equals(director, studio.director) && Objects.equals(headQuarter, studio.headQuarter) && Objects.equals(description, studio.description) && Objects.equals(logoPathRef, studio.logoPathRef);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, director, headQuarter, description, logoPathRef);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", director='" + getDirector() + "'" +
            ", headQuarter='" + getHeadQuarter() + "'" +
            ", description='" + getDescription() + "'" +
            ", logoPathRef='" + getLogoPathRef() + "'" +
            "}";
    }

}
