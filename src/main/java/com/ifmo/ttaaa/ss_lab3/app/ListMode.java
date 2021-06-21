package com.ifmo.ttaaa.ss_lab3.app;

import java.util.Random;

public class ListMode {
    public static String getList() throws Exception {
        //TODO: get list of devices
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new Exception("Test exception!!!!");
        }
        return "TODO: list mode" + random.nextInt(100);
    }
}
