package com.example.heroguessr.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    TextView tvIntelligence1;
    TextView tvStrength1;
    TextView tvSpeed1;
    TextView tvDurability1;
    TextView tvPower1;
    TextView tvCombat1;
    TextView tvIntelligence2;
    TextView tvStrength2;
    TextView tvSpeed2;
    TextView tvDurability2;
    TextView tvPower2;
    TextView tvCombat2;

    Button btnNewBattle;
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
        tvIntelligence1 = view.findViewById(R.id.tvIntelligence1);
        tvStrength1 = view.findViewById(R.id.tvStrength1);
        tvSpeed1 = view.findViewById(R.id.tvSpeed1);
        tvDurability1 = view.findViewById(R.id.tvDurability1);
        tvPower1 = view.findViewById(R.id.tvPower1);
        tvCombat1 = view.findViewById(R.id.tvCombat1);
        tvIntelligence2 = view.findViewById(R.id.tvIntelligence2);
        tvStrength2 = view.findViewById(R.id.tvStrength2);
        tvSpeed2 = view.findViewById(R.id.tvSpeed2);
        tvDurability2 = view.findViewById(R.id.tvDurability2);
        tvPower2 = view.findViewById(R.id.tvPower2);
        tvCombat2 = view.findViewById(R.id.tvCombat2);

        btnNewBattle = view.findViewById(R.id.btnNewBattle);

        btnNewBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHeroes();
            }
        });
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
                    tvIntelligence1.setText(hero.getIntelligence());
                    tvStrength1.setText(hero.getStrength());
                    tvSpeed1.setText(hero.getSpeed());
                    tvDurability1.setText(hero.getDurability());
                    tvPower1.setText(hero.getPower());
                    tvCombat1.setText(hero.getCombat());
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