package com.ifmo.ttaaa.ss_lab3.app;

import com.ifmo.ttaaa.ss_lab3.exception.DirNotFoundException;
import com.ifmo.ttaaa.ss_lab3.exception.Fat32NotSupportedException;
import com.ifmo.ttaaa.ss_lab3.exception.IllegalPasswordException;

import java.util.HashMap;
import java.util.Map;

public class ScriptMode {
    private static final Map<Integer, String> sessionPassword = new HashMap<>();
    private static final Map<Integer, Long> partPointer = new HashMap<>();
    private static final Map<Integer, String> curPath = new HashMap<>();

    private static final ScriptModeLib scriptModeLib = new ScriptModeLib();

    public static String getCurPath(int id, String password) throws IllegalPasswordException {
        checkPassword(id, password);
        return curPath.get(id);
    }

    public static String registerSession(int id, String password, String deviceName) {
        String response;
        long pointer = register(deviceName);

        if (pointer == 0) {
            throw new Fat32NotSupportedException(deviceName);
        }
        response = "FAT32 supported!!!";
        sessionPassword.put(id, password);
        curPath.put(id, deviceName);
        partPointer.put(id, pointer);

        return response;
    }

    public static String helpCommand(int id, String password) throws IllegalPasswordException {
        checkPassword(id, password);

        return scriptModeLib.helpCommand();
    }

    public static String lsCommand(int id, String password) throws IllegalPasswordException {
        checkPassword(id, password);
        return scriptModeLib.lsCommand(partPointer.get(id));
    }

    public static String pwdCommand(int id, String password) throws IllegalPasswordException {
        checkPassword(id, password);
        return getCurPath(id, password);
    }

    public static void exitCommand(int id, String password) throws IllegalPasswordException {
        checkPassword(id, password);

        scriptModeLib.exitCommand(partPointer.get(id));
        partPointer.remove(id);
        sessionPassword.remove(id);
    }

    public static String cdCommand(int id, String password, String to) throws IllegalPasswordException {
        checkPassword(id, password);

        String response;

        if (scriptModeLib.cdCommand(partPointer.get(id), to) == 0) {
            throw new DirNotFoundException("Cd failed!", to, getCurPath(id, password));
        }

        response = """
                   Cd successfully!
                   """;
        switch (to) {
            case "..":
                curPath.put(id, substringUntil(getCurPath(id, password), '/'));
                break;
            case ".":
                break;
            default:
                curPath.put(id, getCurPath(id, password) + "/" + to);
                break;
        }

        return response;
    }

    public static String cpCommand(int id, String password, String from, String to) throws IllegalPasswordException {
        checkPassword(id, password);

        String response;

        switch (scriptModeLib.cpCommand(partPointer.get(id), from, to)) {
            case 0 -> response = "Cp successfully!";
            case 1 -> throw new DirNotFoundException("Cp failed!", from, getCurPath(id, password));
            case 2 -> throw new DirNotFoundException("Cp failed!", to, getCurPath(id, password));
            default -> throw new RuntimeException("Cp failed!");
        }

        return response;
    }

    private static void checkPassword(int id, String password) throws IllegalPasswordException {
        if (!sessionPassword.containsKey(id) || !sessionPassword.get(id).equals(password)) {
            throw new IllegalPasswordException();
        }
    }

    public static long register(String deviceName) {
        return scriptModeLib.getPartition(deviceName);
    }

    private static String substringUntil(String path, char c) {
        for (int i = path.length() - 1; i >= 0; i--) {
            if (path.charAt(i) == c) {
                return path.substring(0, i);
            }
        }

        return "";
    }
}
