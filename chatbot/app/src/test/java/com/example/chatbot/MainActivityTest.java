package com.example.chatbot;

import junit.framework.TestCase;

public class MainActivityTest extends TestCase {

    public void testSendMessage() {
        MainActivity a = new MainActivity();
        ApiReturn b = new ApiReturn(a);
        b.getWoeID("Dublin");
        b.getData();
        double temp = b.getTempMin(0);
        assertNotNull(temp);
    }
}