package ru.project.security.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserDto {

    private int id;
    private String name;
    private String password;
    private String role;
}