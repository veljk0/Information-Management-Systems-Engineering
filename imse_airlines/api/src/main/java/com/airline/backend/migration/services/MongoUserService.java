package com.airline.backend.migration.services;

import com.airline.backend.migration.collections.MongoTicket;
import com.airline.backend.migration.collections.MongoUser;
import com.airline.backend.migration.repositories.MongoUserRepository;
import com.airline.backend.services.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoUserService {

    @Autowired
    private MongoUserRepository userRepository;

    private boolean flag = false;

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

    public String addUser(MongoUser user) {
        if(!checkIfUserExists(user.getEmail())){
            userRepository.save(user);
            return "User: " + user.getEmail() + " successfully added to database!";
        }
        else return "User with this: " + user.getEmail() + " already exists";
    }

    public MongoUser getUserPerEmail(String email){
        List<MongoUser> users = userRepository.findAll();
        MongoUser result = users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
        return result;
    }


    public String deleteAllUsers() {
        userRepository.deleteAll();
        return "All useres DELETED!";
    }

    public List<MongoUser> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean checkIfUserExists(String email) {
        List<MongoUser> users = getAllUsers();
        for (MongoUser user : users)
            if (user.getEmail().equals(email)) return true;
        return false;
    }

    public String buyTicket(String email, MongoTicket ticket){
        MongoUser user = getUserPerEmail(email);
        user.getTickets().add(ticket);
        userRepository.save(user);
        return "ticket " + ticket.getTicketID() + " bought for user " + user.getId();
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return flag;
    }

    public void updateUser(MongoUser mogli) {
        userRepository.save(mogli);
    }
}
