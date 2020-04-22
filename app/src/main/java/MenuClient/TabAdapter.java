package MenuClient;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 15/03/2018.
 */

public class TabAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList ;

    private List<String> titlelist ;


    public TabAdapter(FragmentManager fm) {
        super(fm);

        fragmentList = new ArrayList<>();

        titlelist = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return titlelist.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlelist.get(position) ;
    }

    public void AddFragment(Fragment fragment , String title){

        fragmentList.add(fragment);

        titlelist.add(title);
    }
}
