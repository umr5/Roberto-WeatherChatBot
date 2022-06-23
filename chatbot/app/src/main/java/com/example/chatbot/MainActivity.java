package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private FloatingActionButton mButtonSend;
    private EditText mEditTextMessage;
    private ImageView mImageView;
    private ChatMessageAdapter mAdapter;

    //make separate class for both and call one by one

    ApiReturn ans = new ApiReturn(this);
    String[] cityName = new String[2];
    String[] days = new String[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        mButtonSend = (FloatingActionButton) findViewById(R.id.btn_send);
        mEditTextMessage = (EditText) findViewById(R.id.et_message);
        mImageView = (ImageView) findViewById(R.id.iv_image);
        mAdapter = new ChatMessageAdapter(this, new ArrayList<ChatMessage>());
        mListView.setAdapter(mAdapter);
        mimicOtherMessage("Hi, I'm Roberto your personal travel weather assistant!. How are you today?");


//code for sending the message
        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEditTextMessage.getText().toString();
                if(message.contains("going to")){
                    cityName = message.split("to ");
                }if(message.contains("days")){
                    days = message.split(" ");
                }
                sendMessage(message);
                mEditTextMessage.setText("");

                mListView.setSelection(mAdapter.getCount() - 1);

            }
        });


    }

    public void sendMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, true, false);
        mAdapter.add(chatMessage);
        if (message.contains("HI".toLowerCase())) {
            mimicOtherMessage("Hi, I'm Roberto your personal travel weather assistant!. How are you today?");
            mimicOtherMessage("What city are you visiting?");
        } else if (message.contains("hi,how are you?".toLowerCase(Locale.ROOT)) || message.contains("how")) {
            mimicOtherMessage("I'm good, thank you for asking!");
            mimicOtherMessage("Should we start?");
            mimicOtherMessage("What is the city that you are visiting?");
        } else if (message.contains("I'm good thank you") || message.contains("good") || message.contains("great")|| message.contains("not too bad")) {
            mimicOtherMessage("Awesome!");
            mimicOtherMessage("Should we start?");
            mimicOtherMessage("What city are you visiting?");
        }else if (message.contains("going")){
            if (cityName != null){
                ans.getWoeID(cityName[1]);
            }
            mimicOtherMessage("Oh thats a beautiful place!");
            mimicOtherMessage("Would you like to know the weather for " + cityName[1] + "?");

        }else if(message.contains("yes")){
            ans.getData();
            mimicOtherMessage("How long will your trip be?");
        }else if (message.contains("days")) {
            if (Integer.parseInt(days[0]) < 7) {
                for (int i = 0; i < Integer.parseInt(days[0]); i++) {
                    mimicOtherMessage("The minimum temperature for day " + (i + 1) + " will be " + ans.getTempMin(i) + "°C and the maximum temperature will be " + ans.getTempMax(i) + "°C and there will be " + ans.state(i));
                    double minTemp = ans.getTempMin(i);
                    String state = ans.state(i);
                    if (minTemp < 5.0 && (state.equals("Snow") || state.equals("Sleet"))) {
                        mimicOtherMessage("Wear a warm jacket with warm inner layers.");
                    } else if (minTemp < 5.0 && (state.equals("Heavy Rain") || state.equals("Light Rain"))) {
                        mimicOtherMessage("Wear a warm waterproof jacket and a warm inner.");
                    } else if ((minTemp > 5.0 && minTemp < 15.0) && (state.equals("Heavy Rain") || state.equals("Light Rain") || state.equals("Sleet"))) {
                        mimicOtherMessage("Wear a warm waterproof jacket.");
                    } else if ((minTemp > 15) && (state.equals("Heavy Rain") || state.equals("Light Rain"))) {
                        mimicOtherMessage("Wear a light waterproof jacket.");
                    } else if (state.equals("Showers")) {
                        mimicOtherMessage("Wear water resistant clothing.");
                    } else if (minTemp < 10.0) {
                        mimicOtherMessage("Wear a hoodie or light jacket");
                    } else if (minTemp < 5) {
                        mimicOtherMessage("Wear a warm jacket");
                    } else {
                        mimicOtherMessage("Wear T-Shirt and Shorts.");
                    }
                }
            }else{
                mimicOtherMessage("Forecast available for the next 6 days only :(");
            }
        }else if (message.contains("thanks") || message.contains("thank you")){
            mimicOtherMessage("It was a pleasure to assist you, Enjoy your trip!");
        }
        else{
                mimicOtherMessage("Sorry, I could not understand what you want to say...");
                mimicOtherMessage("Maybe tell me the destination of you next trip, that I will love to help!");
            }
        }


    private void mimicOtherMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, false, false);
        mAdapter.add(chatMessage);

    }

    private void sendMessage() {
        ChatMessage chatMessage = new ChatMessage(null, true, true);
        mAdapter.add(chatMessage);

        mimicOtherMessage();
    }

    private void mimicOtherMessage() {
        ChatMessage chatMessage = new ChatMessage(null, false, true);
        mAdapter.add(chatMessage);
    }
}