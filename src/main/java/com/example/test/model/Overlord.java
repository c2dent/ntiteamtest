package com.example.test.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "overlords")
public class Overlord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    @OneToMany(mappedBy = "overlord", fetch = FetchType.EAGER)
    private List<Planet> planets;

    public Overlord(String name, int age) {
        this.name = name;
        this.age = age;

    }

    public Overlord(Long id, String name, int age, List<Planet> planets) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.planets = planets;
    }

    public Overlord() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Overlord overlord = (Overlord) o;
        return age == overlord.age && Objects.equals(name, overlord.name) && Objects.equals(planets, overlord.planets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, planets);
    }


    @Override
    public String toString() {
        return "Overlord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", planets=" + planets +
                '}';
    }
}
