package com.jangsuhyun.airbnb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ChatController {

    @GetMapping("/chat")
    public String goToChat() {
        return "chat/chat";
    }

}
