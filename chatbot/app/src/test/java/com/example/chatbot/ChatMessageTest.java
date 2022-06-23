package com.example.chatbot;

import junit.framework.TestCase;

public class ChatMessageTest extends TestCase {

    public void testGetContent() {
        ChatMessage a = new ChatMessage("Hi",true,true);
        assertEquals("Hi",a.getContent());
    }
}