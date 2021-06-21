package com.ifmo.ttaaa.ss_lab3.controller;

import com.ifmo.ttaaa.ss_lab3.message.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
    @MessageMapping("/list_mode")
    @SendToUser("/queue/list_mode_reply")
    public ListModeAnswer processMessageFromClient() throws Exception {
        return new ListModeAnswer();
    }

    @MessageMapping("/script_mode_start")
    @SendToUser("/queue/script_mode_start_reply")
    public ScriptModeStartAnswer processMessageFromClient(ScriptModeStartMessage inputMessage) throws Exception {
        System.err.println(inputMessage.getDeviceName());
        return new ScriptModeStartAnswer(inputMessage.getDeviceName());
    }

    @MessageMapping("/script_mode")
    @SendToUser("/queue/script_mode_reply")
    public ScriptModeAnswer processMessageFromClient(ScriptModeMessage inputMessage) throws Exception {
        //TODO: script mode messaging
        return null;
    }
}
