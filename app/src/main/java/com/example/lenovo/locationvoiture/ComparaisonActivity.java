package com.example.lenovo.locationvoiture;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import MenuClient.MakeComparison;
import Myclass.Car;

public class ComparaisonActivity extends AppCompatActivity {

    ImageView carImageOne, carImageTwo;

    TextView etatOne , etatTwo ;

    TextView prixOne , prixTwo ;

    TextView kmOne, kmTwo ;

    ImageView imgEtatOne , imgEtatTwo ;

    ImageView imgPrixtOne , imgPrixTwo ;

    ImageView imgKmOne , imgKMTwo ;

    private static int OneCount =0 , TwoCount =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparaison);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#54915f")));
        getSupportActionBar().setTitle("Résultat comparaison");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Car carOne = (Car) getIntent().getSerializableExtra("car_one");

        Car cartwo = (Car) getIntent().getSerializableExtra("car_two");

        carImageOne = findViewById(R.id.caroneimg);
        carImageTwo = findViewById(R.id.cartwoimg);

        Glide.with(this).load(carOne.getImage()).into(carImageOne);
        Glide.with(this).load(cartwo.getImage()).into(carImageTwo);

        etatOne = findViewById(R.id.etat_one);
        etatTwo = findViewById(R.id.etat_two);

        etatOne.setText(carOne.getEtat());
        etatTwo.setText(cartwo.getEtat());

        prixOne = findViewById(R.id.prix_one);
        prixTwo = findViewById(R.id.prix_two);

        prixOne.setText(Double.toString(carOne.getTarif()));
        prixTwo.setText(Double.toString(cartwo.getTarif()));

        kmOne = findViewById(R.id.km_one);
        kmTwo = findViewById(R.id.km_two);

        kmOne.setText(Double.toString(carOne.getKM()));
        kmTwo.setText(Double.toString(cartwo.getKM()));

        imgEtatOne = findViewById(R.id.img_etat_one);
        imgEtatTwo = findViewById(R.id.img_etat_two);

        imgPrixtOne = findViewById(R.id.img_prix_one);
        imgPrixTwo = findViewById(R.id.img_prix_two);

        imgKmOne = findViewById(R.id.img_km_one);
        imgKMTwo = findViewById(R.id.img_km_two);

        MakeTheComparaison(carOne , cartwo);













    }



    public void MakeTheComparaison(Car one , Car two ){

        if(one.getTarif()>two.getTarif()){


            imgPrixtOne.setImageResource(R.drawable.ic_thumb_down_black_24dp);
            imgPrixTwo.setImageResource(R.drawable.ic_thumb_up_black_24dp);
            TwoCount++;
        }

        if(one.getKM()>two.getKM()){


            imgKmOne.setImageResource(R.drawable.ic_thumb_down_black_24dp);
            imgKMTwo.setImageResource(R.drawable.ic_thumb_up_black_24dp);
            TwoCount++;
        }

        if(one.getEtat().equals("neuf")){

            imgEtatOne.setImageResource(R.drawable.ic_thumb_up_black_24dp);
            OneCount++;
        }

        else if(!one.getEtat().equals("neuf")){

            imgEtatOne.setImageResource(R.drawable.ic_thumb_down_black_24dp);
        }

        //**************************************************************************


        if(two.getTarif()>one.getTarif()){


            imgPrixtOne.setImageResource(R.drawable.ic_thumb_up_black_24dp);
            imgPrixTwo.setImageResource(R.drawable.ic_thumb_down_black_24dp);
            OneCount++;
        }

        if(two.getKM()>one.getKM()){


            imgKmOne.setImageResource(R.drawable.ic_thumb_up_black_24dp);
            imgKMTwo.setImageResource(R.drawable.ic_thumb_down_black_24dp);
            OneCount++;
        }

        if(two.getEtat().equals("neuf")){

            imgEtatTwo.setImageResource(R.drawable.ic_thumb_up_black_24dp);
            TwoCount++;
        }

        else if(!two.getEtat().equals("neuf")){

            imgEtatTwo.setImageResource(R.drawable.ic_thumb_down_black_24dp);
        }


    }

}
