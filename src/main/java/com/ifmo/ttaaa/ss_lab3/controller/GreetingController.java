package com.ifmo.ttaaa.ss_lab3.controller;

import com.ifmo.ttaaa.ss_lab3.message.ListModeAnswer;
import com.ifmo.ttaaa.ss_lab3.message.ScriptModeStartAnswer;
import com.ifmo.ttaaa.ss_lab3.message.ScriptModeStartMessage;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
    @MessageMapping("/list_mode")
    @SendToUser("/queue/reply")
    public ListModeAnswer processMessageFromClient() throws Exception {
        return new ListModeAnswer();
    }

    @MessageMapping("/script_mode")
    @SendToUser("/queue/reply")
    public ScriptModeStartAnswer processMessageFromClient(ScriptModeStartMessage inputMessage) throws Exception {
        System.err.println(inputMessage.getDeviceName());
        return new ScriptModeStartAnswer(inputMessage.getDeviceName());
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
}
