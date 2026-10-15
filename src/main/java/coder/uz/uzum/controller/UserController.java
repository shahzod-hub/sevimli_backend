package coder.uz.uzum.controller;

import coder.uz.uzum.entity.User;
import coder.uz.uzum.entity.dto.UserDto;
import coder.uz.uzum.service.JwtService;
import coder.uz.uzum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    ModelMapper modelMapper=new ModelMapper();
    @PostMapping("/signUp")
    public boolean signUp(@RequestBody UserDto userDto) {
        User user=modelMapper.map(userDto, User.class);
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        user.setCreateDate(LocalDateTime.now());

        return userService.create(user);
    }
    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password
    ) {
        User user = userService.findByEmail(email);

        if (user == null) {
            return "User topilmadi";
        }

        if (!userService.checkPassword(
                password,
                user.getPassword()
        )) {
            return "Password noto'g'ri";
        }

        return jwtService.generateToken(user);
    }
    @GetMapping("/getAll")
    public List<User> getAll() {
        return userService.getAll();
    }
    @GetMapping("/getById/{id}")
    public User getById(@PathVariable UUID id) {
        return userService.getById(id);
    }
    @PutMapping("/update/{id}")
    public boolean update(
            @PathVariable UUID id,
            @RequestBody User user) {

        return userService.update(id, user);
    }
    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable UUID id) {
        return userService.delete(id);
    }

















    }

