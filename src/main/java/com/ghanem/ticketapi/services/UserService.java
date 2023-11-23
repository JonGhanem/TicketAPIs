package com.ghanem.ticketapi.services;

import com.ghanem.ticketapi.models.User;
import com.ghanem.ticketapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user){
       return userRepository.save(user);
    }

    public boolean findOneByEmailAndPassword(String email, String password){
        User user = userRepository.readByEmail(email);
        if(passwordEncoder.matches(password, user.getPassword())){
            return true;
        }
        return false;
    }

}
