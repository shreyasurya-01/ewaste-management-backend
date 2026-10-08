package EwasteManagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EwasteManagement.model.User;
import EwasteManagement.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        user.setRole(user.getRole());

        return repository.save(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginUser) {

        User user = repository.findByEmail(loginUser.getEmail())
                .orElse(null);

        if (user == null) {
            throw new RuntimeException("Invalid email or password");
        }

        if (!user.getPassword().equals(loginUser.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return user;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}