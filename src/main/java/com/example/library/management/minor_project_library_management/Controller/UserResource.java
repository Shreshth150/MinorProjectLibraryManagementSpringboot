package com.example.library.management.minor_project_library_management.Controller;


import com.example.library.management.minor_project_library_management.DataAcessLayer.User;
import com.example.library.management.minor_project_library_management.DataAcessLayer.UserRepository;
import com.example.library.management.minor_project_library_management.Util.Uservalidator;
import com.example.library.management.minor_project_library_management.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class UserResource {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Autowired
    private UserRepository repository ;

    // Find
    @GetMapping("/users")
    List<User> findAll(){
        return repository.findAll();
    }

    @PostMapping("/users")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    // @ , . ,com
    User newUser(@RequestBody User newUser) throws Exception{
        if (Uservalidator.isValidUser(newUser))
            return repository.save(newUser);
        else throw new Exception();
    }

    // Find a given user
    @GetMapping("/users/{id}")
    User findOne(@PathVariable int id){
        LOGGER.info("/users/{id} called with id " + id);

        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }




}
















