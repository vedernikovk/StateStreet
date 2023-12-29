package com.statestreet.students.controller;


import com.statestreet.students.model.Post;
import com.statestreet.students.model.Student;
import com.statestreet.students.service.PostService;
import com.statestreet.students.service.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostService service;

    @PostMapping(consumes = { "application/json" })
    public ResponseEntity<Void> createPost(@RequestBody Post post) {
        service.addPost(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(consumes = { "application/json" })
    public ResponseEntity<Void> updatePost(@RequestBody Post post) throws StudentNotFoundException {
        service.updatePost(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deletePost(@PathVariable long id) throws StudentNotFoundException {
        service.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
