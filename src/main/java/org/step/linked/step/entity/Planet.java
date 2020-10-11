package org.step.linked.step.entity;

import javax.persistence.*;

@Entity
// do not write user
@Table(name = "planets")
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "weight")
    private Long weight;

    @Column(name = "radius")
    private Long radius;

    public Planet() {
    }

    public Planet(Long id, String name, Long weight, Long radius) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.radius = radius;
    }

    public static Planet.PlanetBuilder builder() {
        return new Planet.PlanetBuilder();
    }

    public Long getId() {
        return id;
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

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getRadius() {
        return radius;
    }

    public void setRadius(Long radius) {
        this.radius = radius;
    }
    public static class PlanetBuilder {
        private Long id;
        private String name;
        private Long weight;
        private Long radius;

        private PlanetBuilder() {
        }

        public Planet.PlanetBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Planet.PlanetBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Planet.PlanetBuilder weight(Long weight) {
            this.weight = weight;
            return this;
        }

        public Planet.PlanetBuilder radius(Long radius) {
            this.radius = radius;
            return this;
        }

        public Planet build() {
            return new Planet(id, name, weight, radius);
        }
    }
}
