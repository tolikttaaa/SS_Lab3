package com.ifmo.ttaaa.ss_lab3.app;

public class ListMode {
    private static final ListModeLib listModeLib = new ListModeLib();

    public static String getList() {
        return listModeLib.runListMode();
    }
}
