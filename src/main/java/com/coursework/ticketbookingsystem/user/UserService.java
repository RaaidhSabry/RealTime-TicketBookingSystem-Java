package com.coursework.ticketbookingsystem.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    private static final String FILE_PATH = "src/main/resources/user.json";

    private final ObjectMapper objectMapper;

    public UserService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<User> getAllUsers() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return List.of();
        }
        return objectMapper.readValue(file, new TypeReference<List<User>>() {});
    }

    public void saveUser(User user) throws IOException {
        List<User> users = getAllUsers();
        users.add(user);
        objectMapper.writeValue(new File(FILE_PATH), users);
    }

    public User findByEmail(String email) throws IOException {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}