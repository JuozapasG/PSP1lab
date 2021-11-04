package com.example.bibliotekos_panaudojimas_3dalis.services;

import com.example.bibliotekos_panaudojimas_3dalis.model.User;
import com.example.bibliotekos_panaudojimas_3dalis.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {

    @Mock
    UserRepository userRepositoryMock;

    @InjectMocks
    UserService userService;

    @Test
    void addUser() {
        var User = new User();
        when(userRepositoryMock.save(Mockito.any(User.class))).thenReturn(User);
        var user = new User(1L, "Vardenis", "Pavardenis", "82", "emailas@gmail.com", "Adress", "Password132!");
        userService.addUser(user);
        verify(userRepositoryMock).save(Mockito.any(User.class));
    }

    @Test
    void getAllUsers() {
        var user1 = new User(1L, "Vardenis1", "Pavardenis1", "+37068565656561", "emailas@gmail.com1", "Adress1", "Password1");
        var user2 = new User(1L, "Vardenis2", "Pavardenis2", "+37068565656562", "emailas@gmail.com2", "Adress2", "Password2");
        var userList = List.of(user1, user2);
        when(userRepositoryMock.findAll()).thenReturn(userList);
        var users = userService.getAllUsers();
        assertEquals(2, users.size());
        assertEquals("Vardenis1", users.get(0).getName());
        assertEquals("Vardenis2", users.get(1).getName());
    }

    @Test
    void updateUser(){
        var newUser = new User(1L, "Vardenis", "Pavardenis", "82", "emailas@gmail.com", "Adress", "Password132!");
        when(userRepositoryMock.findById(1L)).thenReturn(java.util.Optional.of(newUser));
        when(userRepositoryMock.save(Mockito.any(User.class))).thenReturn(newUser);
        var user = new User(1L, "Vardenis2", "Pavardenis2", "82", "emailas2@gmail.com", "Adress2", "Password132!");
        userService.updateUser(user);
        verify(userRepositoryMock).save(Mockito.any(User.class));
        assertEquals(1L, newUser.getId());
        assertEquals(user.getName(), newUser.getName());
        assertEquals(user.getSurname(), newUser.getSurname());
        assertEquals(user.getPhoneNumber(), newUser.getPhoneNumber());
        assertEquals(user.getEmail(), newUser.getEmail());
        assertEquals(user.getAddress(), newUser.getAddress());
        assertEquals(user.getPassword(), newUser.getPassword());
    }

    @Test
    void getUserById() {
        var user = new User(1L, "Vardenis", "Pavardenis", "82", "emailas@gmail.com", "Adress", "Password132!");
        when(userRepositoryMock.findById(1L)).thenReturn(java.util.Optional.of(user));
        assertNotNull(userService.getUserById(1L));
        assertNull(userService.getUserById(2L));
    }

    @Test
    void deleteById() {
        userService.deleteById(1L);
        verify(userRepositoryMock).deleteById(Mockito.anyLong());
    }
}