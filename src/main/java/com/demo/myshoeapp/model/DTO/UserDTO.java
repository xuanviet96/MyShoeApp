package com.demo.myshoeapp.model.DTO;

import com.demo.myshoeapp.entity.Role;
import com.demo.myshoeapp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
    private long id;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private String avatar;
    private Set<Role> roles;
}
