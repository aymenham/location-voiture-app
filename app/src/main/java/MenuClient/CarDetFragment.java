package MenuClient;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.locationvoiture.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarDetFragment extends Fragment {

    private TextView Matricule , Marque ,Modele , Tarif , Etat , KM ;




    public CarDetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_car_det, container, false);

        Matricule = view.findViewById(R.id.mat_det);
        Marque = view.findViewById(R.id.marque_det);
        Modele = view.findViewById(R.id.modele_det);
        Tarif = view.findViewById(R.id.tarif_det);
        Etat = view.findViewById(R.id.etat_det);
        KM = view.findViewById(R.id.KM_det);


        Bundle bundle = getActivity().getIntent().getExtras();

       String matricule = bundle.getString("matricule");
        String marque = bundle.getString("marque");
        String modele = bundle.getString("modele");
        String tarif = bundle.getString("tarif");
        String etat = bundle.getString("etat");
        String km = bundle.getString("KM");

        Matricule.setText(matricule);
        Marque.setText(marque);
        Modele.setText(modele);
        Tarif.setText(tarif + "DA / jour ");
        Etat.setText(etat);
        KM.setText(km+"00"+" " + "KM");

        return  view ;
    }

}
