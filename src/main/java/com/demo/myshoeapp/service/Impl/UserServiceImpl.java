package com.demo.myshoeapp.service.Impl;

import com.demo.myshoeapp.entity.User;
import com.demo.myshoeapp.exception.BadRequestException;
import com.demo.myshoeapp.model.DTO.UserDTO;
import com.demo.myshoeapp.model.DTO.UsersDTO;
import com.demo.myshoeapp.model.mapper.UserMapper;
import com.demo.myshoeapp.model.request.ChangePasswordRequest;
import com.demo.myshoeapp.model.request.CreateUserRequest;
import com.demo.myshoeapp.model.request.UpdateProfileRequest;
import com.demo.myshoeapp.repository.UserRepository;
import com.demo.myshoeapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.demo.myshoeapp.config.Contant.LIMIT_USER;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> getAllUsers(int page, int size) {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(UserMapper.toUserDTO(user));
        }

        return userRepository.findAll(PageRequest.of(page, size));
    }
//    public ResponseEntity<UserDTO> getAllUsersDTO(Page<User> usersInPage) {
//        List<UserDTO> userDTOS = new ArrayList<>();
//        for (User user : usersInPage) {
//            userDTOS.add(UserMapper.toUserDTO(user));
//        }
//        UsersDTO usersDTO = new UsersDTO();
//        usersDTO.setUserDTOList();
//    }

    @Override
    public Page<User> adminListUserPages(String fullName, String phone, String email, Integer page) {
        page--;
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, LIMIT_USER, Sort.by("created_at").descending());
        return userRepository.adminListUserPages(fullName, phone, email, pageable);
    }

    @Override
    public User createUser(CreateUserRequest createUserRequest) {
        User user = userRepository.findByEmail(createUserRequest.getEmail());
        if (user != null) {
            throw new BadRequestException("Email đã tồn tại trong hệ thống. Vui lòng sử dụng email khác!");
        }
        user = UserMapper.toUser(createUserRequest);
        userRepository.save(user);
        return user;
    }

    @Override
    public void changePassword(User user, ChangePasswordRequest changePasswordRequest) {
        //Kiểm tra mật khẩu
        if (!BCrypt.checkpw(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("Mật khẩu cũ không chính xác");
        }

        String hash = BCrypt.hashpw(changePasswordRequest.getNewPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);
        userRepository.save(user);
    }

    @Override
    public User updateProfile(User user, UpdateProfileRequest updateProfileRequest) {
        user.setFullName(updateProfileRequest.getFullName());
        user.setPhone(updateProfileRequest.getPhone());
        user.setAddress(updateProfileRequest.getAddress());
        return userRepository.save(user);
    }
}
