package com.example.next.simpleweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Student on 4/22/2017.
 */

public class Database_Activity extends AppCompatActivity {
DBholder dbh;
   public static City_database c1;
    public static City_database c2;
   public static City_database c3;
    EditText et1;
    EditText et2;
    EditText et3;
    public static String  a1;
    public static String a2;
    public static String a3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_activity);

        et1=(EditText)findViewById(R.id.editText1);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);
        dbh=new DBholder(this,null,null,1);
      dbh.send_city();
     check();

    }

    public void send_to_main(City_database c11,City_database c22,City_database c33){

        c1=new City_database(c11);
        c2=new City_database(c22);
        c3=new City_database(c33);


a1=c1.getCity();
        a2=c2.getCity();
        a3=c3.getCity();
    }
    public void check(){

        et1.setText(a1);
        et2.setText(a2);
        et3.setText(a3);
    }


    public void onClick1(View view) {
        dbh.update_city(c1,et1.getText().toString());

    }

    public void onClick2(View view) {
        dbh.update_city(c2,et2.getText().toString());
    }

    public void onClick3(View view) {
        dbh.update_city(c3,et3.getText().toString());
    }

    public void onUpdate(View view) {
        Intent intent =new Intent(this,MainActivity.class);

        startActivity(intent);
    }
    public void onBack(View view)
    {
        Intent intent =new Intent(this,MainActivity.class);

        startActivity(intent);
    }
}
