package com.jangsuhyun.airbnb.controller;

import com.jangsuhyun.airbnb.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ChatController {

    List<ChatRoom> chatRoomList = new ArrayList<ChatRoom>();
    static int roomNumber = 0;

    // 채팅방으로 가기
    @GetMapping("/chat")
    public String goToChat() {
        return "chat/chat";
    }

    // 채팅방 목록으로 가기
    @GetMapping("/chatRoom")
    public String goToChatRoom() {
        return "chat/room";
    }

    // 채팅방 생성하기
    @RequestMapping("/createRoom")
    public @ResponseBody
    List<ChatRoom> createChatRoom(@RequestParam HashMap<Object, Object> params) {
        String roomName = (String) params.get("roomName");
        if (roomName != null && !roomName.trim().equals("")) {
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setRoomNumber(++roomNumber);
            chatRoom.setRoomName(roomName);
            chatRoomList.add(chatRoom);
        }
        return chatRoomList;
    }

    // 채팅방 정보 가져오기
    @RequestMapping("/getRoom")
    public @ResponseBody
    List<ChatRoom> getRoom(@RequestParam HashMap<Object, Object> params) {
        return chatRoomList;
    }

    // 채팅방
    @RequestMapping("/moveChating")
    public String chating(@RequestParam HashMap<Object, Object> params, Model model) {
        int roomNumber = Integer.parseInt((String) params.get("roomNumber"));

        List<ChatRoom> new_list = chatRoomList.stream().filter(o->o.getRoomNumber()==roomNumber).collect(Collectors.toList());
        if (new_list != null && new_list.size() > 0) {
            model.addAttribute("roomName", params.get("roomName"));
            model.addAttribute("roomNumber", params.get("roomNumber"));
            return "chat/chat";
        }else{
            return "chat/room";
        }
    }
}
