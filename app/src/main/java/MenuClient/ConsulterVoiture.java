package MenuClient;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.locationvoiture.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Myclass.Car;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsulterVoiture extends Fragment {

    private RecyclerView carlist ;
    private RecyclerView.Adapter adapter ;
    private RecyclerView.LayoutManager layoutManager ;
    private ArrayList<Car> carArrayList ;
    private final String url = "http://aymenpsg.000webhostapp.com/location/carsinformation.php";
    private ProgressBar progressBar;

    public ConsulterVoiture() {

        this.setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_consulter_voiture, container, false);
        carlist = view.findViewById(R.id.consulterrec);
        carArrayList = new ArrayList<>();
        progressBar = view.findViewById(R.id.prograss);
        layoutManager = new LinearLayoutManager(getContext());
        carlist.setLayoutManager(layoutManager);
        adapter = new CarsAdapter(getContext() , carArrayList);
        carlist.setAdapter(adapter);
        progressBar.setVisibility(View.VISIBLE);
        Get_All_Information();
        return view;
    }

    public void Get_All_Information(){


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, new Response.Listener<JSONArray>() {
            @Override
                public void onResponse(JSONArray response) {
                    progressBar.setVisibility(View.GONE);
                    int count =0;



                    while(count<response.length()){


                        try {
                            JSONObject jsonObject = response.getJSONObject(count);
                            int matricule = jsonObject.getInt("matricule");
                            String marque = jsonObject.getString("marque");
                            String modele = jsonObject.getString("modele");
                            double tarif = jsonObject.getDouble("tarif");
                            String etat = jsonObject.getString("etat");
                            double KM = jsonObject.getDouble("KM");
                            String image = jsonObject.getString("image");
                            Car car = new Car(matricule , marque,modele,tarif,etat,KM,image);
                            carArrayList.add(car);
                            count++;
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                    }

                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.consulter_voiture_menu , menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case R.id.prixtrie :

                SortByprice();

                break;
            case R.id.nomtrie :
                SortByname();
                break;
        }

        return true;
    }

    private void SortByname() {

        Collections.sort(carArrayList, new Comparator<Car>(){
            public int compare(Car s1, Car s2) {
                return s1.getMarque().compareToIgnoreCase(s2.getMarque());
            }
        }) ;

        adapter.notifyDataSetChanged();
    }

    private void SortByprice() {

        Collections.sort(carArrayList, new Comparator<Car>(){
            public int compare(Car s1, Car s2) {

                Double priceone = new Double(s1.getTarif());
                Double pricetwo = new Double(s2.getTarif());

                return   priceone.compareTo(pricetwo);
            }
        }) ;

        adapter.notifyDataSetChanged();
    }
}
