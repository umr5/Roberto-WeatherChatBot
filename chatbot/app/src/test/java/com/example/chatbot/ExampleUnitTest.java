package com.example.chatbot;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    //creating instance of class ChatMessage
    ChatMessage b = new ChatMessage("",true, true);

    //assertNotNull used as each message would be different
    @Test
    public void testChatMessage() {
        assertNotNull(b.getContent());
    }

    @Test
    public void testChatMessage2() {
        assertNotNull(b.isMine());
    }

    @Test
    public void testChatMessage3() {
        assertNotNull(b.isImage());
    }

}