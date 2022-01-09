package com.dream.chitchat.service;

import java.util.Collection;
import com.dream.chitchat.model.Message;

public interface MessageService {
   public abstract void createMessage(Message product);
   public abstract void updateMessage(String id, Message product);
   public abstract void deleteMessage(String id);
   public abstract Collection<Message> getMessages();
}