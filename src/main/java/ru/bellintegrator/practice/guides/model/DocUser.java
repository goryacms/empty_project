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
 * Этото класс предназначен для доступа к полям doc_code, doc_date, doc_number при реализации связи М-М
 */
@Entity
@Table(name = "Docs")
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
    private User users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code")
    private Doc docs;

    @Column(name = "doc_code")
    private Integer docCode;

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
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Doc getDocs() {
        return docs;
    }

    public void setDocs(Doc docs) {
        this.docs = docs;
    }

    public Integer getDocCode() {
        return docCode;
    }

    public void setDocCode(Integer docCode) {
        this.docCode = docCode;
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
