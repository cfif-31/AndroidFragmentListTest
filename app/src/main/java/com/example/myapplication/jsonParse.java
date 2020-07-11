package com.example.myapplication;

import android.util.JsonReader;
import android.widget.TextView;

import com.example.myapplication.ui.notifications.NotificationsFragment;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class jsonParse {
    private static HttpURLConnection getConnection() throws IOException {
        URL url = new URL("http://10.0.2.2/testapi.com/product/readList.php");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.connect();
        return conn;
    }
    public static ArrayList<UserInfo> getListUsers() throws IOException {
        HttpURLConnection conn = getConnection();
        InputStream stream = conn.getInputStream();
        JsonReader reader = new JsonReader(new InputStreamReader(stream));
        reader.beginArray();
        ArrayList<UserInfo> list = new ArrayList<UserInfo>();
        while(reader.hasNext()) {
            list.add(UserInfo.parseJson(reader));
        }
        return list;
    }



}
