package com.ifmo.ttaaa.ss_lab3.controller;

import com.ifmo.ttaaa.ss_lab3.message.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpSession;

@Controller
public class WebSocketController {
    @MessageMapping("/list_mode")
    @SendToUser("/queue/list_mode_reply")
    public ListModeAnswer processMessageFromClient() throws Exception {
        return new ListModeAnswer();
    }

    @MessageMapping("/script_mode_start")
    @SendToUser("/queue/script_mode_start_reply")
    public ScriptModeStartAnswer processMessageFromClient(ScriptModeStartMessage inputMessage) throws Exception {
        return ScriptModeStartAnswer.scriptModeStartAnswerByDeviceName(inputMessage.getDeviceName());
    }

    @MessageMapping("/script_mode_help")
    @SendToUser("/queue/script_mode_reply")
    public ScriptModeAnswer processMessageFromClient(ScriptModeHelpMessage inputMessage) throws Exception {
        return ScriptModeAnswer.ScriptModeAnswerHelp(
                inputMessage.getId(),
                inputMessage.getPassword(),
                inputMessage.getCurPath()
        );
    }

    @MessageMapping("/script_mode_ls")
    @SendToUser("/queue/script_mode_ls_reply")
    public ScriptModeLsAnswer processMessageFromClient(ScriptModeLsMessage inputMessage) throws Exception {
        return ScriptModeLsAnswer.ScriptModeAnswerLs(
                inputMessage.getId(),
                inputMessage.getPassword()
        );
    }

    @MessageMapping("/script_mode_pwd")
    @SendToUser("/queue/script_mode_reply")
    public ScriptModeAnswer processMessageFromClient(ScriptModePwdMessage inputMessage) throws Exception {
        return ScriptModeAnswer.ScriptModeAnswerPwd(
                inputMessage.getId(),
                inputMessage.getPassword(),
                inputMessage.getCurPath()
        );
    }

    @MessageMapping("/script_mode_exit")
    @SendToUser("/queue/script_mode_reply")
    public ScriptModeAnswer processMessageFromClient(ScriptModeExitMessage inputMessage) throws Exception {
        return ScriptModeAnswer.ScriptModeAnswerExit(
                inputMessage.getId(),
                inputMessage.getPassword()
        );
    }

    @MessageMapping("/script_mode_cd")
    @SendToUser("/queue/script_mode_reply")
    public ScriptModeAnswer processMessageFromClient(ScriptModeCdMessage inputMessage) throws Exception {
        return ScriptModeAnswer.ScriptModeAnswerCd(
                inputMessage.getId(),
                inputMessage.getPassword(),
                inputMessage.getCurPath(),
                inputMessage.getTo()
        );
    }

    @MessageMapping("/script_mode_cp")
    @SendToUser("/queue/script_mode_reply")
    public ScriptModeAnswer processMessageFromClient(ScriptModeCpMessage inputMessage) throws Exception {
        return ScriptModeAnswer.ScriptModeAnswerCp(
                inputMessage.getId(),
                inputMessage.getPassword(),
                inputMessage.getCurPath(),
                inputMessage.getFrom(),
                inputMessage.getTo()
        );
    }
}
