package com.spring.resources;

import com.spring.Entities.Mensage;
import com.spring.Entities.User;
import com.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    private UserRepository userRepository;

    int countId = 0;
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok().body(users);
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

    @DeleteMapping("/exclude")
    public ResponseEntity<Mensage<User>> delete(@RequestParam String email) {
        List<User> user = this.userRepository.findByEmail(email);
        if(user.size() > 0) {
            userRepository.delete(user.get(0));
            return  ResponseEntity.ok().body(new Mensage<User>("Usuario deletado com sucesso", user.get(0)));
        }else{
            return ResponseEntity.internalServerError().body(new Mensage<User>("Usuario nao existente", null));
        }
    }
}
