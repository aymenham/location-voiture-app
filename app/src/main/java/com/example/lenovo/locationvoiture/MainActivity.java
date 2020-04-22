package com.example.lenovo.locationvoiture;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Username , Password;
    private Button LoginBtn ;
    private TextView InscreptionBtn;
    private EditText mUsername , mPassword;
    private AlertDialog  builder;
    private SharedPreferences preferences;
    private  final String url="http://aymenpsg.000webhostapp.com/location/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = findViewById(R.id.myusername);

        Password = findViewById(R.id.mypass);

        LoginBtn =  findViewById(R.id.mylogin);

        mUsername = findViewById(R.id.myusername) ;

        mPassword = findViewById(R.id.mypass) ;

        builder = new AlertDialog.Builder(this).create();

        preferences = getSharedPreferences("login"  , MODE_PRIVATE);

        if(preferences.contains("username")){

            startActivity(new Intent(this,ClientMenuActivity.class));
            finish();
        }

        LoginBtn.setOnClickListener(this);

        InscreptionBtn = findViewById(R.id.myinscr);

        InscreptionBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent ClickResult;

        if(view==LoginBtn){
            String username = Username.getText().toString().trim();
            String password = Password.getText().toString().trim();
            this.CheckLogin(username, password);
            //startActivity(new Intent(this,ClientMenuActivity.class));
        }

        else if(view==InscreptionBtn){
            ClickResult = new Intent(this,InscriptionActivity.class);
            startActivity(ClickResult);

        }

    }

    public void CheckLogin(final String username , final String password){

        if(username.trim().isEmpty() || password.trim().isEmpty()){

            Toast.makeText(this, "il faut remplir tout les champs ", Toast.LENGTH_SHORT).show();

        }

        else{

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONObject jsonObject;
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                         jsonObject = jsonArray.getJSONObject(0);
                        String code = jsonObject.getString("code");
                        String name = jsonObject.getString("nom");
                        String id = jsonObject.getString("id");
                        if(code.equals("login failed")){

                            builder.setTitle("Erreur !!");
                            builder.setMessage("username ou mot de passe incorrecte");
                            builder.setButton(AlertDialog.BUTTON_NEUTRAL, "OK ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    builder.dismiss();
                                }
                            });
                            builder.show();

                        }

                        else {

                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("username" , username);
                            editor.putString("name" , name);
                            editor.putString("id" ,id);
                            editor.commit();
                            startActivity(new Intent(MainActivity.this,ClientMenuActivity.class));
                            finish();


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String , String > params = new HashMap<>();
                    params.put("user_name" ,username);
                    params.put("user_pass" ,password);
                    return  params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
}
