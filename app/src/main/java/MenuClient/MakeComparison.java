package MenuClient;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.locationvoiture.ComparaisonActivity;
import com.example.lenovo.locationvoiture.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Myclass.Car;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakeComparison extends Fragment {


    private RecyclerView recyclerView1;

    private RecyclerView.LayoutManager layoutManager ;
    private RecyclerView.Adapter adapter ;

    private Car carOne, carTwo ;

    public  static  int Count = 0;

    private List<Car> carList ;
    private final String url = "http://aymenpsg.000webhostapp.com/location/carsinformation.php";



    public MakeComparison() {

        this.setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_make_comparison, container, false);

        recyclerView1 = view.findViewById(R.id.leftside);

        carOne = new Car();
        carTwo = new Car();

        carList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView1.setLayoutManager(layoutManager);

        adapter = new ComparisionAdapter(getContext() , carList , carOne , carTwo);
        recyclerView1.setAdapter(adapter);





        Get_All_Information();
        return view;
    }

    public void Get_All_Information(){




        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

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
                        carList.add(car);
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

                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);

    }

    @Override
    public void onStart() {
        super.onStart();

        Count =0;
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.comparaison_menu , menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if(id==R.id.startcom){

            if(Count==2){


               Intent intent = new Intent(getContext() , ComparaisonActivity.class);

               intent.putExtra("car_one" , carOne);

               intent.putExtra("car_two" , carTwo);



               startActivity(intent);

            }

            else {

                Toast.makeText(getContext(), "il faut choisir deux voitures ", Toast.LENGTH_SHORT).show();
            }


        }

        return true;

    }
}
