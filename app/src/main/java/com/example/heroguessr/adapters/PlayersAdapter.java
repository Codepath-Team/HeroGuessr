package com.example.heroguessr.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.heroguessr.R;
import com.example.heroguessr.models.User;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.text.DecimalFormat;
import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {
    private Context context;
    private List<ParseUser> users;

    public PlayersAdapter(Context context, List<ParseUser> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_player, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParseUser user = users.get(position);
        holder.bind(user, position + 1);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRank;
        private TextView tvName;
        private TextView tvGames;
        private TextView tvWinrate;
        private ImageView ivProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRank = itemView.findViewById(R.id.tvRank);
            tvName = itemView.findViewById(R.id.tvName);
            tvGames = itemView.findViewById(R.id.tvGames);
            tvWinrate = itemView.findViewById(R.id.tvWinrate);
            ivProfile = itemView.findViewById(R.id.ivProfile);
        }

        public void bind(ParseUser user, int rank) {
            int userWins = (int) user.get("Wins");
            int userLosses = (int) user.get("Losses");
            int userGames = userWins + userLosses;
            double userWinrate = (double) userWins / userGames;
            DecimalFormat df = new DecimalFormat("0.00%");
            Log.i("test", String.valueOf(userWinrate));
            tvRank.setText(String.valueOf(rank));
            tvName.setText(user.getUsername());
            tvGames.setText(String.valueOf(userGames));
            tvWinrate.setText(df.format((userWinrate)));

            ParseFile image = (ParseFile) user.get("image");
            if (image != null) {
                Glide.with(context).load(((ParseFile) user.get("image")).getUrl()).into(ivProfile);
            }
        }
    }

}