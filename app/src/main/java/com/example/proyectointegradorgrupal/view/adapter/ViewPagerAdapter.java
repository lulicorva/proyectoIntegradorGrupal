package com.example.proyectointegradorgrupal.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.proyectointegradorgrupal.view.fragment.FragmentDetalleCancion;
import com.example.proyectointegradorgrupal.view.fragment.FragmentReproductor;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> fragmentList;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior, FragmentReproductor fragmentReproductor) {
        super(fm, behavior);

        fragmentList = new ArrayList<>();
        fragmentList.add(fragmentReproductor);
        fragmentList.add(new FragmentDetalleCancion());

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
