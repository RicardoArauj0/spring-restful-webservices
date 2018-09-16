package com.springboot.restfullwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDAOService userService;

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public Resource<User> getUser(@PathVariable int id) {
        User user = userService.findOne(id);

        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel(("all-users")));
        return resource;
    }

    @PostMapping(path = "/users")
    public ResponseEntity saveUser(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable int id) {
        User user = userService.deleteById(id );

        if(user == null) {
            throw new UserNotFoundException("id" + id);
        }
    }
}

