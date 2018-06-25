package com.example.next.simpleweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Student on 4/22/2017.
 */

    public class Tabb_Activity extends Fragment {

    TabsPagerAdapter tabsPagerAdapter;
    TabLayout tabLayout;
MainActivity mainActivity=new MainActivity();
    ViewPager mViewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.tab_layout,container,false);

        tabsPagerAdapter= new TabsPagerAdapter(getFragmentManager());
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();
                Log.d("TAG", String.valueOf(position));
                switch (position) {
                    case 0: {

                        mainActivity.changeCity("Chandigarh");
                        break;

                    }
                    case 1:
                        mainActivity.changeCity("Gurgaon");

                        break;
                    case 2:
                        mainActivity.changeCity("Jaipur");

                        break;
                    default:
                        mainActivity.changeCity("Chandigarh");

                }

            }
        });
        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {
        TabsPagerAdapter adapter = new TabsPagerAdapter(getFragmentManager());
        adapter.addFragment(new City1(), "Chandigarh");
        adapter.addFragment(new City1(), "Gurgaon");
        adapter.addFragment(new City1(), "Jaipur");
        viewPager.setAdapter(adapter);
    }



}
