package MenuClient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.locationvoiture.R;

import java.util.ArrayList;

import Myclass.Car;
import Myclass.CarLocation;

/**
 * Created by LENOVO on 16/03/2018.
 */

class VoitureLoueAdapter extends android.support.v7.widget.RecyclerView.Adapter<VoitureLoueAdapter.MyViewHolder> {

    private ArrayList<CarLocation> carArrayList ;

    private Context context ;

    public VoitureLoueAdapter(ArrayList<CarLocation> carArrayList, Context context) {
        this.carArrayList = carArrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mesvoiture_row , parent , false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {



        Glide.with(context).load(carArrayList.get(position).getCar().getImage()).override(700 , 150 ).into(holder.carImage);

        String marque = carArrayList.get(position).getCar().getMarque()+" "+carArrayList.get(position).getCar().getModele();

        holder.Marque.setText(marque);

        holder.date_debut.setText(carArrayList.get(position).getDate_debut());

        holder.date_fin.setText(carArrayList.get(position).getDate_fin());

        holder.NbrJours.setText(" Il vous reste : "+carArrayList.get(position).getNbrJours() + " Jours");

    }

    @Override
    public int getItemCount() {
        return carArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        ImageView carImage ;

        TextView Marque ,  date_debut , date_fin , NbrJours;

        public MyViewHolder(View itemView) {
            super(itemView);

            carImage = itemView.findViewById(R.id.voitureLimage);

            Marque = itemView.findViewById(R.id.voitureLmarque);

            date_debut = itemView.findViewById(R.id.voitureLdateD);

            date_fin = itemView.findViewById(R.id.voitureLdateF);

            NbrJours = itemView.findViewById(R.id.voitureLNbr);
        }
    }
}
