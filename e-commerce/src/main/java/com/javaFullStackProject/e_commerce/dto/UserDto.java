package com.javaFullStackProject.e_commerce.dto;

import com.javaFullStackProject.e_commerce.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String email;
    private String name;
    private UserRole userRole;
}
