package MenuClient;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.locationvoiture.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MesVoiture extends Fragment {

    private TabLayout tabLayout ;

    private ViewPager viewPager ;

    private TabAdapter adapter ;


    public MesVoiture() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_mes_voiture, container, false);

        tabLayout = view.findViewById(R.id.mytab) ;

        viewPager = view.findViewById(R.id.myviewpager) ;

        adapter = new TabAdapter(getActivity().getSupportFragmentManager());

        adapter.AddFragment(new VoitureLoue() , "Mes Voitures");

        adapter.AddFragment(new ConsulterVoiture() , "Voiture en attent");

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        return view ;
    }


}
