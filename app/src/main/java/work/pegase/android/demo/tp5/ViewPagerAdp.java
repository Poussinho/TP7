package work.pegase.android.demo.tp5;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Silien on 16/03/2017.
 */

public class ViewPagerAdp extends FragmentPagerAdapter {

    private final List fragments = new ArrayList();
    private FragmentManager fm ;

    public ViewPagerAdp(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    public void addFragment(Fragment frag){
        fragments.add(frag);
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int position) {
        return (Fragment) fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
