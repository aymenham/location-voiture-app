package MenuClient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.locationvoiture.CarDetailActivity;
import com.example.lenovo.locationvoiture.R;

import java.util.ArrayList;

import Myclass.Car;

/**
 * Created by LENOVO on 03/03/2018.
 */

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.Holder> {

    private Context context ;
    private ArrayList<Car> carsarray ;

    public CarsAdapter(Context context, ArrayList<Car> carsarray) {
        this.context = context;
        this.carsarray = carsarray;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.consulter_voiture_row , parent , false);

        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

            holder.carsMarMod.setText(carsarray.get(position).getMarque() +" " + carsarray.get(position).getModele());
            String tarif = Double.toString( carsarray.get(position).getTarif() );
            holder.tarif.setText(tarif + " DA/jour");
        Glide.with(context).load(carsarray.get(position).getImage()).override(700 , 150 ).into(holder.carsImage);

        holder.carsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("image", carsarray.get(position).getImage());
                bundle.putString("matricule" , Integer.toString(carsarray.get(position).getMatricule()));
                bundle.putString("marque" , carsarray.get(position).getMarque());
                bundle.putString("modele" , carsarray.get(position).getModele());
                bundle.putString("tarif" , Double.toString(carsarray.get(position).getTarif()));
                bundle.putString("etat" , carsarray.get(position).getEtat());
                bundle.putString("KM" , Double.toString(carsarray.get(position).getKM()));
                Intent intent = new Intent(context , CarDetailActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return carsarray.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{


        ImageView carsImage ;
        TextView carsMarMod , tarif  ;



        public Holder(View itemView) {
            super(itemView);
            carsImage = itemView.findViewById(R.id.image_voiture);
            carsMarMod = itemView.findViewById(R.id.marque_modele);
            tarif = itemView.findViewById(R.id.prix);
        }
    }


}
