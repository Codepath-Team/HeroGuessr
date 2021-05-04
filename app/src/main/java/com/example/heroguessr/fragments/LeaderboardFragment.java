package com.example.heroguessr.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.heroguessr.R;
import com.example.heroguessr.adapters.PlayersAdapter;
import com.example.heroguessr.models.User;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardFragment extends Fragment {

    public static final String TAG = "LeaderboardFragment";
    private RecyclerView rvLeaderboards;
    protected PlayersAdapter adapter;
    protected List<ParseUser> allUsers;
    private SwipeRefreshLayout swipeContainer;


    public LeaderboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leaderboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvLeaderboards = view.findViewById(R.id.rvLeaderboards);
        allUsers = new ArrayList<ParseUser>();
        adapter = new PlayersAdapter(getContext(), allUsers);
        rvLeaderboards.setAdapter(adapter);
        rvLeaderboards.setLayoutManager(new LinearLayoutManager(getContext()));
        queryUsers();

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                queryUsers();
            }
        });
    }

    protected void queryUsers() {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.include(User.KEY_USERNAME);
        query.whereGreaterThan("Wins", 0);
        query.setLimit(25);
        query.addDescendingOrder(User.KEY_WINS);
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue getting users", e);
                }
                for (ParseUser user : users) {
                    Log.i(TAG, "User found: " + user.get("username"));
                }
                allUsers.clear();
                allUsers.addAll(users);
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }
        });
    }
    public void clear() {
        allUsers.clear();
        adapter.notifyDataSetChanged();
    }

    public void addAll(List<ParseUser> list) {
        allUsers.addAll(list);
        adapter.notifyDataSetChanged();
    }
}