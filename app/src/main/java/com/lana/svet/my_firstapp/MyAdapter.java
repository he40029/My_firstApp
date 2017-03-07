package com.lana.svet.my_firstapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    List<User> data = new ArrayList<User>();
    Context context;

    public MyAdapter(Context context, List<User> users) {
        if (users != null) {
            data = users;
        }
        this.context = context;
    }
    //количество элементов
    @Override
    public int getCount() {
        return data.size();
    }

    //элемент по позиции
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    //id по позиции
    @Override
    public long getItemId(int arg1) {
        return arg1;
    }
    //элемент
    @Override
    public View getView(int position, View someView, ViewGroup arg2) {
        //Получение объекта inflater из контекста
        LayoutInflater inflater = LayoutInflater.from(context);
        //Если someView (View из ListView) вдруг оказался равен
        //null тогда мы загружаем его с помошью inflater
        if (someView == null)
            someView = inflater.inflate(R.layout.listview_item, arg2, false);

        //получаем картинку
        /*DownloadImage _imgAva = new DownloadImage(data.get(position).getAvatar());
        Thread dLoadImg = new Thread(_imgAva);
        dLoadImg.start();
        try {
            dLoadImg.join();
        } catch (InterruptedException e) {
            Log.d("debag","Недождались ответ из потока");
        }*/
        new DownloadImageTask(data.get(position).getAvatar(), someView).execute();


       /* ImageView i = (ImageView) someView.findViewById(R.id.image_avatar);
        i.setImageBitmap(_imgAva.getImage());*/

        //Обявляем объекты с текстом и связываем их с разметкой
        TextView name = (TextView) someView.findViewById(R.id.item_name);
        TextView age = (TextView) someView.findViewById(R.id.item_age);
        TextView similarity = (TextView) someView.findViewById(R.id.item_similarity);
        TextView lastSeen = (TextView) someView.findViewById(R.id.item_lastSeen);
        TextView unreadMessages = (TextView) someView.findViewById(R.id.item_unreadMessages);
        View status = (View) someView.findViewById(R.id.item_status);

        //colorSimilarity
        similarity.setTextColor (Color.parseColor("#d2442b"));

        //Устанавливаем соответствующий цвет иконки статуса
        if ("online".equals(data.get(position).getStatus()) )
            status.setBackgroundResource(R.drawable.status_online);
        if ("away".equals(data.get(position).getStatus()) )
            status.setBackgroundResource(R.drawable.status_away);
        if ("dnd".equals(data.get(position).getStatus()) )
            status.setBackgroundResource(R.drawable.status_dnd);

        //Устанавливаем соответствующий текст
        name.setText(data.get(position).getName());
        age.setText(data.get(position).getAge() + " лет, ");
        similarity.setText(data.get(position).getSimilarity() + "%");
        lastSeen.setText(data.get(position).getLastSeen());
        unreadMessages.setText(data.get(position).getUnreadMessages());

        return someView;
    }
}

