package com.dream.chitchat.socket;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.dream.chitchat.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class WebSocketHandler extends TextWebSocketHandler implements SubProtocolCapable {
    
    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
    private static Map<String, String> chat = new HashMap<>();
    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Server connection opened");
        sessions.add(session);
        
        TextMessage message = new TextMessage("[]");
        logger.info("Server sends: {}", message);
        for (Map.Entry mapElement : chat.entrySet()) {
            String value = (String)mapElement.getValue();
            session.sendMessage(new TextMessage(value));
        }
        //session.sendMessage(message);
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.info("Server connection closed: {}", status);
        sessions.remove(session);
    }
    
    @Scheduled(fixedRate = 10000)
    void sendPeriodicMessages() throws IOException {
//        for (WebSocketSession session : sessions) {
//            if (session.isOpen()) {
//                //String broadcast = "server periodic message " + LocalTime.now();
//                logger.info("Server sends: {}"+chat.values().toString());
//                session.sendMessage(new TextMessage(chat.values().toString()));
//            }
//        }
    }
    
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String request = message.getPayload();
        Message msg = new ObjectMapper().readValue(request,Message.class);
        msg.setId(msg.getMessage());
        chat.put(msg.getId(), new ObjectMapper().writeValueAsString(msg));
        logger.info("Server received: {}", request);
        for (WebSocketSession sess : sessions) {
            if (sess.isOpen()) {
                //String broadcast = "server periodic message " + LocalTime.now();
                logger.info("Server sends: {}"+new ObjectMapper().writeValueAsString(msg));
                sess.sendMessage(new TextMessage(new ObjectMapper().writeValueAsString(msg)));
            }
        }
    }
    
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        logger.info("Server transport error: {}", exception.getMessage());
    }
    
    @Override
    public List<String> getSubProtocols() {
        return Collections.singletonList("subprotocol.demo.websocket");
    }
}