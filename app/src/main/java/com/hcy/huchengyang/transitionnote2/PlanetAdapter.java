package com.hcy.huchengyang.transitionnote2;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.hcy.huchengyang.transitionnote2.model.Planet;

import java.util.ArrayList;
import java.util.List;

public class PlanetAdapter extends Adapter<PlanetAdapter.PlanetViewHolder> {

    private List<Planet> planets = new ArrayList<>();

    private final OnPlanetClickedListener listener;

    public PlanetAdapter() {
        this.listener = null;
    }

    public PlanetAdapter(OnPlanetClickedListener listener) {
        this.listener = listener;
    }

    @Override
    public PlanetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_planet, parent, false);

        return new PlanetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PlanetViewHolder holder, final int position) {
        Planet planet = planets.get(position);

        holder.name.setText(planet.name);

        Glide.with(holder.image.getContext())
                .load(planet.url)
                .centerCrop()
                .into(holder.image);

        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPlanetClicked(holder.image, planets.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }

    public class PlanetViewHolder extends ViewHolder {

        private final TextView name;
        private final ImageView image;

        public PlanetViewHolder(View itemView) {
            super(itemView);

            name =  itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
        }
    }

    public interface OnPlanetClickedListener {
        void onPlanetClicked(View sharedImage, Planet planet);
    }
}
