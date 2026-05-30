package com.cfs.BookMyShow.service;

import com.cfs.BookMyShow.dto.LoginRequest;
import com.cfs.BookMyShow.dto.UserRequest;
import com.cfs.BookMyShow.entity.User;
import com.cfs.BookMyShow.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User register(UserRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists " + request.getEmail());
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phonenumber(request.getPhonenumber())
                .password(request.getPassword())
                .build();

        return userRepository.save(user);
    }

    public User login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("User not found with email"+request.getEmail()));
        if(!user.getPassword().equals(request.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        return user;
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).
                orElseThrow(()->new RuntimeException("User not found with id "+id));
    }
}
