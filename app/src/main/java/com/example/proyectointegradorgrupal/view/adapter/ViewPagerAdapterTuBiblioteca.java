package com.example.proyectointegradorgrupal.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.proyectointegradorgrupal.view.fragment.FragmentDetalleCancion;
import com.example.proyectointegradorgrupal.view.fragment.FragmentReproductor;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapterTuBiblioteca extends FragmentStatePagerAdapter {


    private List<Fragment> fragmentList;

    public ViewPagerAdapterTuBiblioteca(@NonNull FragmentManager fm, int behavior, Fragment fragment1, Fragment fragment2, Fragment fragment3) {
        super(fm, behavior);

        fragmentList = new ArrayList<>();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);

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
