package MenuClient;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Myclass.Car;
import Myclass.CarLocation;

/**
 * A simple {@link Fragment} subclass.
 */
public class VoitureLoue extends Fragment {

    private RecyclerView recyclerView ;

    private RecyclerView.Adapter adapter ;

    private RecyclerView.LayoutManager layoutManager ;

    private ArrayList<CarLocation> carLocationArrayList ;

    private final String url ="http://aymenpsg.000webhostapp.com/location/mycarslocation.php";

    private String id ;

    public VoitureLoue() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_voiture_loue, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login" , getActivity().MODE_PRIVATE);


        id =  sharedPreferences.getString("id" , null);

        recyclerView = view.findViewById(R.id.voiturelouee);

        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        carLocationArrayList = new ArrayList<>();

        adapter = new VoitureLoueAdapter(carLocationArrayList , getContext());

        recyclerView.setAdapter(adapter);

        Get_All_Information();
        return view;
    }

    private void Get_All_Information() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);


                    for(int i=0 ; i<response.length() ; i++){

                        try {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String image = jsonObject.getString("image");
                            String marque = jsonObject.getString("marque");
                            String modele = jsonObject.getString("modele");

                            Car car = new Car(0,marque , modele ,0 ,"" ,0 ,image);

                            String date_debut = jsonObject.getString("date_debut");

                            String date_fin = jsonObject.getString("date_fin");

                            String nbrjours = jsonObject.getString("nbrjours");

                            CarLocation carLocation = new CarLocation(date_debut , date_fin , nbrjours , car);

                            carLocationArrayList.add(carLocation);

                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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

                params.put("id_client" , id);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        requestQueue.add(stringRequest);


    }

}
