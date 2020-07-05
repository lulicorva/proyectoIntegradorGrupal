package com.example.proyectointegradorgrupal.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.proyectointegradorgrupal.dao.SearchDao;
import com.example.proyectointegradorgrupal.model.Track;
import com.example.proyectointegradorgrupal.util.ResultListener;

public class SearchController {

    private SearchDao searchDao;
    private Context context;

    public SearchController(Context context) {
        this.searchDao = new SearchDao();
        this.context = context;
    }

    public void getSearch(String query, final ResultListener<Track> resultListenerFromView){


        if(hayInternet()) {

            searchDao.getSearch(query, new ResultListener<Track>() {
                @Override
                public void onFinish(Track result) {
                    resultListenerFromView.onFinish(result);
                }
            });
        } else {
            Toast.makeText(context, "No hay conexion", Toast.LENGTH_SHORT).show();





        }

    }

    public boolean hayInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return actNetInfo != null && actNetInfo.isConnected();


    }

}
