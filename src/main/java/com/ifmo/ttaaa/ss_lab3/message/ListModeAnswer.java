package com.ifmo.ttaaa.ss_lab3.message;

import com.ifmo.ttaaa.ss_lab3.app.ListMode;

public class ListModeAnswer {
    private String list;

    public ListModeAnswer() {
        this.list = ListMode.getList();
    }

    public String getList() {
        return list;
    }
}
