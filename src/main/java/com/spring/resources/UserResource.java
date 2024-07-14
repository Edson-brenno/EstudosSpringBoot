package com.spring.resources;

import com.spring.Entities.Mensage;
import com.spring.Entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    public List<User> users = new ArrayList<>();
    int countId = 0;
    @GetMapping
    public ResponseEntity<List<User>> findAll() {

        return ResponseEntity.ok().body(this.users);
    }

    @PostMapping("/new")
    public ResponseEntity<Mensage<User>> create(@RequestParam String name, @RequestParam String email,
            @RequestParam String phone, @RequestParam String password) {

        User user = new User(countId + 1, name, email, phone, password);
        if(!this.users.contains(user) && this.users.stream().noneMatch(user1 -> Objects.equals(user.getEmail(), user1.getEmail()))) {
            this.users.add(user);
            countId +=1;
            Mensage<User> mensage = new Mensage<User>("Usuario criado com sucesso", user);
            return ResponseEntity.ok().body(mensage);
        }else{
            Mensage<User> mensage = new Mensage<User>("E-mail ja cadastrado", user);
            return ResponseEntity.internalServerError().body(mensage);
        }
    }
}
