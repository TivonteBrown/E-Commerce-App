package com.revature.ecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.eCommerce.services.RoleService;
import com.revature.eCommerce.services.UserService;
import com.revature.eCommerce.models.User;

import com.revature.eCommerce.utils.ResourceNotFoundException;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.revature.eCommerce.dao.UserDao;

public class UserServiceTest {

    @Mock
    private RoleService roleService;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void UserServiceSaveTest() {
        //Mock roleService
        when(roleService.getRoleIDByName("DEFAULT")).thenReturn("DEFAULT_ROLE_ID");

        // Creating a user
        User userTest = new User();
        userTest.setName("testUser");
        userTest.setPassword("testPassword");

        // Call the save method
        User savedUser = userService.save(userTest);

        // Make sure the role ID is set to "DEFAULT_ROLE_ID"
        assertEquals("DEFAULT_ROLE_ID", savedUser.getRoleID());

        // Verify that the password is hashed
        assertTrue(BCrypt.checkpw("testPassword", savedUser.getPassword()));

    }


    @Test(expected = ResourceNotFoundException.class)
    public void UserServiceSaveTest_RoleNotFound() {
        // Mock roleService
        when(roleService.getRoleIDByName("DEFAULT")).thenReturn(null);

        // Creating a user
        User userTest = new User();
        userTest.setName("testUser");
        userTest.setPassword("testPassword");

        // Call the save method
        userService.save(userTest);
    }

    @Test
    public void UserServiceLoginTest_Successful() {
        // Mock
        String name = "testUser";
        String password = "testPassword";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

        User user = new User();
        user.setName(name);
        user.setPassword(hashedPassword);

        List<User> users = new ArrayList<>();
        users.add(user);

        // Mocking userDao.findAllWithRole() to return a list of users
        when(userDao.findAllWithRole()).thenReturn(users);

        // Call the login method
        Optional<User> loggedInUser = userService.login(name, password);

        // Verify that the user is there
        assertEquals(user, loggedInUser.orElse(null));
    }

    @Test
    public void UserServiceLoginTest_Unsuccessful() {
        // Mock
        String name = "testUser";
        String password = "testPassword";
        String hashedPassword = BCrypt.hashpw("wrongPassword", BCrypt.gensalt(12));

        User user = new User();
        user.setName(name);
        user.setPassword(hashedPassword);

        List<User> users = new ArrayList<>();
        users.add(user);

        // Mocking userDao.findAllWithRole()
        when(userDao.findAllWithRole()).thenReturn(users);

        // Call the login method
        Optional<User> loggedInUser = userService.login(name, password);

        // Verify that the user is not there
        assertEquals(Optional.empty(), loggedInUser);
    }

    @Test
    public void UserServiceIsUniqueUsernameTest_Unique() {
        // Mocking
        when(userDao.findAll()).thenReturn(new ArrayList<>());

        boolean isUnique = userService.isUniqueUsername("newUsername");

        // Verify that the method returns true for a unique username
        assertTrue(isUnique);
    }

    @Test
    public void UserServiceIsUniqueUsernameTest_NotUnique() {
        // Mocking data
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setName("existingUsername");
        users.add(user);

        when(userDao.findAll()).thenReturn(users);

        boolean isUnique = userService.isUniqueUsername("existingUsername");

        assertFalse(isUnique);
    }

    @Test
    public void UserServiceTestIsValidUsername_Valid() {
        // Valid usernames to test
        String[] validUsernames = {
            "user1234",
            "user.name",
            "user_name",
            "user123_name",
            "user_name123"
        };

        // Test each valid username
        for (String username : validUsernames) {
            assertTrue(userService.isValidUsername(username));
        }
    }

    @Test
    public void UserServiceTestIsValidUsername_Invalid() {

        String[] invalidUsernames = {
            "user",
            "user12345678901234567890", // Longer than 20 characters
            "_username", // Starts with underscore
            "username_", // Ends with underscore
            "user__name", // Contains consecutive underscores
            "user.name.", // Contains consecutive dots
            "user.name_" // Ends with underscore
        };

        // Test each invalid username
        for (String username : invalidUsernames) {
            assertFalse(userService.isValidUsername(username));
        }
    }

    @Test
    public void UserServiceTestIsValidPassword_Valid() {

        String[] validPasswords = {
            "Password1!",
            "StrongPassword123$",
            "P@ssw0rd!#",
            "12345678Aa!"
        };

        // Test each valid password
        for (String password : validPasswords) {
            assertTrue(userService.isValidPassword(password));
        }
    }

    @Test
    public void UserServiceTestIsValidPassword_Invalid() {

        String[] invalidPasswords = {
            "password", // No special character
            "short", // Less than eight characters
            "noNumbersOrSpecialChar",
            "NoSpecialCharacters1",
        };

        // Test each invalid password
        for (String password : invalidPasswords) {
            assertFalse(userService.isValidPassword(password));
        }
    }

    @Test
    public void UserServiceTestIsUniqueEmail_Unique() {
        // Mock userDao.findAll() to return an empty list
        when(userDao.findAll()).thenReturn(new ArrayList<>());


        boolean isUnique = userService.isUniqueEmail("new@example.com");

        // Verify
        assertTrue(isUnique);
    }

    @Test
    public void UserServiceTestIsUniqueEmail_NotUnique() {
        // Mocking data
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setEmail("existing@example.com");
        users.add(user);

        // Mocking userDao.findAll()
        when(userDao.findAll()).thenReturn(users);


        boolean isUnique = userService.isUniqueEmail("existing@example.com");

        // Verify that it is false
        assertFalse(isUnique);
    }
}