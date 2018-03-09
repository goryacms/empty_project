package ru.bellintegrator.practice.register.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Регистрация и авторизация
 */
@Entity(name = "Registers")
public class Register {
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Логин пользователя
     */
    @Basic(optional = false)
    @Column(name = "login")
    private String login;

    /**
     * Пароль пользователя
     */
    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    /**
     * Имя пользователя
     */
    @Column(name = "name")
    private String name;

    /**
     * Конструктор для hibernate
     */
    public Register() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
