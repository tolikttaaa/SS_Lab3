package com.ifmo.ttaaa.ss_lab3.message;

import com.ifmo.ttaaa.ss_lab3.app.PasswordGenerator;
import com.ifmo.ttaaa.ss_lab3.app.ScriptMode;

public class ScriptModeStartAnswer {
    private String secretPassword;
    private int id;
    private String errorMessage;
    private String startPath;

    private static int curNum = 0;

    public ScriptModeStartAnswer(String deviceName) {
        this.id = curNum++;
        this.secretPassword = new PasswordGenerator.PasswordGeneratorBuilder()
                                                        .useLower(true)
                                                        .useUpper(true)
                                                        .build()
                                                    .generate(10);
        try {
            this.startPath = ScriptMode.registerSession(id, secretPassword, deviceName);
            this.errorMessage = "null";
        } catch (Exception e) {
            System.err.printf(
                    """
                    Exception in method: "ScriptMode.registerSession"
                    with arguments: id=%d, password="%s", deviceName="%s"
                    """,
                    id,
                    secretPassword,
                    deviceName
            );
            System.err.println(e.getMessage());
            this.errorMessage = e.getMessage();
            this.startPath = "null";
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
}

