package com.lana.svet.my_firstapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class User {

    private static String log = "debage";

    private String name;
    private String age;
    private String avatar;
    private String status;
    private String unreadMessages;
    private String similarity;
    private String lastSeen;

    User(String name, String age, String avatar, String status,
         String unreadMessages, String similarity, String lastSeen){
        this.name = name;
        this.age = age;
        this.avatar = avatar;
        this.status = status;;
        this.unreadMessages = unreadMessages;
        this.similarity = similarity;
        this.lastSeen = lastSeen;
    }

    public String getName(){
        return this.name;
    }
    public String getAge(){
        return this.age;
    }
    public String getAvatar(){
        return this.avatar;
    }
    public String getStatus(){
        return this.status;
    }
    public String getUnreadMessages(){
        return this.unreadMessages;
    }
    public String getSimilarity(){
        return this.similarity;
    }
    public String getLastSeen(){
        return this.lastSeen;
    }

    public static List<User> getUserList(String dataInLine){
        List<User> users = new ArrayList<User>();

        //инициируем запрос данных к серверу
/*               try {
            myThread.join();
        }catch (InterruptedException err3){
            Log.d(log, "не дождались ответа RequestDataFromServ");
        }
        String dataInLine = dataUsers.getRequest();
        Log.d(log,"От сервера получили: " + dataInLine);
*/
        //разбираем полученные данные на элементы
        try {
            JSONArray array = new JSONArray(dataInLine);
            for(int i = 0; i<array.length(); i++){
                JSONObject _userdata = array.getJSONObject(i);
                String _name = _userdata.getString("name");
                String _age = _userdata.getString("age");
                String _avatar = _userdata.getString("avatar");
                String _status = _userdata.getString("status");
                String _unreadMessages= _userdata.getString("unreadMessages");
                String _similarity = _userdata.getString("similarity");
                String _lastSeen = _userdata.getString("lastSeen");
                User user = new User(_name,_age,_avatar,_status,_unreadMessages,_similarity,_lastSeen);

                users.add(user); //добавляем новый элемент в arraylist
            }
        }catch (JSONException e){
            Log.d(log,"json array не создается");
        }

        return users;
    }

}
