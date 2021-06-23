package com.ifmo.ttaaa.ss_lab3.message;

import com.ifmo.ttaaa.ss_lab3.app.PasswordGenerator;
import com.ifmo.ttaaa.ss_lab3.app.ScriptMode;

public class ScriptModeStartAnswer {
    private String secretPassword;
    private int id;
    private String errorMessage;
    private String response;
    private String startPath;

    private static int curNum = 0;

    public ScriptModeStartAnswer(String deviceName) {
        this.id = curNum++;
        this.secretPassword = new PasswordGenerator.PasswordGeneratorBuilder()
                                                        .useLower(true)
                                                        .useUpper(true)
                                                        .build()
                                                    .generate(10);
        this.errorMessage = null;
        this.response = null;
        this.startPath = null;

        try {
            this.response = ScriptMode.registerSession(id, secretPassword, deviceName);
            this.startPath = ScriptMode.getCurPath(id, secretPassword);
        } catch (Throwable e) {
            this.errorMessage = String.format(
                    """
                    Exception in method: "ScriptMode.registerSession"
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
}

