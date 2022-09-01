package com.statestreet.students.entity.one_to_many;

import javax.persistence.*;

@Entity
@Table(name="ID_DOCUMENTS")
public class IdDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String text;

    @ManyToOne
    private PersonEntity person;
}
