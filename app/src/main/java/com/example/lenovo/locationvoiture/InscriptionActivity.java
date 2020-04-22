package com.example.lenovo.locationvoiture;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InscriptionActivity extends AppCompatActivity implements View.OnClickListener {

        private EditText CompletName , UsernameInscr , TelephoneInsc , PassInscr , ConfirmPassInsc;
        private Button submit ;
        private AlertDialog dialog ;
        private final String url ="http://aymenpsg.000webhostapp.com/location/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        CompletName = findViewById(R.id.completname);
        UsernameInscr = findViewById(R.id.usernameinsc);
        TelephoneInsc = findViewById(R.id.telephoneinsc);
        PassInscr = findViewById(R.id.passinsc);
        ConfirmPassInsc = findViewById(R.id.passconfirminscr);
        submit = findViewById(R.id.submit);
        dialog = new AlertDialog.Builder(this).create();
        submit.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F4420A")));

        getSupportActionBar().setTitle("Page d'inscription : ");
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity.class));
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        String Cname = CompletName.getText().toString();
        String username = UsernameInscr.getText().toString();
        String telephone = TelephoneInsc.getText().toString();
        String password = PassInscr.getText().toString();
        String Cpassword = ConfirmPassInsc.getText().toString();

        CheckInscription(Cname , username , telephone , password , Cpassword);

    }

    public void CheckInscription(final String Cname , final String username , final String telephone , final String password , String Cpassword ){

        if(Cname.trim().isEmpty() || username.trim().isEmpty() ||telephone.trim().isEmpty() ||password.trim().isEmpty() ||Cpassword.trim().isEmpty()){

            Toast.makeText(this, "il faut remplir tout les champs ", Toast.LENGTH_SHORT).show();
        }

        else{


            if(!password.trim().equals(Cpassword.trim())){

                dialog.setTitle("Erreur au niveau de mot de passe");
                dialog.setMessage("confimer bien votre mot de passe ");
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PassInscr.setText("");
                        ConfirmPassInsc.setText("");
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }

            else{

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");
                            String message = jsonObject.getString("message");

                            if(!code.equals("complet")){

                                dialog.setTitle("erreur !!");
                                dialog.setMessage(message);
                                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialog.dismiss();
                                    }
                                });

                                dialog.show();


                            }

                            else{

                                dialog.setTitle("Merci  :) ");
                                dialog.setMessage(message);
                                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startActivity(new Intent(InscriptionActivity.this , MainActivity.class));
                                        finish();
                                    }
                                });

                                dialog.show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(InscriptionActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(InscriptionActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String , String > params = new HashMap<>();

                        params.put("user_name" , username ) ;
                        params.put("user_pass" , password);
                        params.put("complet_name" , Cname);
                        params.put("telephone" , telephone);

                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

            }
        }


    }


}
