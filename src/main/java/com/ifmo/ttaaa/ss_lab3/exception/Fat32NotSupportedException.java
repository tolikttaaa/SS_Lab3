package com.ifmo.ttaaa.ss_lab3.exception;

public class Fat32NotSupportedException extends RuntimeException {
    public Fat32NotSupportedException(String path) {
        super("Fat32 not supported by path=\"" + path + "\"");
    }
}
