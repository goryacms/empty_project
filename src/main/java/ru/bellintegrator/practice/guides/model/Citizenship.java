package ru.bellintegrator.practice.guides.model;

import javax.persistence.*;

/**
 * Гражданство
 */
@Entity
@Table(name = "Citizenship")
public class Citizenship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code")
    private Long code;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;


    /**
     * Наименование
     */
    @Basic(optional = false)
    @Column(name = "name")
    private String name;





    public Citizenship() {
    }


    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
