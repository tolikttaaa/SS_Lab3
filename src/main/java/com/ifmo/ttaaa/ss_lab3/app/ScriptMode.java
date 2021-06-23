package com.ifmo.ttaaa.ss_lab3.app;

import com.ifmo.ttaaa.ss_lab3.IllegalPasswordException;

import java.util.HashMap;
import java.util.Map;

public class ScriptMode {
    private static final Map<Integer, String> sessionPassword = new HashMap<>();
    private static final Map<Integer, String> currentPath = new HashMap<>();

    public static String getCurPath(int id, String password) throws IllegalPasswordException {
        checkPassword(id, password);
        return currentPath.get(id);
    }

    public static String registerSession(int id, String password, String deviceName) {
        sessionPassword.put(id, password);
        String startPath = generateStartPath(deviceName);
        currentPath.put(id, startPath);

        //TODO: check connection

        return startPath;
    }

    private static String generateStartPath(String deviceName) {
        //TODO: generate start path
        return "TODO: generate start path";
    }

    public static String helpCommand(int id, String password) throws IllegalPasswordException {
        checkPassword(id, password);

        //TODO: help command
        return "TODO: help command";
    }

    public static String lsCommand(int id, String password) throws IllegalPasswordException {
        checkPassword(id, password);

        //TODO: ls command
        return "TODO: ls command";
    }

    public static String pwdCommand(int id, String password) throws IllegalPasswordException {
        checkPassword(id, password);

        //TODO: pwd command
        return "TODO: pwd command";
    }

    public static String exitCommand(int id, String password) throws IllegalPasswordException {
        checkPassword(id, password);

        //TODO: exit command
        return "TODO: exit command";
    }

    public static String cdCommand(int id, String password, String to) throws IllegalPasswordException {
        checkPassword(id, password);

        //TODO: exit command
        return "TODO: cd command";
    }

    public static String cpCommand(int id, String password, String from, String to) throws IllegalPasswordException {
        checkPassword(id, password);

        //TODO: exit command
        return "TODO: cp command";
    }

    private static void checkPassword(int id, String password) throws IllegalPasswordException {
        if (!sessionPassword.containsKey(id) || !sessionPassword.get(id).equals(password)) {
            throw new IllegalPasswordException();
        }
    }
}
