package ru.bellintegrator.practice.organization.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Организация
 */
@Entity
@Table(name = "Organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Иия
     */
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    /**
     * Полное иия
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * ИНН
     */
    @Basic(optional = false)
    @Column(name = "inn")
    private Long inn;

    /**
     * КПП
     */
    @Basic(optional = false)
    @Column(name = "kpp")
    private Long kpp;

    /**
     * Адрес
     */
    @Column(name = "address")
    private String address;

    /**
     * Телефон
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Признак активации
     */
    @Basic(optional = false)
    @Column(name = "is_active")
    private boolean isActive;


    /**
     * Конструктор для hibernate
     */
    public Organization() {

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{id:");
        builder.append(getId());
        builder.append(";name:");
        builder.append(getName());
        builder.append(";fullName:");
        builder.append(getFullName());
        builder.append(";inn:");
        builder.append(getInn());
        builder.append(";kpp:");
        builder.append(getKpp());
        builder.append(";address:");
        builder.append(getAddress());
        builder.append(";phone:");
        builder.append(getPhone());
        builder.append(";isActive:");
        builder.append(getActive());
        builder.append("}");

        return builder.toString();
    }


    /**
     * Геттеры/сеттеры
     */
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public Long getKpp() {
        return kpp;
    }

    public void setKpp(Long kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


}