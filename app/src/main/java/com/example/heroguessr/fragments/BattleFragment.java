package com.example.heroguessr.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.heroguessr.R;
import com.example.heroguessr.models.Hero;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONException;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
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
    TextView tvWinner;
    TextView tvStatus;
    Button btnNewBattle;
    Button btnHero1Win;
    Button btnHero2Win;

    int randomHero1;
    int randomHero2;

    String hero1Name;
    String hero2Name;
    String intelligence1;
    String strength1;
    String speed1;
    String durability1;
    String power1;
    String combat1;

    String intelligence2;
    String strength2;
    String speed2;
    String durability2;
    String power2;
    String combat2;

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
        tvWinner = view.findViewById(R.id.tvWinner);
        tvStatus = view.findViewById(R.id.tvStatus);

        btnNewBattle = view.findViewById(R.id.btnNewBattle);
        btnHero1Win = view.findViewById(R.id.btnHero1Win);
        btnHero2Win = view.findViewById(R.id.btnHero2Win);

        btnNewBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultHeroStats();
                btnHero1Win.setEnabled(true);
                btnHero2Win.setEnabled(true);
                getHeroes();
            }
        });
        getHeroes();

        btnHero1Win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHero1Stats();
                setHero2Stats();
                pickWinner(1);
                btnHero1Win.setEnabled(false);
                btnHero2Win.setEnabled(false);
            }
        });

        btnHero2Win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHero1Stats();
                setHero2Stats();
                pickWinner(2);
                btnHero1Win.setEnabled(false);
                btnHero2Win.setEnabled(false);
            }
        });
    }

    public void pickWinner(int winner) {
        int intel1 = Integer.parseInt(intelligence1);
        int str1 = Integer.parseInt(strength1);
        int spd1 = Integer.parseInt(speed1);
        int dur1 = Integer.parseInt(durability1);
        int pwr1 = Integer.parseInt(power1);
        int cbt1 = Integer.parseInt(combat1);

        int intel2 = Integer.parseInt(intelligence2);
        int str2 = Integer.parseInt(strength2);
        int spd2 = Integer.parseInt(speed2);
        int dur2 = Integer.parseInt(durability2);
        int pwr2 = Integer.parseInt(power2);
        int cbt2 = Integer.parseInt(combat2);

        int hero1Overall = intel1 + str1 + spd1 + dur1 + pwr1 + cbt1;
        int hero2Overall = intel2 + str2 + spd2 + dur2 + pwr2 + cbt2;


        if (hero1Overall > hero2Overall) {
            tvWinner.setText("Winner is " + hero1Name);
            if (winner == 1) {
                tvStatus.setText("You were CORRECT");
                addWin();
            } else {
                tvStatus.setText("You were INCORRECT");
                addLoss();
            }
        } else {
            tvWinner.setText("Winner is " + hero2Name);
            if (winner == 2) {
                tvStatus.setText("You were CORRECT");
                addWin();
            } else {
                tvStatus.setText("You were INCORRECT");
                addLoss();
            }
        }
    }

    private void addWin() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        currentUser.increment("Wins");
        currentUser.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.i(TAG, "Successfully saved win");
                } else {
                    Log.e(TAG, "Failed to save win" + e);
                }
            }
        });
    }

    private void addLoss() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        currentUser.increment("Losses");
        currentUser.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.i(TAG, "Successfully saved loss");
                } else {
                    Log.e(TAG, "Failed to save loss" + e);
                }
            }
        });
    }

    public void saveHero1Stats(Hero hero) {
        hero1Name = hero.getName();
        intelligence1 = hero.getIntelligence();
        strength1 = hero.getStrength();
        speed1 = hero.getSpeed();
        durability1 = hero.getDurability();
        power1 = hero.getPower();
        combat1 = hero.getCombat();
    }

    public void saveHero2Stats(Hero hero) {
        hero2Name = hero.getName();
        intelligence2 = hero.getIntelligence();
        strength2 = hero.getStrength();
        speed2 = hero.getSpeed();
        durability2 = hero.getDurability();
        power2 = hero.getPower();
        combat2 = hero.getCombat();
    }

    public void setHero1Stats() {
        tvIntelligence1.setText("Intelligence: " + intelligence1);
        tvStrength1.setText("Strength: " + strength1);
        tvSpeed1.setText("Speed: " + speed1);
        tvDurability1.setText("Durability: " + durability1);
        tvPower1.setText("Power: " + power1);
        tvCombat1.setText("Combat: " + combat1);
    }

    public void setHero2Stats() {
        tvIntelligence2.setText("Intelligence: " + intelligence2);
        tvStrength2.setText("Strength: " + strength2);
        tvSpeed2.setText("Speed: " + speed2);
        tvDurability2.setText("Durability: " + durability2);
        tvPower2.setText("Power: " + power2);
        tvCombat2.setText("Combat: " + combat2);
    }

    public void defaultHeroStats() {
        tvIntelligence1.setText("Intelligence: ?");
        tvStrength1.setText("Strength: ?");
        tvSpeed1.setText("Speed: ?");
        tvDurability1.setText("Durability: ?");
        tvPower1.setText("Power: ?");
        tvCombat1.setText("Combat: ?");
        tvIntelligence2.setText("Intelligence: ?");
        tvStrength2.setText("Strength: ?");
        tvSpeed2.setText("Speed: ?");
        tvDurability2.setText("Durability: ?");
        tvPower2.setText("Power: ?");
        tvCombat2.setText("Combat: ?");
        tvWinner.setText("Winner is ...");
        tvStatus.setText("");
    }

    public boolean isThereNull(Hero hero) {
        if (hero.getIntelligence().equals("null")) {
            return true;
        }
        if (hero.getStrength().equals("null")) {
            return true;
        }
        if (hero.getSpeed().equals("null")) {
            return true;
        }
        if (hero.getDurability().equals("null")) {
            return true;
        }
        if (hero.getPower().equals("null")) {
            return true;
        }
        return hero.getCombat().equals("null");
    }

    public void getHeroes() {
        getHero1();
        getHero2();
    }

    public void getHero1() {
        randomHero1 = (int) (Math.random() * 731 + 1);
        String heroURL1 = API + randomHero1;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(heroURL1, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                try {
                    Hero hero = new Hero(json.jsonObject);
                    if (isThereNull(hero)) {
                        getHero1();
                    } else {
                        tvHero1.setText(hero.getName());
                        saveHero1Stats(hero);
                        Glide.with(getContext()).load(hero.getImageURL()).into(ivHero1);
                    }
                    Log.i(TAG, "Success");
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

    public void getHero2() {
        randomHero2 = (int) (Math.random() * 731 + 1);
        String heroURL2 = API + randomHero2;

        AsyncHttpClient client2 = new AsyncHttpClient();
        client2.get(heroURL2, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                try {
                    Hero hero = new Hero(json.jsonObject);
                    if (isThereNull(hero)) {
                        getHero2();
                    } else {
                        tvHero2.setText(hero.getName());
                        saveHero2Stats(hero);
                        Glide.with(getContext()).load(hero.getImageURL()).transform(new RoundedCornersTransformation(10, 5)).override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(ivHero2);
                    }
                    Log.i(TAG, "Success");
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