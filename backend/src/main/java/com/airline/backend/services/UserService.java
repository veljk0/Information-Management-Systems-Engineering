package com.airline.backend.services;

import java.util.List;
import com.airline.backend.entities.User;
import com.airline.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String addUser(User user) {
        if(!checkIfUserExists(user.getEmail())){
            userRepository.save(user);
            return "User: " + user.getEmail() + " successfully added to database!";
        }
        else return "User with this: " + user.getEmail() + " already exists";
    }

    public User getUser(int id){
        if(userRepository.existsById(id)) 
            return userRepository.getOne(id);

        System.out.println("User with this" + id + "does't exist in database!");
        return null;
    }

    public String deleteUser(int id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return "User with id: " + id + " is deleted!";
        }
        else return "User with id: " + id + " does't exist in database!";
    }

    public String deleteAllUsers() {
        userRepository.deleteAll();
        return "All useres DELETED!";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public boolean checkIfUserExists(String email) {
        List<User> users = getAllUsers();
        for (User user : users)
            if (user.getEmail().equals(email)) return true;
        return false;
    }
}
