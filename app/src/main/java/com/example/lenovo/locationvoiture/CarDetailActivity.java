package com.example.lenovo.locationvoiture;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.like.OnLikeListener;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import MenuClient.AvisFragment;
import MenuClient.CarDetFragment;
import MenuClient.DetailleTabAdapter;
import MenuClient.TabAdapter;

public class CarDetailActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView CarImage ;
    private Toolbar toolbar ;
    private LikeButton favoritButton ;
    private SharedPreferences sharedPreferences;
    private String matricule , id , name  ;
    private TabLayout tabLayout ;
    private ViewPager viewPager;

    private DetailleTabAdapter adapter ;

    private Button reserver ;

    private SharedPreferences preferences ;

    private  final String add_url = "http://aymenpsg.000webhostapp.com/location/add_favorit.php";

    private final String remove_url = "http://aymenpsg.000webhostapp.com/location/remove_favorit.php" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        preferences = getSharedPreferences("login" ,MODE_PRIVATE);
         id = preferences.getString("id" ,null);
         name = preferences.getString("name" ,null);



        tabLayout = findViewById(R.id.mytabdet);
        viewPager = findViewById(R.id.myviewpagerdet);

        CarImage = findViewById(R.id.image_det);

        reserver = findViewById(R.id.reserver);

        reserver.setOnClickListener(this);

        toolbar = findViewById(R.id.toolbar1);

        tabLayout = findViewById(R.id.mytabdet);

        viewPager = findViewById(R.id.myviewpagerdet);

        adapter = new DetailleTabAdapter(getSupportFragmentManager());





        favoritButton = findViewById(R.id.favoritbtn);
        sharedPreferences = getSharedPreferences("favorit_stat" , MODE_PRIVATE);



        Bundle bundle = getIntent().getExtras();

        String image = bundle.getString("image");
        matricule = bundle.getString("matricule");
        String marque = bundle.getString("marque");
        String modele = bundle.getString("modele");

        adapter.AddTab(new CarDetFragment() , "Description");
        adapter.AddTab(new AvisFragment(matricule , id , name), "AVIS");


        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);


        favoritButton.setLiked(sharedPreferences.getBoolean(matricule ,false ));

        setSupportActionBar(toolbar);
       getSupportActionBar().setTitle(marque + " " + modele);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Glide.with(this).load(image).override(700 , 150 ).into(CarImage);



        FavoritButtonAction();

    }

    public void FavoritButtonAction(){

        final SharedPreferences.Editor editor = sharedPreferences.edit();



        favoritButton.setOnLikeListener(new OnLikeListener() {


            @Override
            public void liked(LikeButton likeButton) {

                editor.putBoolean(matricule , true);
                editor.commit();

                AddToFavorit(id , matricule);

            }

            @Override
            public void unLiked(LikeButton likeButton) {

                editor.putBoolean(matricule , false);
                editor.commit();

                RemoveFromFavorit(id, matricule);

            }
        });



    }


    public void AddToFavorit(final String id  ,final  String matricule){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, add_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(CarDetailActivity.this, response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CarDetailActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String > params = new HashMap<>();

                params.put("user_id",id);

                params.put("car_mat" , matricule);

                return  params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    public void RemoveFromFavorit(final String id  ,final  String matricule){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, remove_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(CarDetailActivity.this, response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CarDetailActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String > params = new HashMap<>();

                params.put("user_id",id);

                params.put("car_mat" , matricule);

                return  params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View view) {

        Bundle bundle = new Bundle() ;

        bundle.putString("id" , id);
        bundle.putString("mat" , matricule);

        Intent intent = new Intent(this , ReserverActivity.class) ;

        intent.putExtras(bundle);

        startActivity(intent);

    }
}
