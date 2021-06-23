package com.ifmo.ttaaa.ss_lab3.message;

import com.ifmo.ttaaa.ss_lab3.app.ListMode;

public class ListModeAnswer {
    private String list;
    private String errorMessage;

    public ListModeAnswer() {
        try {
            this.list = ListMode.getList();
            this.errorMessage = null;
        } catch (Throwable e) {
            this.errorMessage = String.format(
                 """
                 Exception in method: "ListMode.getList"
                 %s
                 """,
                 e.getMessage()
            );
            System.err.println(errorMessage);
            this.list = null;
        }
    }

    public String getList() {
        return list;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
