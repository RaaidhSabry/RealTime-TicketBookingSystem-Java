package com.coursework.ticketbookingsystem.user;

import org.springframework.stereotype.Repository;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class UserRepository {

    private static final String USER_FILE = "users.json";  // File path for storing users
    private ObjectMapper objectMapper = new ObjectMapper();

    // Method to load users from the JSON file
    private List<User> loadUsers() {
        try {
            File file = new File(USER_FILE);
            if (file.exists()) {
                // Read the users from the file and return
                return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // Method to save users to the JSON file
    private void saveUsers(List<User> users) {
        try {
            objectMapper.writeValue(new File(USER_FILE), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to find a user by email
    public User findByEmail(String email) {
        try {
            File file = new File("src/main/resources/user.json");
            List<User> users = Arrays.asList(objectMapper.readValue(file, User[].class));

            return users.stream()
                    .filter(user -> user.getEmail().equals(email))
                    .findFirst()
                    .orElse(null);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to add a user to the list and save
    public void save(User user) {
        List<User> users = loadUsers();
        users.add(user);  // Add the new user
        saveUsers(users);  // Save updated list of users
    }
}
