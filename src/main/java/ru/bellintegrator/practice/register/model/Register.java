package ru.bellintegrator.practice.register.model;

import javax.persistence.*;

/**
 * Регистрация и авторизация
 */
@Entity(name = "Registers")
public class Register {
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
     * Электронная почта
     */
    @Column(name = "email")
    private String email;


    @Column(name = "code")
    private String code;

    @Column(name = "is_active")
    private boolean isActive;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
