package com.spring.resources;

import com.spring.Entities.Mensage;
import com.spring.Entities.User;
import com.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    public List<User> users = new ArrayList<>();
    @Autowired
    private UserRepository userRepository;

    int countId = 0;
    @GetMapping
    public ResponseEntity<List<User>> findAll() {

        return ResponseEntity.ok().body(this.users);
    }

    @PostMapping("/new")
    public ResponseEntity<Mensage<User>> create(@RequestParam String name, @RequestParam String email,
            @RequestParam String phone, @RequestParam String password) {

        User user = new User(name, email, phone, password);

        List<User> users = this.userRepository.findByEmail(email);
        if(users.size() == 0) {
            this.userRepository.save(user);
            Mensage<User> mensage = new Mensage<User>("Usuario criado com sucesso", user);
            return ResponseEntity.ok().body(mensage);
        }
        else{
            Mensage<User> mensage = new Mensage<User>("E-mail ja cadastrado", user);
            return ResponseEntity.internalServerError().body(mensage);
        }
    }
}
