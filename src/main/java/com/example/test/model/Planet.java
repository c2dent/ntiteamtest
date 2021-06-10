package com.example.test.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "planets")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "overlord_id")
    private Overlord overlord;

    public Planet(String name, Overlord overlord) {
        this.name = name;
        this.overlord = overlord;
    }

    public Planet(Long id, String name, Overlord overlord) {
        this.id = id;
        this.name = name;
        this.overlord = overlord;
    }

    public Planet() {
    }

    public Overlord getOverlord() {
        return overlord;
    }

    public void setOverlord(Overlord overlord) {
        this.overlord = overlord;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(id, planet.id) && Objects.equals(name, planet.name) && Objects.equals(overlord, planet.overlord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, overlord);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ((overlord != null) ? (", overlord_id=" + overlord.getId()) : ("")) +
                '}';
    }
}
