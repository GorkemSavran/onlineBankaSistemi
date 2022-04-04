package com.gorkemsavran.service;

import com.gorkemsavran.domain.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public enum UserService {
    INSTANCE;

    private User loggedInUser;
    private Set<User> users = new HashSet<>();

    public void loadUsers() throws IOException {
        String fileText = Files.readString(Path.of("users.json"));
        JSONArray usersJson = new JSONArray(fileText);
        usersJson.forEach(userJson -> users.add(User.fromJson((JSONObject) userJson)));
    }

    public void saveUsers() throws IOException {
        JSONArray usersJson = new JSONArray();
        users.stream().map(User::toJson).forEach(usersJson::put);
        Files.writeString(Path.of("users.json"), usersJson.toString());
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public boolean login(String tcKimlikNo, String password) {
        Optional<User> optionalUser = users.stream().filter(user -> user.getTcKimlikNo().equals(tcKimlikNo) && user.getPassword().equals(password)).findFirst();
        if (optionalUser.isEmpty()) return false;
        loggedInUser = optionalUser.get();
        return true;
    }

    public boolean logOut() {
        loggedInUser = null;
        return true;
    }

    public User getUser(String tcKimlikNo) {
        return users.stream().filter(user -> user.getTcKimlikNo().equals(tcKimlikNo)).findFirst().orElse(null);
    }

    public Collection<User> getUsers() {
        return users;
    }

    public boolean addUser(User user) {
        if (user.getPassword().contains(user.getDateOfBirth())) return false;
        return users.add(user);
    }

    public boolean updateUser(User user) {
        Optional<User> optionalUser = users.stream().filter(u -> u.equals(user)).findFirst();
        if (optionalUser.isEmpty()) return false;
        User u = optionalUser.get();
        u.update(user);
        return true;
    }
}
