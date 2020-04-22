package com.example.lenovo.locationvoiture;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ReserverActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText date_debut , date_fin ;

    private Button reserver ;

    private DatePickerDialog datePickerDialog ;

    private  String id , matricule , DateD , DateF ;

    private AlertDialog.Builder builder;

    private final String url = "http://aymenpsg.000webhostapp.com/location/add_reservation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserver);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Reserver cette voiture");




        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#39a33e")));

        builder = new AlertDialog.Builder(this);

        date_debut = findViewById(R.id.date_debut);

        date_fin = findViewById(R.id.date_fin) ;

        reserver = findViewById(R.id.reservefinal);

        Intent intent = getIntent() ;

        id = intent.getExtras().getString("id");
        matricule = intent.getExtras().getString("mat");

        date_debut.setOnClickListener(this);
        date_fin.setOnClickListener(this);
        reserver.setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {

        if(view == findViewById(R.id.date_debut)){

            Calendar calendar = Calendar.getInstance() ;

            int day = calendar.get(Calendar.DAY_OF_MONTH) ;

            int month = calendar.get(Calendar.MONTH);

            int year = calendar.get(Calendar.YEAR);

            datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {


                        DateD = year+"-"+(month+1)+"-"+day ;
                        date_debut.setText(DateD);


                }
            },year , month, day);

            datePickerDialog.show();


        }

        if(view == findViewById(R.id.date_fin)){

            Calendar calendar = Calendar.getInstance() ;

            int day = calendar.get(Calendar.DAY_OF_MONTH) ;

            int month = calendar.get(Calendar.MONTH);

            int year = calendar.get(Calendar.YEAR);

            datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                    DateF = year+"-"+(month+1)+"-"+day;
                    date_fin.setText(DateF);


                }
            },year , month, day);



            datePickerDialog.show();


        }

        else if(view == findViewById(R.id.reservefinal)){


            ReserverVoiture();

        }

    }

    public void ReserverVoiture(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

             builder.setTitle("merci pour votre fidélité") ;
             builder.setMessage(response) ;
             builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {

                     startActivity(new Intent(getApplicationContext() , ClientMenuActivity.class));
                     finish();
                 }
             }) ;

             builder.show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ReserverActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String , String> paramas = new HashMap<>();

                paramas.put("user_id" , id);
                paramas.put("car_mat" , matricule);
                paramas.put("date_debut" , DateD) ;
                paramas.put("date_fin" , DateF);

                return paramas;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);

    }
}
