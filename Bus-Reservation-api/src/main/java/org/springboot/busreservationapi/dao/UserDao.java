package org.springboot.busreservationapi.dao;

import org.springboot.busreservationapi.model.User;
import org.springboot.busreservationapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {
    @Autowired
    private UserRepository userRepository;

    //-------------------For adding New User---------------
    public User addUser(User user) {
        return userRepository.save(user);
    }

    //-------------------Find User By Id---------------
    public Optional<User> findUserById(int userId) {
        return userRepository.findById(userId);
    }

    //-------------------Find All Users ---------------
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //-------------------Update User----------------
    public User updateUser(User existingUser) {
        return userRepository.save(existingUser);
    }

    //-------------------Delete User by Id------------
    public void delete(int userId) {
        userRepository.deleteById(userId);
    }

    //------------------Verify By Phone and Password----------------
    public Optional<User> verifyUserByPhoneAndPassword(long phone, String password) {
        return userRepository.findByPhoneAndPassword(phone, password);
    }

    //------------------Verify By Email and Password----------------
    public Optional<User> verifyUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
