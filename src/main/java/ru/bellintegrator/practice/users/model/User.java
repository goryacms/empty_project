package ru.bellintegrator.practice.users.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import ru.bellintegrator.practice.guides.model.DocUser;
import ru.bellintegrator.practice.guides.model.Citizenship;
import ru.bellintegrator.practice.office.model.Office;


@Entity
@Table(name = "Users")
public class User {
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
    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия
     */
    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;

    /**
     * Отчество
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * Должность
     */
    @Column(name = "position")
    private String position;

    /**
     * Телефон
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Возраст
     */
    @Basic(optional = false)
    @Column(name = "age")
    private int age;

    /**
     * Признак идентификации
     */
    @Basic(optional = false)
    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "docs", fetch = FetchType.LAZY)
    private Set<DocUser> docsUsers = new HashSet<DocUser>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_id")
    private Citizenship citizenship;

    /**
     * Конструктор для hibernate
     */
    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<DocUser> getDocsUsers() {
        return docsUsers;
    }

    public void setDocsUsers(Set<DocUser> docsUsers) {
        this.docsUsers = docsUsers;
    }

    public Citizenship getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Citizenship citizenship) {
        this.citizenship = citizenship;
    }


}
