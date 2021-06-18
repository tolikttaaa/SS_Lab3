package com.ifmo.ttaaa.ss_lab3.controller;

import com.ifmo.ttaaa.ss_lab3.hello.Greeting;
import com.ifmo.ttaaa.ss_lab3.hello.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) throws Exception {
//        Thread.sleep(3000); // simulated delay
//        System.err.println(message.getName());
//        return new Greeting("Hello, " + message.getName() + "!");
//    }

    @MessageMapping("/hello")
    @SendToUser("/queue/reply")
    public Greeting processMessageFromClient(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        System.err.println(message.getName());
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
}
