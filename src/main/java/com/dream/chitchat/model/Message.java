package com.dream.chitchat.model;

import java.sql.Timestamp;

public class Message {
private String id;
private String user;
private Timestamp time;
private String message;

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getUser() {
	return user;
}
public void setUser(String user) {
	this.user = user;
}
public Timestamp getTime() {
	return time;
}
public void setTime(Timestamp time) {
	this.time = time;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
}
