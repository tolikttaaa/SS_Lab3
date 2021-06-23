package com.ifmo.ttaaa.ss_lab3.app;

public class ScriptModeLib {
    static {
        try {
            System.loadLibrary("ScriptModeLib");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            throw e;
        }
    }

    public native long getPartition(String path);
    public native String lsCommand(long partPointer);
    public native int cdCommand(long partPointer, String to);
    public native int cpCommand(long partPointer, String from, String to);
    public native String helpCommand();
    public native void exitCommand(long partPointer);

}
