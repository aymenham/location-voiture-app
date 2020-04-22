package MenuClient;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 20/03/2018.
 */

public class DetailleTabAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList ;
    List<String> listtitle;

    public DetailleTabAdapter(FragmentManager fm) {
        super(fm);

        fragmentList = new ArrayList<>();
        listtitle = new ArrayList<>();
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listtitle.get(position);
    }

    public void AddTab(Fragment fragment , String title){

        listtitle.add(title);

        fragmentList.add(fragment);

    }
}
