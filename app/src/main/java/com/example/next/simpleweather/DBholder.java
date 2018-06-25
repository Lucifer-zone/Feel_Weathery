package com.example.next.simpleweather;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;

public class DBholder extends SQLiteOpenHelper{

    private static final int database_version=1;
    private static final String database_name="Cities.db";
    private static final String table="Cities_table";
    private static final String columnn_id="id";
    private static final String column_city="city";

    public DBholder(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,database_name, factory, database_version);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query="create table "+table+"("+columnn_id+" integer primary key autoincrement,"+column_city+" text);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists"+table);
        onCreate(sqLiteDatabase);

    }

    public void addproducts(City_database city_database){
       ContentValues values=new ContentValues();
        values.put(column_city, city_database.getCity());
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.insert(table,null,values);
        sqLiteDatabase.close();

    }
    public void update_city(City_database city_database,String city)
    {
        ContentValues args = new ContentValues();
        args.put(columnn_id, city_database.getId());
        args.put(column_city, city);
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
      sqLiteDatabase.update(table,args,columnn_id + "=" + city_database.getId(),null);
sqLiteDatabase.close();

    }
    public void send_city(){
        int i=0;
        String total=" ";
        String show_column[]={columnn_id,column_city};
       City_database c[]=new City_database[3];
        for(int j=0;j<3;j++)
        {
            c[j]=new City_database(j," ");
        }


        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.query(table,show_column,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            c[i].setId(cursor.getInt(0));
            c[i].setCity(cursor.getString(1));

          i++;
        }

        Database_Activity database_activity=new Database_Activity();
       database_activity.send_to_main(c[0],c[1],c[2]);
        db.close();
    }
    public String[] send_to_main(){
        int i=0;
        String show_column[]={columnn_id,column_city};
        String array[]=new String[3];



        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.query(table,show_column,null,null,null,null,null);
        while(cursor.moveToNext()) {

            array[i] = cursor.getString(1);

            i++;
        }
        db.close();
        return array;

    }
}
