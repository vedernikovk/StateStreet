package com.statestreet.students.service;

import com.statestreet.students.entity.one_to_many.IdDocument;
import com.statestreet.students.entity.one_to_many.PersonEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class DocumentsService {

    @PersistenceContext
    private EntityManager em;

    public List<String> findDocumentsByPerson(String name) {

        // Navigating to Single-Valued Relationship Fields (Many-to-one)
        List<IdDocument> docs = (List<IdDocument>) em.createQuery("Select d from IdDocument d Where d.person.name = :name")
                .setParameter("name", name).getResultList();

        return docs.stream().map(IdDocument::getText).collect(toList());
    }

    public String findPersonByDocumentId(Long id) {
        // Navigating to Collection-Valued Relationship Fields (One-to-Many - should do join)
        // You can traverse relationships as long as they are not collections.
        PersonEntity personEntity = (PersonEntity) em.createQuery("Select p from PersonEntity p JOIN p.idDocuments d Where d.id = :id")
                .setParameter("id", id).getSingleResult();

        return personEntity.getName();
    }
}
