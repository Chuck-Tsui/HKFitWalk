package com.example.hkfitwalk;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvDistrict;
    private TextView tvRoute;
    private TextView tvHowToAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tv_title);
        tvDistrict = findViewById(R.id.tv_district);
        tvRoute = findViewById(R.id.tv_route);
        tvHowToAccess = findViewById(R.id.tv_how_to_access);

        String jsonString = loadJSONFromAsset("fitness_track.json");
        parseJsonAndDisplayData(jsonString);
    }

    private String loadJSONFromAsset(String fileName) {
        String json;
        try {
            InputStream inputStream = getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void parseJsonAndDisplayData(String jsonString) {
        if (jsonString == null) {
            Log.e("MainActivity", "JSON content is null");
            return;
        }
        Gson gson = new Gson();
        List<FitnessTrack> fitnessTracks = gson.fromJson(jsonString, new TypeToken<List<FitnessTrack>>(){}.getType());

        // Assuming you want to display the data of the first FitnessTrack in the list
        FitnessTrack fitnessTrack = fitnessTracks.get(0);

        tvTitle.setText(fitnessTrack.getTitle_en());
        tvDistrict.setText(fitnessTrack.getDistrict_en());
        tvRoute.setText(fitnessTrack.getRoute_en());
        tvHowToAccess.setText(fitnessTrack.getHowToAccess_en());
    }

    }


