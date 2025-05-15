package com.educore.service.impl;

import com.educore.common.utils.ApiResponse;
import com.educore.exception.ApplicationException;
import com.educore.model.dto.requests.create.UserCreateRequest;
import com.educore.model.entity.Address;
import com.educore.model.entity.User;
import com.educore.repository.UserRepository;
import com.educore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User create(UserCreateRequest userCreateRequest) {
        if (fetchByPhoneNumber(userCreateRequest.getPhone()) != null) {
            throw new ApplicationException("Mobile number already registered");
        }
        User user = new User(
                userCreateRequest.getName(),
                userCreateRequest.getEmail(),
                userCreateRequest.getPhone(),
                passwordEncoder.encode(userCreateRequest.getPassword()),
                userCreateRequest.getRole());
        return userRepository.save(user);
    }

    @Override
    public User addAddress(String id, Address address) {
        User user = Optional.ofNullable(findById(id)).orElseThrow(
                () -> new ApplicationException("user not found"));
        user.setAddress(address);
        return userRepository.save(user);
    }

    @Override
    public User fetchByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void updateLastSignIn(String id) {
        User user = findById(id);
        user.setLastSignedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public Boolean updatePassword(String id, String oldPassword, String newPassword) {
        User user = Optional.ofNullable(findById(id)).orElseThrow(
                () -> new ApplicationException("User not found"));
        if(!passwordEncoder.matches(oldPassword, user.getPassword())){
            throw new ApplicationException("Wrong old password");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }

    @Override
    public ApiResponse<Boolean> updateAddress(String id, Address address) {
        User user = Optional.ofNullable(findById(id)).orElseThrow(
                () -> new ApplicationException("User not found"));
        user.setAddress(address);
        userRepository.save(user);
        return new ApiResponse<>(true, HttpStatus.OK);
    }

    @Override
    public Boolean updateOtp(int otp, String userId) {
//        User user = Optional.ofNullable(findById(userId)).orElseThrow(
//                () -> new ApplicationException("User not found"));
//        user.setOtp(otp);
//        userRepository.save(user);
        return true;
    }

    @Override
    public ApiResponse<Boolean> updateEmail(String id, String email) {
        User user = Optional.ofNullable(findById(id)).orElseThrow(
                () -> new ApplicationException("User not found"));

        User fetchedUser = userRepository.findByEmail(email);
        if(fetchedUser != null){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Email already registered");
        }else {
            user.setEmail(email);
            userRepository.save(user);
            return new ApiResponse<>(true, HttpStatus.OK);
        }
    }

    @Override
    public ApiResponse<Boolean> updateMobileNumber(String id, String newMobileNumber) {
        User user = Optional.ofNullable(findById(id)).orElseThrow(
                () -> new ApplicationException("User not found"));

       User fetchedUser = userRepository.findByPhoneNumber(newMobileNumber);
        if(fetchedUser != null){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Phone Number already registered");
        }else {
            user.setPhoneNumber(newMobileNumber);
            userRepository.save(user);
            return new ApiResponse<>(true, HttpStatus.OK);
        }
    }

    @Override
    public User findById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
