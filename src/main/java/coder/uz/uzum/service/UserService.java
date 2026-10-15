package coder.uz.uzum.service;

import coder.uz.uzum.entity.Role;
import coder.uz.uzum.entity.User;
import coder.uz.uzum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public boolean create(User user) {
        if (user == null) {
            return false;
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return false; // bu email allaqachon bor
        }
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }


    public User login(String email, String password) {
        if (email == null && password == null) {
            return null;
        } else {
            return userRepository
                    .findByEmail(email)
                    .orElse(null);
        }
    }


    public List getAll() {
        return userRepository.findAll();
    }

    public User getById(UUID id) {
        if (id == null) {
            return null;
        } else {
            return userRepository.getById(id);

        }


    }

    public boolean update(UUID id, User user) {
        if (id == null || user == null) {
            return false;
        } else {
            User old_user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User topilmadi"));
            old_user.setName(user.getName());
            old_user.setEmail(user.getEmail());
            if (user.getPassword() != null && !user.getPassword().isBlank()) {
                old_user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userRepository.save(old_user);
            return true;
        }
    }

    public boolean delete(UUID id) {
        if (id == null) {
            return false;
        } else {
            userRepository.deleteById(id);
            return true;
        }
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}