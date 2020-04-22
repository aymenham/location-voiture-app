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
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by LENOVO on 08/03/2018.
 */

public class FavoritAdapter extends RecyclerView.Adapter<FavoritAdapter.MyHolder> {

    private ArrayList<Car> arrayList ;

    private Car carOne, carTwo ;

    Context context ;

    public FavoritAdapter(ArrayList<Car> arrayList, Context context ) {
        this.arrayList = arrayList;
        this.context = context;

          }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.favorit_row , parent , false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {


        Glide.with(context).load(arrayList.get(position).getImage()).override(100 , 100).into(holder.favori_image);

        holder.marque.setText(arrayList.get(position).getMarque());
        holder.modele.setText(arrayList.get(position).getModele());

        holder.favori_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("image", arrayList.get(position).getImage());
                bundle.putString("matricule" , Integer.toString(arrayList.get(position).getMatricule()));
                bundle.putString("marque" , arrayList.get(position).getMarque());
                bundle.putString("modele" , arrayList.get(position).getModele());
                bundle.putString("tarif" , Double.toString(arrayList.get(position).getTarif()));
                bundle.putString("etat" , arrayList.get(position).getEtat());
                bundle.putString("KM" , Double.toString(arrayList.get(position).getKM()));
                Intent intent = new Intent(context , CarDetailActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size() ;
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        CircleImageView favori_image ;

        TextView marque ;

        TextView modele ;

        public MyHolder(View itemView) {
            super(itemView);

            favori_image = itemView.findViewById(R.id.image_favoris);
            marque = itemView.findViewById(R.id.favorit_text);

            modele = itemView.findViewById(R.id.modele_fav) ;
        }
    }
}
