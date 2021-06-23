package com.ifmo.ttaaa.ss_lab3.app;

public class ListModeLib {
    static {
        try {
//            System.load("/home/ttaaa/gitlab.se.ifmo.ru/System-Software-Lab2/libListModeLib.so");
            System.loadLibrary("ListModeLib");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            throw e;
        }
    }

    public native String runListMode();
}
