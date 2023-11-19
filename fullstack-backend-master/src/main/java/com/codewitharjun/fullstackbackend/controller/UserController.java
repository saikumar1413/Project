package com.codewitharjun.fullstackbackend.controller;

import com.codewitharjun.fullstackbackend.exception.UserNotFoundException;
import com.codewitharjun.fullstackbackend.model.User;
import com.codewitharjun.fullstackbackend.repository.UserRepository;
import com.codewitharjun.fullstackbackend.service.LoginMessage;
import com.codewitharjun.fullstackbackend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* Created by sai */
@RestController
@CrossOrigin("http://localhost:3000")
//@RequestMapping("register")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService  serv;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }
    
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody User user)
    {
        LoginMessage loginResponse = serv.loginemployee(user);
        return ResponseEntity.ok(loginResponse);
    }
    
    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                	user.setFullname(newUser.getFullname());
                	user.setEmail(newUser.getEmail());
                	user.setMobile(newUser.getMobile());
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setConfirmpassword(newUser.getConfirmpassword());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return  "User with id "+id+" has been deleted success.";
    }



}
