package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import org.json.*;
import java.net.MalformedURLException;
import java.util.Arrays;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private FloatingActionButton mButtonSend;
    private EditText mEditTextMessage;
    private ImageView mImageView;
    private ChatMessageAdapter mAdapter;


    double data[][] = new double[6][2];
    String[] state = new String[6];

    {
        try {
            APIConnector getWoeId = new APIConnector("https://www.metaweather.com/api/location/search/?query=");
            String city = "Dublin";
            JSONArray cityData = getWoeId.getJSONArray(city);
            int woeID = cityData.getJSONObject(0).getInt("woeid");
            String query = String.valueOf(woeID);
            APIConnector getForecast = new APIConnector("https://www.metaweather.com/api/location/");
            JSONObject thing = getForecast.getJSONObject(query);



            for (int i = 0; i < data.length; i++){
                data[i][0] = thing.getJSONArray("consolidated_weather").getJSONObject(i).getDouble("min_temp");
                data[i][1] = thing.getJSONArray("consolidated_weather").getJSONObject(i).getDouble("max_temp");

            }

            for (int i = 0; i < state.length; i++){
                state[i] = thing.getJSONArray("consolidated_weather").getJSONObject(i).getString("weather_state_name");
            }
            for (int i = 0; i < data.length; i++){
                //System.out.println(Arrays.toString(data[i]));
            }
            //System.out.println(Arrays.toString(state));

        } catch (MalformedURLException | JSONException e) {
            e.printStackTrace();
        }
    }




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

//code for sending the message
        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEditTextMessage.getText().toString();
                sendMessage(message);
                mEditTextMessage.setText("");
                mListView.setSelection(mAdapter.getCount() - 1);
            }
        });
    }

    private void sendMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, true, false);
        mAdapter.add(chatMessage);
        //respond as Helloworld
        mimicOtherMessage("kkkkk");
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