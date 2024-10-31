package ru.project.security.services;

import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.project.security.mappers.UserMapper;
import ru.project.security.models.Role;
import ru.project.security.models.User;
import ru.project.security.models.UserDto;
import ru.project.security.repositories.UserRepository;

@Service
@ExtensionMethod(UserMapper.class)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserDto CreateUser(UserDto userDto) {
        User user = new User(
                userDto.getName(),
                encoder().encode(userDto.getPassword()),
                Role.valueOf(userDto.getRole()));
        userRepository.save(user);
        return user.asDto();
    }
    public UserDto getCurrentUser() {
        return userRepository.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName()).asDto();
    }

    public UserDto getUserByName(String name) {
        return userRepository.findUserByName(name).asDto();
    }
    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}