package com.example.trelloBoard.Service;

import com.example.trelloBoard.Models.User;
import com.example.trelloBoard.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /*
    Method to add new user to repository
    input: User object
     */
    public void addNewUser(User u){
        userRepository.save(u);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
    /*
    Method to display all users in the repository
    returns Iterable<User> object
     */
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

}
