package com.ifmo.ttaaa.ss_lab3.message;

import com.ifmo.ttaaa.ss_lab3.app.PasswordGenerator;
import com.ifmo.ttaaa.ss_lab3.app.ScriptMode;

public class ScriptModeStartAnswer {
    private String secretPassword;
    private int id;
    private String errorMessage;
    private String response;
    private String startPath;

    private ScriptModeStartAnswer(
            String secretPassword,
            int id,
            String errorMessage,
            String response,
            String startPath
    ) {
        this.secretPassword = secretPassword;
        this.id = id;
        this.errorMessage = errorMessage;
        this.response = response;
        this.startPath = startPath;
    }

    public static ScriptModeStartAnswer scriptModeStartAnswerByDeviceName(String deviceName) {
        int id = ScriptMode.getCurNum();
        String secretPassword = new PasswordGenerator.PasswordGeneratorBuilder()
                                                        .useLower(true)
                                                        .useUpper(true)
                                                        .build()
                                                    .generate(10);
        String errorMessage = null;
        String response = null;
        String startPath = null;

        try {
            response = ScriptMode.registerSessionByDeviceName(id, secretPassword, deviceName);
            startPath = ScriptMode.getCurPath(id, secretPassword);
        } catch (Throwable e) {
            errorMessage = String.format(
                    """
                    Exception in method: "ScriptMode.registerSessionByDeviceName"
                    with arguments: id=%d, password="%s", deviceName="%s"
                    %s
                    """,
                    id,
                    secretPassword,
                    deviceName,
                    e.getMessage()
            );
            System.err.println(errorMessage);
        }

        return new ScriptModeStartAnswer(secretPassword, id, errorMessage, response, startPath);
    }

    public static ScriptModeStartAnswer scriptModeStartAnswerByPath(String path) {
        int id = ScriptMode.getCurNum();
        String secretPassword = new PasswordGenerator.PasswordGeneratorBuilder()
                .useLower(true)
                .useUpper(true)
                .build()
                .generate(10);
        String errorMessage = null;
        String response = null;
        String startPath = null;

        try {
            response = ScriptMode.registerSessionByPath(id, secretPassword, path);
            startPath = ScriptMode.getCurPath(id, secretPassword);
        } catch (Throwable e) {
            errorMessage = String.format(
                    """
                    Exception in method: "ScriptMode.registerSessionByPath"
                    with arguments: id=%d, password="%s", path="%s"
                    %s
                    """,
                    id,
                    secretPassword,
                    path,
                    e.getMessage()
            );
            System.err.println(errorMessage);
        }

        return new ScriptModeStartAnswer(secretPassword, id, errorMessage, response, startPath);
    }

    public static ScriptModeStartAnswer scriptModeStartAnswerError(String errorMessage) {
        System.err.println(errorMessage);

        return new ScriptModeStartAnswer(null, 0, errorMessage, null, null);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSecretPassword() {
        return secretPassword;
    }

    public String getStartPath() {
        return startPath;
    }

    public int getId() {
        return id;
    }

    public String getResponse() {
        return response;
    }

    public void concatResponseBefore(String additional) {
        this.response = additional + "\n" + ((this.response == null) ? "" : this.response);
    }
}

