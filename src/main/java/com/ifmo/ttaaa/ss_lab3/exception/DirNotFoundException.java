package com.ifmo.ttaaa.ss_lab3.exception;

public class DirNotFoundException extends IllegalArgumentException {
    public DirNotFoundException(String message, String dirName, String path) {
        this(String.format(
                """
                %s
                Dir not found, dirName="%s", path="%s"
                """,
                message,
                dirName,
                path
                )
        );
    }

    public DirNotFoundException(String dirName, String path) {
        this("", dirName, path);
    }

    public DirNotFoundException(String message) {
        super(message);
    }
}
