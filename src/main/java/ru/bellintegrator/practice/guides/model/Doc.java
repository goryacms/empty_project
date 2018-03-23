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






    @OneToMany(mappedBy = "doc", fetch = FetchType.LAZY)
    private Set<DocUser> docUsers = new HashSet<DocUser>();

    public Set<DocUser> getDocUsers() {
        return docUsers;
    }

    public void setDocUsers(Set<DocUser> docUsers) {
        this.docUsers = docUsers;
    }

    public void addDocUser(DocUser docUser) {
        this.docUsers.add(docUser);
    }






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
