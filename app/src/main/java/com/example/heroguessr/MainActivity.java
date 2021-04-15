package com.example.heroguessr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.heroguessr.fragments.BattleFragment;
import com.example.heroguessr.fragments.LeaderboardFragment;
import com.example.heroguessr.fragments.ProfileFragment;
import com.example.heroguessr.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String API = "https://superheroapi.com/api/104590598401309/search/";
    public static final String TAG = "MainActivity";
    private BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_search:
                        //fragment = new SearchFragment();
                        if(fragmentManager.findFragmentByTag("search")!=null) {
                            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("search")).commit();
                        }
                        else {
                            fragmentManager.beginTransaction().add(R.id.flContainer, new SearchFragment(), "search").commit();
                        }
                        if(fragmentManager.findFragmentByTag("profile")!=null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("profile")).commit();
                        }
                        if(fragmentManager.findFragmentByTag("battle")!=null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("battle")).commit();
                        }
                        if(fragmentManager.findFragmentByTag("leaderboard")!=null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("leaderboard")).commit();
                        }
                        break;
                    case R.id.action_battle:
                        //fragment = new BattleFragment();
                        if(fragmentManager.findFragmentByTag("battle")!=null) {
                            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("battle")).commit();
                        }
                        else {
                            fragmentManager.beginTransaction().add(R.id.flContainer, new BattleFragment(), "battle").commit();
                        }
                        if(fragmentManager.findFragmentByTag("search")!=null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("search")).commit();
                        }
                        if(fragmentManager.findFragmentByTag("profile")!=null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("profile")).commit();
                        }
                        if(fragmentManager.findFragmentByTag("leaderboard")!=null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("leaderboard")).commit();
                        }
                        break;
                    case R.id.action_leaderboard:
                        //fragment = new LeaderboardFragment();
                        if(fragmentManager.findFragmentByTag("leaderboard")!=null) {
                            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("leaderboard")).commit();
                        }
                        else {
                            fragmentManager.beginTransaction().add(R.id.flContainer, new LeaderboardFragment(), "leaderboard").commit();
                        }
                        if(fragmentManager.findFragmentByTag("search")!=null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("search")).commit();
                        }
                        if(fragmentManager.findFragmentByTag("battle")!=null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("battle")).commit();
                        }
                        if(fragmentManager.findFragmentByTag("profile")!=null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("profile")).commit();
                        }
                        break;
                    default:
                        //fragment = new ProfileFragment();
                        if(fragmentManager.findFragmentByTag("profile")!=null) {
                            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("profile")).commit();
                        }
                        else {
                            fragmentManager.beginTransaction().add(R.id.flContainer, new ProfileFragment(), "profile").commit();
                        }
                        if(fragmentManager.findFragmentByTag("search")!=null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("search")).commit();
                        }
                        if(fragmentManager.findFragmentByTag("battle")!=null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("battle")).commit();
                        }
                        if(fragmentManager.findFragmentByTag("leaderboard")!=null) {
                            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("leaderboard")).commit();
                        }
                        break;
                }
                //fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_battle);
    }
}