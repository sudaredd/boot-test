package app.boot.boottest.controller;

import app.boot.boottest.model.User;
import app.boot.boottest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping
    @RequestMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return this.userService.getUserByUsername(username);
    }

    @PostMapping
    public ResponseEntity<Void> createNewUser(@RequestBody  @Valid User user, UriComponentsBuilder uriComponentsBuilder) {
        this.userService.storeNewUser(user);
        return ResponseEntity
            .created(uriComponentsBuilder.path("/api/users/{username}").build(user.getUsername()))
            .build();
    }
}
