package com.example.proyectointegradorgrupal.dao;

import com.example.proyectointegradorgrupal.model.Body;
import com.example.proyectointegradorgrupal.model.Example;
import com.example.proyectointegradorgrupal.model.Message;
import com.example.proyectointegradorgrupal.service.LyricService;
import com.example.proyectointegradorgrupal.util.ResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LyricsDao extends RetrofitDaoLyrics {

    private static String apikey = "770a20daafeb67187c8876b46eaf0196";

    private LyricService lyricService;

    public LyricsDao() {lyricService = super.retrofitLyrics.create(LyricService.class); }

    public void getLyrics(String trackName, String artistName, final ResultListener<Example> resultListenerPorController) {
        Call<Example> call = this.lyricService.getLyrics(trackName,artistName, apikey);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()) {
                    Example example = response.body();
                    resultListenerPorController.onFinish(example);
                } else {
                    response.errorBody();
                }

            }


            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }



}
