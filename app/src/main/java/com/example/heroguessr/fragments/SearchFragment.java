package com.example.heroguessr.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
import com.example.heroguessr.MainActivity;
import com.example.heroguessr.R;
import com.example.heroguessr.models.Hero;
//import com.example.heroguessr.adapters.SearchAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private EditText etHeroSearch;
    private RecyclerView rvSearchResults;
    protected List<Hero> heroes;
    protected SearchAdapter searchAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvSearchResults = view.findViewById(R.id.rvSearchResults);
        heroes = new ArrayList<>();
        searchAdapter = new SearchAdapter(getContext(), heroes);
        rvSearchResults.setAdapter(searchAdapter);
        rvSearchResults.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.i("SearchFragment", "Adapter: " + searchAdapter);

        etHeroSearch = view.findViewById(R.id.etHeroSearch);

        // listen for enter on hero search keypress, search API for hero
        etHeroSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {

                      //Toast.makeText(getContext(), etHeroSearch.getText(), Toast.LENGTH_SHORT).show();
                      String heroName = etHeroSearch.getText().toString();
                      searchForHero(heroName);
                    }
                return false;
            }
        });
    }

    private void searchForHero(String name) {
        String url = MainActivity.API + name;

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");
                    heroes.clear();
                    searchAdapter.notifyDataSetChanged();
                    heroes.addAll(Hero.fromJsonArray(results));
                    searchAdapter.notifyDataSetChanged();
                    for (Hero hero : heroes) {
                        Log.i("SearchFrament", "Hero: " + hero.getName() + ", id: " + hero.getHeroID());
                    }

                    } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}