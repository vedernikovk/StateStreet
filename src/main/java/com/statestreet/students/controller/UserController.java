package com.statestreet.students.controller;

import com.statestreet.students.entity.one_to_one_bidirect.UserEntity;
import com.statestreet.students.model.Address;
import com.statestreet.students.model.Student;
import com.statestreet.students.model.User;
import com.statestreet.students.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = { "application/json" })
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}", produces = { "application/json"} )
    public ResponseEntity<User> findUser(@PathVariable long id) {
        User user = userService.findUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(params = { "city" }, produces = { "application/json" })
    public ResponseEntity<User> findUserByCity(@RequestParam("city") String city) {
        User user = userService.findUserByCity(city);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(params = { "userName" }, produces = { "application/json" })
    public ResponseEntity<Address> findCityByUserName(@RequestParam("userName") String userName) {
        Address address = userService.findCityByUserName(userName);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PutMapping(value = "{id}", produces = { "application/json"} )
    public ResponseEntity<Void> updateUser(@RequestBody User user, @PathVariable long id) {
        userService.updateUser2(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
