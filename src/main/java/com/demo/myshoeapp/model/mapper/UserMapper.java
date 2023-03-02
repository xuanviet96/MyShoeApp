package com.demo.myshoeapp.model.mapper;

import com.demo.myshoeapp.entity.Role;
import com.demo.myshoeapp.entity.User;
import com.demo.myshoeapp.model.DTO.UserDTO;
import com.demo.myshoeapp.model.request.CreateUserRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddress(user.getAddress());
        userDTO.setPhone(user.getPhone());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setRoles(user.getRoles());

        return userDTO;
    }

    public static User toUser(@Valid CreateUserRequest createUserRequest) {
        //Set default ROLE_USER for user
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER"));

        User user = new User();
        user.setFullName(createUserRequest.getFullName());
        user.setEmail(createUserRequest.getEmail());
        // Hash password using BCrypt
        String hash = BCrypt.hashpw(createUserRequest.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);
//        user.setPassword(createUserRequest.getPassword());
        user.setPhone(createUserRequest.getPhone());
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setStatus(true);
        user.setRoles(roles);

        return user;
    }
}
