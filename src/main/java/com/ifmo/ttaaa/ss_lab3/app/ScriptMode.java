package com.ifmo.ttaaa.ss_lab3.app;

import java.util.HashMap;
import java.util.Map;

public class ScriptMode {
    private static final Map<Integer, String> sessionPassword = new HashMap<>();
    private static final Map<Integer, String> currentPath = new HashMap<>();

    public static String registerSession(int id, String password, String deviceName) {
        sessionPassword.put(id, password);
        String startPath = generateStartPath(deviceName);
        currentPath.put(id, startPath);

        //TODO: check connection

        return startPath;
    }

    private static String generateStartPath(String deviceName) {
        //TODO: generate start path
        return "TODO";
    }
}
