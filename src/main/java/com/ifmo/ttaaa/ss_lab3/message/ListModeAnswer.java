package com.ifmo.ttaaa.ss_lab3.message;

import com.ifmo.ttaaa.ss_lab3.app.ListMode;

public class ListModeAnswer {
    private String list;
    private String errorMessage;

    public ListModeAnswer() {
        try {
            this.list = ListMode.getList();
            this.errorMessage = "null";
        } catch (Exception e) {
           System.err.println(
                    """
                    Exception in method: "ListMode.getList"
                    """
            );
            System.err.println(e.getMessage());
            this.errorMessage = e.getMessage();
            this.list = "null";
        }
    }

    public String getList() {
        return list;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
