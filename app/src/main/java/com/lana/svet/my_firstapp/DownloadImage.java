package com.lana.svet.my_firstapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
/*загружается картинка с url*/

public class DownloadImage implements Runnable{

    private String url;
    private Bitmap image;

    DownloadImage(String imageUrl){
        this.url = imageUrl;
    }

    public Bitmap getImage(){
        return this.image;
    }

    @Override
    public void run(){
        try {
            image = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
        }catch (MalformedURLException err1){
            Log.d("debag","Картинка не грузится...");
        } catch (IOException e) {
            Log.d("debag","Картинка не грузится... I/O");
        }
    }


}

