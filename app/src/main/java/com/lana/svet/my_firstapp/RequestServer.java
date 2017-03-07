package com.lana.svet.my_firstapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*в отдельном потоке получаем данные (request) из Инета  */
public class RequestServer implements Runnable {

    private final String PATH;
    private String request = "";
    private final String deb = "debage";

    RequestServer(String url){
        this.PATH = url;
    }

    public String getRequest (){
        return this.request;
    }

    @Override
    public void run(){
        URL url = null;
        HttpURLConnection urlConnection;
        BufferedReader in;
        try {
            url = new URL(PATH);
        }catch (MalformedURLException err1){
            Log.d(deb,"URL содержит ошибки");
        }
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                int c = 0;
                while ((c = in.read())!=-1)
                    request += (char)c;
            }
        }catch (IOException err1){
            Log.d(deb,"ошибка при чтении данных с потока");
        }
    }

}
