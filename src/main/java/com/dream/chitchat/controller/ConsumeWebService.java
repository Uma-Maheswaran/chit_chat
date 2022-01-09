package com.dream.chitchat.controller;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dream.chitchat.model.Message;

@RestController
public class ConsumeWebService {
   @Autowired
   private RestTemplate restTemplate;


   @RequestMapping(value = "/template/messages")
   public String getMessageList() {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<String> entity = new HttpEntity<String>(headers);
      
      return restTemplate.exchange(
         "https://chit-chat-115.herokuapp.com/messages", HttpMethod.GET, entity, String.class).getBody();
   }
   @RequestMapping(value = "/template/messages", method = RequestMethod.POST)
   public String createMessages(@RequestBody Message message) {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<Message> entity = new HttpEntity<Message>(message,headers);
      
      return restTemplate.exchange(
         "https://chit-chat-115.herokuapp.com/messages", HttpMethod.POST, entity, String.class).getBody();
   }
   @RequestMapping(value = "/template/messages/{id}", method = RequestMethod.PUT)
   public String updateMessage(@PathVariable("id") String id, @RequestBody Message message) {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<Message> entity = new HttpEntity<Message>(message,headers);
      
      return restTemplate.exchange(
         "https://chit-chat-115.herokuapp.com/messages/"+id, HttpMethod.PUT, entity, String.class).getBody();
   }
   @RequestMapping(value = "/template/messages/{id}", method = RequestMethod.DELETE)
   public String deleteMessage(@PathVariable("id") String id) {
      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<Message> entity = new HttpEntity<Message>(headers);
      
      return restTemplate.exchange(
         "https://chit-chat-115.herokuapp.com/messages/"+id, HttpMethod.DELETE, entity, String.class).getBody();
   }
}