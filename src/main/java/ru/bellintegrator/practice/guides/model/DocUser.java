package ru.bellintegrator.practice.guides.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ru.bellintegrator.practice.users.model.User;
/**
 * Этот класс предназначен для доступа к полям doc_code, doc_date, doc_number при реализации связи М-М
 */
@Entity
@Table(name = "Docs_Users")
public class DocUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_code")
    private Doc doc;


    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    @Column(name = "doc_number")
    private Long docNumber;


    public DocUser() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User getUsers() {
        return user;
    }

    public void setUsers(User users) {
        this.user = users;
    }

    public Doc getDocs() {
        return doc;
    }

    public void setDocs(Doc docs) {
        this.doc = docs;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Long getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(Long docNumber) {
        this.docNumber = docNumber;
    }


}
