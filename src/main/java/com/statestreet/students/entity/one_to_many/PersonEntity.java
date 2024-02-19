package com.statestreet.students.entity.one_to_many;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="PERSONS")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<IdDocument> idDocuments;

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

    public List<IdDocument> getIdDocuments() {
        return idDocuments;
    }

    public void setIdDocuments(List<IdDocument> idDocuments) {
        this.idDocuments = idDocuments;
    }
}
