package com.lana.svet.my_firstapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cbetn on 06.03.2017.
 */

public class DownloadImageTask extends AsyncTask<Void, Void, Void> {

    private Bitmap image;
    private String url;
    private View sv;
    public DownloadImageTask(String url, View view) {
        this.url = url;
        sv = view;
    }
    @Override
    protected Void doInBackground(Void... params) {
        try {
            image = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
        }catch (MalformedURLException err1){
            Log.d("debag","Картинка не грузится...");
        } catch (IOException e) {
            Log.d("debag","Картинка не грузится... I/O");
        }        return null;
    }
    @Override
    protected void onPostExecute(Void result) {
        ImageView i = (ImageView) sv.findViewById(R.id.image_avatar);
        i.setImageBitmap(image);
    }
}
