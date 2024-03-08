package com.File_Hider.File_Hider.Services;

import com.File_Hider.File_Hider.Models.User;
import com.File_Hider.File_Hider.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    /*
     Function info:Checking whether particular user exists
     Parameter:email(to be checked)
    */
    public boolean isExists(String email){
        List<User> userList=userRepository.findAll();
        //Iterating through all the users and checking if email already exists
        for(User user:userList){
            if(user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    /*
    Function info:Saving the user details in the db
    Parameter:User(details about user)
    */
    public void saveUser(User user){
        userRepository.save(user);
        System.out.println("User saved to db");
    }
}
