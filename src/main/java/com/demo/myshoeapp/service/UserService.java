package com.demo.myshoeapp.service;

import com.demo.myshoeapp.entity.User;
import com.demo.myshoeapp.model.DTO.UserDTO;
import com.demo.myshoeapp.model.request.ChangePasswordRequest;
import com.demo.myshoeapp.model.request.CreateUserRequest;
import com.demo.myshoeapp.model.request.UpdateProfileRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    Page<User> getAllUsers(int page, int size);

    Page<User> adminListUserPages(String fullName, String phone, String email, Integer page);

    User createUser(CreateUserRequest createUserRequest);

    void changePassword(User user, ChangePasswordRequest changePasswordRequest);

    User updateProfile(User user, UpdateProfileRequest updateProfileRequest);
}
