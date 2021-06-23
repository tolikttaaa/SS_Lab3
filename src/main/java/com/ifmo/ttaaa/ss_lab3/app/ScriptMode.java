package com.ifmo.ttaaa.ss_lab3.app;

import com.ifmo.ttaaa.ss_lab3.exception.DirNotFoundException;
import com.ifmo.ttaaa.ss_lab3.exception.Fat32NotSupportedException;
import com.ifmo.ttaaa.ss_lab3.exception.IllegalPasswordException;

import java.util.HashMap;
import java.util.Map;

public class ScriptMode {
    private static final Map<Integer, String> sessionPassword = new HashMap<>();
    private static final Map<Integer, String> currentPath = new HashMap<>();
    private static final Map<Integer, String> targets = new HashMap<>();

    private static final ScriptModeLib scriptModeLib = new ScriptModeLib();

    public static String getCurPath(int id, String password) throws IllegalPasswordException {
        checkPassword(id, password);
        return targets.get(id) + currentPath.get(id);
    }

    public static String registerSession(int id, String password, String deviceName) {
        String response = checkConnection(deviceName);

        sessionPassword.put(id, password);
        targets.put(id, deviceName);
        currentPath.put(id, "");

        return response;
    }

    public static String helpCommand(int id, String password) throws IllegalPasswordException {
        checkPassword(id, password);

        return scriptModeLib.helpCommand();
    }

    public static String lsCommand(int id, String password) throws IllegalPasswordException {
        checkPassword(id, password);

        return scriptModeLib.lsCommand(getCurPath(id, password));
    }

    public static String pwdCommand(int id, String password) throws IllegalPasswordException {
        checkPassword(id, password);

        return getCurPath(id, password);
    }

    public static void exitCommand(int id, String password) throws IllegalPasswordException {
        checkPassword(id, password);

        currentPath.remove(id);
        targets.remove(id);
        sessionPassword.remove(id);
    }

    public static String cdCommand(int id, String password, String to) throws IllegalPasswordException {
        checkPassword(id, password);

        String response;

        if (scriptModeLib.cdCommand(getCurPath(id, password), to) == 1) {
            throw new DirNotFoundException("Cd failed!", to, getCurPath(id, password));
        } else {
            response = """
                       Cd successfully!
                       """;
            switch (to) {
                case "..":
                    currentPath.put(id, substringUntil(getCurPath(id, password), '/'));
                    break;
                case ".":
                    break;
                default:
                    currentPath.put(id, getCurPath(id, password) + "/" + to);
                    break;
            }
        }

        return response;
    }

    public static String cpCommand(int id, String password, String from, String to) throws IllegalPasswordException {
        checkPassword(id, password);

        String response;

        switch (scriptModeLib.cpCommand(getCurPath(id, password), from, to)) {
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

    public static String checkConnection(String path) {
        if (scriptModeLib.getPartition(path) == 0) {
            return "FAT32 supported!";
        }
        throw new Fat32NotSupportedException(path);
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
