package com.example.chatbot;

import org.json.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class APIConnector {

    private final String urlString;





    public APIConnector(String urlString) throws MalformedURLException {
        this.urlString = urlString;
    }

    public JSONObject getJSONObject(String query){
        try {
            URL url = new URL(urlString + query);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                String str = informationString.toString();
                JSONObject obj = new JSONObject(str);

                return obj;

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public JSONArray getJSONArray(String query){
        try {
            URL url = new URL(urlString + query);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                String str = informationString.toString();
                JSONArray obj = new JSONArray(str);

                return obj;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}