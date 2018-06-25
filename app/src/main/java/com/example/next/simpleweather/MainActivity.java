package com.example.next.simpleweather;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.Window;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
   Intent intent;
    NavigationView navigationView;
    public static String city[]=new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

         navigationView = (NavigationView) findViewById(R.id.nav_view);

     DBholder dBholder=new DBholder(this,null,null,1);

       city= dBholder.send_to_main();

        Menu m = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(this);
        SubMenu Cities=m.addSubMenu("Favourites");
        Cities.add(0,0,1,city[0]);
        Cities.getItem(0).setIcon(R.drawable.ic_home_black_24dp);
        Cities.add(0,1,2,city[1]);
        Cities.getItem(1).setIcon(R.drawable.ic_business_black_24dp);
        Cities.add(0,2,3,city[2]);
        Cities.getItem(2).setIcon(R.drawable.ic_explore_black_24dp);
        Cities.add(0,3,4,"Settings");
        Cities.getItem(3).setIcon(R.drawable.ic_menu_manage);
        SubMenu Com=m.addSubMenu("Communication");
        Com.add(1,4,1,"Share");
        Com.getItem(0).setIcon(R.drawable.ic_menu_share);
       Com.add(1,5,2,"About us");
      Com.getItem(1).setIcon(R.drawable.ic_menu_send);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new WeatherFragment())
                    .commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.weather, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.change_city){
            showInputDialog();
        }
        return false;
    }

    private void showInputDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change city");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeCity(input.getText().toString());
            }
        });
        builder.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);
            if (!(mi.getItemId() == item.getItemId())) {
                mi.setCheckable(false);
            }
        }
       item.setCheckable(true);
        item.setChecked(true);
        int id = item.getItemId();

        switch (id) {
            case 0:
               changeCity(city[0]);
                break;

            case 1:
                changeCity(city[1]);

                break;
            case 2:
                changeCity(city[2]);
                break;
            case 3:
                intent=new Intent(this,Database_Activity.class);
                 startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeCity(String city){
        WeatherFragment wf = (WeatherFragment)getSupportFragmentManager()
                .findFragmentById(R.id.container);
        wf.changeCity(city);
        new CityPreference(this).setCity(city);
    }
    public void onBackPressed() {
        //  super.onBackPressed();
        moveTaskToBack(true);

    }

}
