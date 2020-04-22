package com.example.lenovo.locationvoiture;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Collection;

import MenuClient.ConsulterVoiture;
import MenuClient.MakeComparison;
import MenuClient.MesFavoris;
import MenuClient.MesVoiture;

public class ClientMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView myName , myuUername ;

    private SharedPreferences preferences , preferencesFavorit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Mon Menu ");
        Fragment  fragment = new ConsulterVoiture();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment ,fragment).commit();
        preferences = getSharedPreferences("login" ,MODE_PRIVATE);
        preferencesFavorit = getSharedPreferences("favorit_stat" , MODE_PRIVATE);
        String username = preferences.getString("username" ,null);
        String name = preferences.getString("name" , null);





        setSupportActionBar(toolbar);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#54915f")));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        myuUername = view.findViewById(R.id.ownusername);
        myName = view.findViewById(R.id.mycompletename);
        myuUername.setText("@"+username);
        myName.setText(name);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
                finish();
            super.onBackPressed();
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment ;

        switch (id){

            case R.id.consultervoiture :
                fragment = new ConsulterVoiture();
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment ,fragment).commit();
                break;

            case R.id.mesfavoris :
                fragment = new MesFavoris();
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment ,fragment).commit();
                break;

            case R.id.mesvoiture :
                fragment = new MesVoiture();
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment ,fragment).commit();
                break;

            case R.id.comparaison :
                fragment = new MakeComparison();
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment ,fragment).commit();
                break;

            case R.id.logout :

                preferences.edit().clear().commit();
                preferencesFavorit.edit().clear().commit();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
