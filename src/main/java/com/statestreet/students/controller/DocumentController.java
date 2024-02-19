package com.statestreet.students.controller;

import com.statestreet.students.model.Address;
import com.statestreet.students.model.User;
import com.statestreet.students.service.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("docs")
public class DocumentController {

    @Autowired
    private DocumentsService service;

    @GetMapping(params = { "name" }, produces = { "application/json" })
    public ResponseEntity<List<String>> findDocumentsByPerson(@RequestParam("name") String name) {
        List<String> docs = service.findDocumentsByPerson(name);
        return new ResponseEntity<>(docs, HttpStatus.OK);
    }

    @GetMapping(params = { "id" }, produces = { "application/json" })
    public ResponseEntity<String> findPersonByDocumentId(@RequestParam("id") Long id) {
        String person = service.findPersonByDocumentId(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
