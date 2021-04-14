package com.example.heroguessr.adapters;
import com.bumptech.glide.request.target.Target;
import com.example.heroguessr.R;
import com.bumptech.glide.Glide;
import com.example.heroguessr.models.Hero;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private List<Hero> heroes;

    public SearchAdapter(Context context, List<Hero> heroes) {
        this.context = context;
        this.heroes = heroes;
    }

    // inflate a layout from XML and return the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("SearchAdapter", "onCreateViewHolder");
        View heroView = LayoutInflater.from(context).inflate(R.layout.item_hero, parent, false);
        return new ViewHolder(heroView);
    }

    // populate data into the view through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("SearchAdapter", "onBindViewHolder " + position);
        Hero hero = heroes.get(position);

        holder.bind(hero);
    }

    // return count of items in list
    @Override
    public int getItemCount() {
        return heroes.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvHeroName;
        TextView tvInt;
        TextView tvStr;
        TextView tvSpeed;
        TextView tvDurability;
        TextView tvPower;
        TextView tvCombat;
        ImageView ivHeroImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeroName = itemView.findViewById(R.id.tvHeroName);
            ivHeroImage = itemView.findViewById(R.id.ivHeroImage);
            tvInt = itemView.findViewById(R.id.tvInt);
            tvStr = itemView.findViewById(R.id.tvStr);
            tvSpeed = itemView.findViewById(R.id.tvSpeed);
            tvDurability = itemView.findViewById(R.id.tvDurability);
            tvPower = itemView.findViewById(R.id.tvPower);
            tvCombat = itemView.findViewById(R.id.tvCombat);
        }


        public void bind(Hero hero) {
            Log.d("SearchAdapter", "bind");
            tvHeroName.setText(hero.getName() + " (" + hero.getFullName() + ")");
            tvInt.setText("Intelligence: " + hero.getIntelligence());
            tvStr.setText("Strength: " + hero.getStrength());
            tvSpeed.setText("Speed: " + hero.getSpeed());
            tvDurability.setText("Durability: " + hero.getDurability());
            tvPower.setText("Power: " + hero.getPower());
            tvCombat.setText("Combat: " + hero.getCombat());
            String imageUrl;
            imageUrl = hero.getImageURL();


            int radius = 10;
            int margin = 5;
            Glide.with(context)
                    .load(imageUrl)
                    .transform(new RoundedCornersTransformation(radius, margin)).override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(ivHeroImage);

            }
        }
    }

