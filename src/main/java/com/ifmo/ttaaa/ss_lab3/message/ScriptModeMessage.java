package com.ifmo.ttaaa.ss_lab3.message;

public abstract class ScriptModeMessage {
    private int id;
    private String password;
    private String curPath;

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getCurPath() {
        return curPath;
    }
}
