package com.example.serv;

/**
 * Created by Agneev on 22-04-2016.
 */
import com.example.server.vo.Content;

public class App {
    public static void main(String[] args) {

        System.out.println("Sending POST to GCM");

        String apiKey = "AIzaSyBuCBTT9SpNNDMjr3ebg9amo7-ts42LIA4";
        Content content = createContent();

        Post2Gcm.post(apiKey, content);
    }

    public static Content createContent() {
        Content c = new Content();

        c.addRegId("APA91bG4-Y90PEEE34P4mXS6qnH9KJs7fEAKV0tIpSQoxEGBdOmH-PzMFNRRLg1oqI7zuGdhnSVqmXyaAYKBqanbZpOranMLJmeSq86kpAQDoV18aA9iVEbq4Bo1noI68mkPyok8F3tS");

        c.createData("Server-Client Established!", "Test message");

        return c;
    }
}

