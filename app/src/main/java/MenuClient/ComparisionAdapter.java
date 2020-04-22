package MenuClient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.locationvoiture.R;

import java.util.List;

import Myclass.Car;

/**
 * Created by LENOVO on 16/03/2018.
 */

class ComparisionAdapter extends RecyclerView.Adapter<ComparisionAdapter.MyviewHodler> {
    private List<Car> carList ;
    private Context context ;

    private Car carOne, carTwo ;

    public ComparisionAdapter(Context context, List<Car> carList , Car carOne , Car carTwo) {
        this.context = context ;
        this.carList = carList;
        this.carOne = carOne;
        this.carTwo = carTwo;

    }

    @Override
    public MyviewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.comparaison_row , parent , false);

        return  new MyviewHodler(view);

    }



    @Override
    public void onBindViewHolder(final MyviewHodler holder, final int position) {

        Glide.with(context).load(carList.get(position).getImage()).override(300 , 100 ).into(holder.imageView);
        holder.myCheck.setChecked(false);

        holder.myCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked){
                    if(MakeComparison.Count>=2){
                        Toast.makeText(context, "il faut choisir deux voitures seulement", Toast.LENGTH_SHORT).show();
                        holder.myCheck.setChecked(false);
                    }
                    else {
                        MakeComparison.Count++;

                        if(MakeComparison.Count==1){
                            carOne.setImage(carList.get(position).getImage());
                            carOne.setEtat(carList.get(position).getEtat());
                            carOne.setKM(carList.get(position).getKM());
                            carOne.setTarif(carList.get(position).getTarif());

                        }

                        if(MakeComparison.Count==2){
                            carTwo.setImage(carList.get(position).getImage());
                            carTwo.setEtat(carList.get(position).getEtat());
                            carTwo.setKM(carList.get(position).getKM());
                            carTwo.setTarif(carList.get(position).getTarif());

                        }




                    }

                }

                else{

                    MakeComparison.Count--;

                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class MyviewHodler extends RecyclerView.ViewHolder{

        ImageView imageView ;
        CheckBox myCheck;

        public MyviewHodler(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imagecom);
            myCheck = itemView.findViewById(R.id.mycheck);
        }
    }
}
