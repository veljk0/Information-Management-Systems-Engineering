package com.airline.backend.services;

import java.util.List;

import com.airline.backend.entities.Ticket;
import com.airline.backend.entities.User;
import com.airline.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private Token token = new Token("empty", false);

    public String login(String username){
        if(token.getFlag() && token.getUsername().equals(username)) return "user already logged in";
        
        token.setUsername(username);
        token.setFlag(true);
        return "Login was successfull!";
    }

    public Token getUser(){
        return token;
    }

    public String logout(){
        token.setUsername("empty");
        token.setFlag(false);
        return "User is logged out";
    }

    public String addUser(User user) {
        if(!checkIfUserExists(user.getEmail())){
            userRepository.save(user);
            return "User: " + user.getEmail() + " successfully added to database!";
        }
        else return "User with this: " + user.getEmail() + " already exists";
    }

    public User getUserPerEmail(String email){
       List<User> users = userRepository.findAll();
       User result = users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
       return result;
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

    public String buyTicket(String email, Ticket ticket){
        User user = getUserPerEmail(email);
        user.getTickets().add(ticket);
        userRepository.save(user);
        return "ticket " + ticket.getTicketID() + " bought for user " + user.getId();
    }

}
