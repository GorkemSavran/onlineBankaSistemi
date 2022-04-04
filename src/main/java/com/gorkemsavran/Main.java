package com.gorkemsavran;

import com.gorkemsavran.service.UserService;
import com.gorkemsavran.sreen.MainScreen;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        setUp();
        new MainScreen().onScreen();
        onExit();
    }

    private static void onExit() throws IOException {
        UserService.INSTANCE.saveUsers();
    }

    private static void setUp() throws IOException {
        UserService.INSTANCE.loadUsers();
    }
}
