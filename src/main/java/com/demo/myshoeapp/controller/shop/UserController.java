package com.demo.myshoeapp.controller.shop;

import com.demo.myshoeapp.entity.User;
import com.demo.myshoeapp.model.DTO.UserDTO;
import com.demo.myshoeapp.security.JwtTokenUtil;
import com.demo.myshoeapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/users")
    public ResponseEntity<Page<User>> getListUsers(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "3") int size) {
//        List<UserDTO> userDTOS = userService.getListUsers(page, size);
        return new ResponseEntity<>(userService.getAllUsers(page, size), HttpStatus.OK);
    }
    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        String message = "this enpoint for user only";
        return ResponseEntity.ok().body(message);
    }

//    @PostMapping("/api/admin/users")
//    public ResponseEntity<Object> createUser(@Valid @RequestBody CreateUserRequest createUserRequest){
//        User user = userService.createUser(createUserRequest);
//        return ResponseEntity.ok(UserMapper.toUserDTO(user));
//    }
//
//    @PostMapping("/api/register")
//    public ResponseEntity<Object> register(@Valid @RequestBody CreateUserRequest createUserRequest, HttpServletResponse response) {
//        //Create user
//        User user = userService.createUser(createUserRequest);
//
//        //Gen token
//        UserDetails principal = new CustomUserDetails(user);
//        String token = jwtTokenUtil.generateToken(principal);
//
//        //Add token on cookie to login
//        Cookie cookie = new Cookie("JWT_TOKEN", token);
//        cookie.setMaxAge(MAX_AGE_COOKIE);
//        cookie.setPath("/");
//        response.addCookie(cookie);
//
//        return ResponseEntity.ok(UserMapper.toUserDTO(user));
//    }
//
//    @PostMapping("/api/login")
//    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
//        //Authenticate
//        try {
//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                    loginRequest.getEmail(),
//                    loginRequest.getPassword()
//            ));
//            //Gen token
//            String token = jwtTokenUtil.generateToken((CustomUserDetails) authentication.getPrincipal());
//
//            //Add token to cookie to login
//            Cookie cookie = new Cookie("JWT_TOKEN", token);
//            cookie.setMaxAge(MAX_AGE_COOKIE);
//            cookie.setPath("/");
//            response.addCookie(cookie);
//
//            return ResponseEntity.ok(UserMapper.toUserDTO(((CustomUserDetails) authentication.getPrincipal()).getUser()));
//        } catch (Exception ex) {
//            throw new BadRequestException("Email hoặc mật khẩu không chính xác!");
//
//        }
//    }
//
//    @GetMapping("/tai-khoan")
//    public String getProfilePage(Model model) {
//        return "shop/account";
//    }
//
//    @PostMapping("/api/change-password")
//    public ResponseEntity<Object> changePassword(@Valid @RequestBody ChangePasswordRequest passwordReq) {
//        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
//        userService.changePassword(user, passwordReq);
//        return ResponseEntity.ok("Đổi mật khẩu thành công");
//    }
//
//    @PutMapping("/api/update-profile")
//    public ResponseEntity<Object> updateProfile(@Valid @RequestBody UpdateProfileRequest profileReq) {
//        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
//
//        user = userService.updateProfile(user, profileReq);
//        UserDetails userDetails = new CustomUserDetails(user);
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        return ResponseEntity.ok("Cập nhật thành công");
//    }
}
