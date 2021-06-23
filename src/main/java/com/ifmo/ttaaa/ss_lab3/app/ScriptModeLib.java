package com.ifmo.ttaaa.ss_lab3.app;

public class ScriptModeLib {
    static {
        try {
//            System.load("/home/ttaaa/gitlab.se.ifmo.ru/System-Software-Lab2/libScriptModeLib.so");
            System.loadLibrary("ScriptModeLib");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            throw e;
        }
    }

    public native int getPartition(String path);
    public native String lsCommand(String path);
    public native int cdCommand(String path, String to);
    public native int cpCommand(String path, String from, String to);
    public native String helpCommand();
}
