package com.lana.svet.my_firstapp;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.ListView;
        import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String PATH =  "https://a11d.firebaseio.com/users.json";
    private RequestServer requestServer;
    private static String log = "debage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView lv = (ListView)findViewById(R.id.listview);

        /*получение данных*/

        //  инициируем запрос данных к серверу

        requestServer = new RequestServer(PATH);
        Thread myThreadRequestServer = new Thread(requestServer);
        myThreadRequestServer.start();
        try {
            myThreadRequestServer.join();
        }catch (InterruptedException e){
            Log.d(log, "не дождались ответа RequestDataFromServ");
        }
        //формируем полученные данные
        List<User> users = User.getUserList(requestServer.getRequest());
        //создаем список
        lv.setAdapter(new MyAdapter(this, users));



    }

}
