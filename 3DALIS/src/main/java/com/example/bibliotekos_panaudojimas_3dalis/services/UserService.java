package com.example.bibliotekos_panaudojimas_3dalis.services;

import com.example.bibliotekos_panaudojimas_3dalis.model.User;
import com.example.bibliotekos_panaudojimas_3dalis.repository.UserRepository;
import com.example.bibliotekos_panaudojimas_3dalis.validator.CountryCode;
import com.example.bibliotekos_panaudojimas_3dalis.validator.EmailValidator;
import com.example.bibliotekos_panaudojimas_3dalis.validator.PasswordChecker;
import com.example.bibliotekos_panaudojimas_3dalis.validator.PhoneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Long addUser(User user) {
        if (validateUserFields(user)) {
            return userRepository.save(user).getId();
        }
        return null;
    }

    public void updateUser(User newUser) {
        var user = userRepository.findById(newUser.getId()).orElse(null);
        if (user != null && validateUserFields(newUser)) {
            user.update(newUser);
            userRepository.save(user);
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    private boolean validateUserFields(User user) {
        EmailValidator emailValidator = new EmailValidator();
        PasswordChecker passwordChecker = new PasswordChecker(5);
        PhoneValidator phoneValidator = new PhoneValidator();
        var emailValidated = emailValidator.validate(user.getEmail());
        var passwordValidated = passwordChecker.validate(user.getPassword());
        var phoneValidated = phoneValidator.validate(user.getPhoneNumber(), new CountryCode("LT", 5, "+370", "8"));

        if (!emailValidated.status) {
            System.out.println("------------------");
            System.out.println("ERROR VALIDATING EMAIL\n" + emailValidated.value);
            System.out.println("------------------");
        }
        if (!passwordValidated.status) {
            System.out.println("------------------");
            System.out.println("ERROR VALIDATING PASSWORD\n" + passwordValidated.value);
            System.out.println("------------------");
        }
        if (!phoneValidated.status) {
            System.out.println("------------------");
            System.out.println("ERROR VALIDATING PHONE\n" + phoneValidated.value);
            System.out.println("------------------");
        }
        return emailValidated.status && passwordValidated.status && phoneValidated.status;
    }
}
