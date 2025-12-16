package com.gurkha.authService;

import com.gurkha.entities.User;
import com.gurkha.exception.UserNotFound;
import com.gurkha.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public  class LoggedUser {
    @Autowired
    private UserRepo userRepo;

    public User getLoggedUser(){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        return  userRepo.findByEmail(email).orElseThrow(()-> new UserNotFound("logged user not found"));
    }
}