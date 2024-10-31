package ru.project.security.mappers;

import lombok.experimental.UtilityClass;
import ru.project.security.models.User;
import ru.project.security.models.UserDto;

@UtilityClass
public class UserMapper {
    public static UserDto asDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .id(user.getId())
                .password(user.getPassword())
                .role(user.getRole().name())
                .build();
    }
}
