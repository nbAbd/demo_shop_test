package com.example.demoshop.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class DataHelper {
    private Context mContext;

    public DataHelper(Context context) {
        this.mContext = context;
    }

    public String getJsonFromAssets(String fileName) {
        String jsonString;
        try {

            InputStream inputStream = mContext.getAssets().open(fileName);

            int size = inputStream.available();
            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();

            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }
}
