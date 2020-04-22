package MenuClient;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class MesFavoris extends Fragment {

    private RecyclerView recyclerView ;

    private ProgressBar progressBar ;

    private final String url ="http://aymenpsg.000webhostapp.com/location/favoritinformations.php";

    private RecyclerView.Adapter adapter ;

    private RecyclerView.LayoutManager layoutManager ;

    ArrayList<Car> arrayList ;



    private String id ;

    private SharedPreferences preferences ;

    public MesFavoris() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mes_favoris, container, false);

        recyclerView = view.findViewById(R.id.recyler_favoris);

        progressBar = view.findViewById(R.id.prograss_favorit);

        arrayList = new ArrayList<>();

        preferences = getActivity().getSharedPreferences("login" , getActivity().MODE_PRIVATE);

        id = preferences.getString("id" , null);







        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        adapter = new FavoritAdapter( arrayList
                , getContext());

        progressBar.setVisibility(View.VISIBLE);


        recyclerView.setAdapter(adapter);

        GetAllInformation();

        return view;
    }

    public void GetAllInformation(){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressBar.setVisibility(View.GONE);

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i =0 ; i< jsonArray.length() ; i++){

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int matricule = jsonObject.getInt("matricule");
                        String marque = jsonObject.getString("marque");
                        String modele = jsonObject.getString("modele");
                        double tarif = jsonObject.getDouble("tarif");
                        String etat = jsonObject.getString("etat");
                        double KM = jsonObject.getDouble("KM");
                        String image = jsonObject.getString("image");
                        Car car = new Car(matricule , marque,modele,tarif,etat,KM,image);
                        arrayList.add(car);

                        adapter.notifyDataSetChanged();

                    }
                } catch (JSONException e) {
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
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
