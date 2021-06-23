package com.ifmo.ttaaa.ss_lab3.exception;

public class IllegalPasswordException extends IllegalAccessException {
    public IllegalPasswordException() {
        super("Password or session id is incorrect!");
    }
}
