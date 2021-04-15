package com.example.heroguessr.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.heroguessr.R;
import com.example.heroguessr.models.Hero;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import okhttp3.Headers;

public class BattleFragment extends Fragment {
    public static final String API = "https://superheroapi.com/api/104590598401309/";
    public static final String TAG = "BattleFragment";
    TextView tvHero1;
    TextView tvHero2;
    ImageView ivHero1;
    ImageView ivHero2;
    int randomHero1;
    int randomHero2;

    public BattleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_battle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvHero1 = view.findViewById(R.id.tvHero1);
        ivHero1 = view.findViewById(R.id.ivHero1);
        tvHero2 = view.findViewById(R.id.tvHero2);
        ivHero2 = view.findViewById(R.id.ivHero2);

        getHeroes();
    }

    public void getHeroes() {
        randomHero1 = (int)(Math.random()*731+1);
        randomHero2 = (int)(Math.random()*731+1);
        String heroURL1 = API + randomHero1;
        String heroURL2 = API + randomHero2;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(heroURL1, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                try {
                    Hero hero = new Hero(json.jsonObject);
                    tvHero1.setText(hero.getName());
                    Log.i(TAG, "Success: " + json.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i(TAG, "Response Error: " + json.toString());
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.i(TAG, "API Error: " + throwable.toString());
            }
        });

        AsyncHttpClient client2 = new AsyncHttpClient();
        client2.get(heroURL2, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                try {
                    Hero hero = new Hero(json.jsonObject);
                    tvHero2.setText(hero.getName());
                    Log.i(TAG, "Success: " + json.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i(TAG, "Response Error: " + json.toString());
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.i(TAG, "API Error: " + throwable.toString());
            }
        });
    }
}