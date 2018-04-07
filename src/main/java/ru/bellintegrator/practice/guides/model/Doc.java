package ru.bellintegrator.practice.guides.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Виды документов
 */
@Entity
@Table(name = "Docs")
public class Doc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code")
    private Integer id;

    @Version
    private Integer version;

    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Doc() {
    }
}
