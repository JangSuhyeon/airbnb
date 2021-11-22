package com.jangsuhyun.airbnb.controller;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;

@Component
public class SocketHandler extends TextWebSocketHandler {

    HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); // 웹 소켓 세션 담아둘 해시맵

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //메시지 발송
        String msg = message.getPayload(); // 메시지에 담긴 텍스트값을 얻어올 수 있다.
        JSONObject obj = jsonToObjectParser(msg);
        for (String key : sessionMap.keySet()) {
            WebSocketSession wss = sessionMap.get(key);
            try {
                wss.sendMessage(new TextMessage(obj.toJSONString())); // sessionMap에 있는 모든 세션에 메시지 전송
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //소켓 연결 후
        super.afterConnectionEstablished(session);
        sessionMap.put(session.getId(), session); // 새롭게 연결된 세션을 sessionMap에 저장
        JSONObject obj = new JSONObject();
        obj.put("type", "getid");
        obj.put("sessionId", session.getId());
        session.sendMessage(new TextMessage(obj.toJSONString())); // 클라이언트 단에서 type값을 통해 메시지와 초기 설정값을 구분할 예정 ??
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //소켓 연결 종료 후
        sessionMap.remove(session.getId()); // 종료된 세션을 sessionMap에서 삭제
        super.afterConnectionClosed(session, status);
    }

    private static JSONObject jsonToObjectParser(String jsonStr) {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(jsonStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
