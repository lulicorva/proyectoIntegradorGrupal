package com.example.proyectointegradorgrupal.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.view.fragment.FragmentReproductor;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapterPrueba extends FragmentStatePagerAdapter {


    private List<Fragment> fragmentList;


    public ViewPagerAdapterPrueba(@NonNull FragmentManager fm, int behavior, List<Track> trackList) {
        super(fm, behavior);

        fragmentList = new ArrayList<>();

        for (Track track : trackList) {
            fragmentList.add(FragmentReproductor.getFragmentReproductor(track));

        }


    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
