package com.ifmo.ttaaa.ss_lab3.message;

import com.ifmo.ttaaa.ss_lab3.app.ScriptMode;

public class ScriptModeLsAnswer {
    private String errorMessage;
    private String response;

    private ScriptModeLsAnswer(String errorMessage, String response) {
        this.errorMessage = errorMessage;
        this.response = response;
    }
    public static ScriptModeLsAnswer ScriptModeAnswerLs(int id, String password) {
        String response = null;
        String errorMessage = null;

        try {
            response = processToJson(ScriptMode.lsCommand(id, password));
        } catch (Throwable e) {
            errorMessage = String.format(
                    """
                    Exception in method: "ScriptMode.lsCommand"
                    with arguments: id=%d, password="%s"
                    %s
                    """,
                    id,
                    password,
                    e.getMessage()
            );
            System.err.println(errorMessage);
        }

        return new ScriptModeLsAnswer(errorMessage, response);
    }

    private static String processToJson(String input) {
        StringBuilder res = new StringBuilder("[");

        String[] arr = input.split("\n");

        for (int i = 0; i < arr.length; i++) {
            String cur = arr[i];

            String type;
            String name;

            if (cur.startsWith("DIR")) {
                type = "DIR";
                name = cur.substring(3).strip();
            } else {
                type = "FILE";
                name = cur.substring(4).strip();
            }

            res.append(String.format("{ \"type\": \"%s\", \"name\": \"%s\" }", type, name));

            if (i < arr.length - 1) {
                res.append(',');
            }
        }

        res.append("]");

        return res.toString();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getResponse() {
        return response;
    }
}
