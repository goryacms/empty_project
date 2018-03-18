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
    @Column(name = "is_identified")
    private boolean isIdentified;







    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
               cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<DocUser> docUsers = new HashSet<DocUser>();








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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{id:");
        builder.append(getId());
        builder.append(";firstName:");
        builder.append(getFirstName());
        builder.append(";lastName:");
        builder.append(getLastName());
        builder.append(";middleName:");
        builder.append(getMiddleName());
        builder.append(";position:");
        builder.append(getPosition());
        builder.append(";phone:");
        builder.append(getPhone());
        builder.append(";age:");
        builder.append(getAge());
        builder.append(";isIdentified:");
        builder.append(isIdentified());
        builder.append(";office:");
        builder.append(getOffice());
        builder.append(";citizenship:");
        builder.append(getCitizenship());
        builder.append("}");

        return builder.toString();
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

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }





    public void addDocUser(DocUser docUser) {
        this.docUsers.add(docUser);
    }

    public Set<DocUser> getDocUsers() {
        return docUsers;
    }

    public void setDocUsers(Set<DocUser> docUsers) {
        this.docUsers = docUsers;
    }






    public Citizenship getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Citizenship citizenship) {
        this.citizenship = citizenship;
    }


}
