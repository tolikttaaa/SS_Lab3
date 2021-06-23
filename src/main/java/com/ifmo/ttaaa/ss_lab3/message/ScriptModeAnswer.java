package com.ifmo.ttaaa.ss_lab3.message;

import com.ifmo.ttaaa.ss_lab3.app.ScriptMode;

public class ScriptModeAnswer {
    private String errorMessage;
    private String response;
    private String path;

    private ScriptModeAnswer(String errorMessage, String response, String path) {
        this.errorMessage = errorMessage;
        this.response = response;
        this.path = path;
    }

    public static ScriptModeAnswer ScriptModeAnswerHelp(int id, String password, String curPath) {
        String response = null;
        String errorMessage = null;

        try {
            response = ScriptMode.helpCommand(id, password);
        } catch (Exception e) {
            System.err.printf(
                    """
                    Exception in method: "ScriptMode.helpCommand"
                    with arguments: id=%d, password="%s"
                    """,
                    id,
                    password
            );
            System.err.println(e.getMessage());
            errorMessage = e.getMessage();
        }

        return new ScriptModeAnswer(errorMessage, response, curPath);
    }

    public static ScriptModeAnswer ScriptModeAnswerLs(int id, String password, String curPath) {
        String response = null;
        String errorMessage = null;

        try {
            response = ScriptMode.lsCommand(id, password);
        } catch (Exception e) {
            System.err.printf(
                    """
                    Exception in method: "ScriptMode.lsCommand"
                    with arguments: id=%d, password="%s"
                    """,
                    id,
                    password
            );
            System.err.println(e.getMessage());
            errorMessage = e.getMessage();
        }

        return new ScriptModeAnswer(errorMessage, response, curPath);
    }

    public static ScriptModeAnswer ScriptModeAnswerPwd(int id, String password, String curPath) {
        String response = null;
        String errorMessage = null;

        try {
            response = ScriptMode.pwdCommand(id, password);
        } catch (Exception e) {
            System.err.printf(
                    """
                    Exception in method: "ScriptMode.pwdCommand"
                    with arguments: id=%d, password="%s"
                    """,
                    id,
                    password
            );
            System.err.println(e.getMessage());
            errorMessage = e.getMessage();
        }

        return new ScriptModeAnswer(errorMessage, response, curPath);
    }

    public static ScriptModeAnswer ScriptModeAnswerExit(int id, String password) {
        String response = null;
        String errorMessage = null;

        try {
            response = ScriptMode.exitCommand(id, password);
        } catch (Exception e) {
            System.err.printf(
                    """
                    Exception in method: "ScriptMode.exitCommand"
                    with arguments: id=%d, password="%s"
                    """,
                    id,
                    password
            );
            System.err.println(e.getMessage());
            errorMessage = e.getMessage();
        }

        return new ScriptModeAnswer(errorMessage, response, null);
    }

    public static ScriptModeAnswer ScriptModeAnswerCd(int id, String password, String curPath, String to) {
        String response = null;
        String errorMessage = null;
        String path = curPath;

        try {
            response = ScriptMode.cdCommand(id, password, to);
            path = ScriptMode.getCurPath(id, password);
        } catch (Exception e) {
            System.err.printf(
                    """
                    Exception in method: "ScriptMode.cdCommand"
                    with arguments: id=%d, password="%s", to="%s"
                    """,
                    id,
                    password,
                    to
            );
            System.err.println(e.getMessage());
            errorMessage = e.getMessage();
        }

        return new ScriptModeAnswer(errorMessage, response, path);
    }

    public static ScriptModeAnswer ScriptModeAnswerCp(int id, String password, String curPath, String from, String to) {
        String response = null;
        String errorMessage = null;

        try {
            response = ScriptMode.cpCommand(id, password, from, to);
        } catch (Exception e) {
            System.err.printf(
                    """
                    Exception in method: "ScriptMode.cpCommand"
                    with arguments: id=%d, password="%s", from="%s", to="%s"
                    """,
                    id,
                    password,
                    from,
                    to
            );
            System.err.println(e.getMessage());
            errorMessage = e.getMessage();
        }

        return new ScriptModeAnswer(errorMessage, response, curPath);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getResponse() {
        return response;
    }

    public String getPath() {
        return path;
    }
}
