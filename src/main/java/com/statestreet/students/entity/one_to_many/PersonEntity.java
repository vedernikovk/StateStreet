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
    private List<IdDocument> idDocuments;}
