package work.pegase.android.demo.tp5;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.pereiraan.session6.R;


/**
 * Created by Silien on 16/03/2017.
 */

public class ViewPagerAct extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view_pager_act);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdp adp = new ViewPagerAdp(getSupportFragmentManager());
        adp.addFragment(new MapsFrag());
        /* TODO : Ajouter le fragment ListMarkerFrag */
        viewPager.setAdapter(adp);
    }
}
