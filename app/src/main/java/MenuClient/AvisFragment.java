package MenuClient;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.locationvoiture.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Myclass.Avis;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class AvisFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView ;
    
    private Button sendButton;
    
    private EditText commentText;

    private RecyclerView.Adapter adapter ;

    private List<Avis> avisList ;

    private final String url ="http://aymenpsg.000webhostapp.com/location/avisinformations.php";

    private final  String Send_url = "http://aymenpsg.000webhostapp.com/location/sendcomment.php";


    String matricule , id , name;


    @SuppressLint("ValidFragment")
    public AvisFragment(String matricule , String id , String name) {

        this.matricule = matricule ;
        this.name = name;
        this.id=id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_avis, container, false);

        recyclerView = view.findViewById(R.id.recyler_avis);
        
        sendButton = view.findViewById(R.id.send_commentaite);
        
        commentText = view.findViewById(R.id.laisse_commentaire);

        avisList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new AvisAdapter(avisList , getContext());

        recyclerView.setAdapter(adapter);
        
        Get_All_The_Information();
        
        sendButton.setOnClickListener(this);

        return view;
    }

    private void Get_All_The_Information() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0;i<jsonArray.length() ; i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String username = jsonObject.getString("nom");
                        String date = jsonObject.getString("comment_date");
                        String comment = jsonObject.getString("comment");
                        Avis avis = new Avis(username ,comment ,date);
                        avisList.add(avis);
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("car_mat" , matricule);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        requestQueue.add(stringRequest);

    }

    @Override
    public void onClick(View view) {
        
        SendComment();
    }

    private void SendComment() {

        if(commentText.getText().toString().trim().isEmpty()){

            Toast.makeText(getContext(), "Ecrire quelque chose", Toast.LENGTH_SHORT).show();

        }

        else{

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String current_date =dateFormat.format(date);

            String comment = commentText.getText().toString();

        avisList.add(new Avis(name , comment ,current_date));
        commentText.setText("");
        adapter.notifyDataSetChanged();
        StoreComment(comment);




    }
    }

    private void StoreComment(final String comment) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Send_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("user_id" , id);
                params.put("car_mat" , matricule);
                params.put("my_comment" , comment);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        requestQueue.add(stringRequest);
    }
}
