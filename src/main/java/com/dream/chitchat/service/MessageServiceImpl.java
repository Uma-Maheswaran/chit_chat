package com.dream.chitchat.service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.dream.chitchat.model.Message;

@Service
public class MessageServiceImpl implements MessageService {
   private static Map<String, Message> chat = new HashMap<>();
   static {
	  long now = System.currentTimeMillis();
      Timestamp sqlTimestamp = new Timestamp(now);
	  
      Message msg1 = new Message();
      msg1.setId("1");
      msg1.setUser("user1");
      msg1.setTime(sqlTimestamp);
      msg1.setMessage("Hi dude");
      chat.put(msg1.getId(), msg1);

      Message msg2 = new Message();
      msg2.setId("1");
      msg2.setUser("user2");
      msg2.setTime(sqlTimestamp);
      msg2.setMessage("Welcome to chit chat");
      chat.put(msg2.getId(), msg2);
   }
   @Override
   public void createMessage(Message message) {
      chat.put(message.getId(), message);
   }
   @Override
   public void updateMessage(String id, Message message) {
      chat.remove(id);
      message.setId(id);
      chat.put(id, message);
   }
   @Override
   public void deleteMessage(String id) {
      chat.remove(id);

   }
   @Override
   public Collection<Message> getMessages() {
      return chat.values();
   }
}