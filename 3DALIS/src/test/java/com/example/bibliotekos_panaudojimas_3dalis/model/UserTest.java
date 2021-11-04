package com.example.bibliotekos_panaudojimas_3dalis.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void update() {
        var user = new User(1L, "Vardenis", "Pavardenis", "+3706856565656", "emailas@gmail.com", "Adress", "Password");
        var newUser = new User();
        newUser.update(user);

        assertNull(newUser.getId());
        assertEquals(user.getName(), newUser.getName());
        assertEquals(user.getSurname(), newUser.getSurname());
        assertEquals(user.getPhoneNumber(), newUser.getPhoneNumber());
        assertEquals(user.getEmail(), newUser.getEmail());
        assertEquals(user.getAddress(), newUser.getAddress());
        assertEquals(user.getPassword(), newUser.getPassword());
    }
}