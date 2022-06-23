package com.example.chatbot;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChatMessageTest {

    ChatMessage msg = new ChatMessage("Hi",true,false);

    @Test
    public void getContent() {
        assertEquals("Hi",msg.getContent());
    }

    @Test
    public void isMine() {
    }

    @Test
    public void isImage() {
    }
}