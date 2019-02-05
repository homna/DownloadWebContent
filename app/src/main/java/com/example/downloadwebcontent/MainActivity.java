package com.example.downloadwebcontent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadWebSite downloadWebSite = new DownloadWebSite();
        String output = "";
        try {
            output = downloadWebSite.execute(new String[]{"https://zappycode.com", "http://www.browxy.com"}).get();
        } catch (Exception e) {
            Log.i("Problem2", e.getMessage());
        }
        Log.i("HTML", output);
    }

    public class DownloadWebSite extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder result = new StringBuilder();
            result.append("");
            for (String string : strings) {
                Log.i("HTML", "Downloading ...." + string);
                try {
                    URL url = new URL(string);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    //First Solution
                    int read = inputStreamReader.read();
                    while (read != -1) {
                        char character = (char) read;
                        result.append(character);

                        read = inputStreamReader.read();
                    }
                } catch (Exception e) {
                    Log.i("Problem1", e.getMessage());
                }
            }
            return result.toString();
        }
    }
}
