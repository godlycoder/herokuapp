package com.example.ribani.herokuapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ribani.herokuapp.R;
import com.example.ribani.herokuapp.listener.CustomItemClickListener;
import com.example.ribani.herokuapp.model.Actors;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder> {

    private Context mContext;
    private List<Actors> actorsList;
    private CustomItemClickListener itemClickListener;

    public ActorsAdapter(Context mContext, List<Actors> actorsList, CustomItemClickListener itemClickListener) {
        this.mContext = mContext;
        this.actorsList = actorsList;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ActorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_actors_layout, parent, false);

        return new ActorsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ActorsViewHolder holder, int position) {
        Actors actors = actorsList.get(position);

        holder.textName.setText(actors.getName());
        holder.textDob.setText(actors.getNewFormatDob());
        Picasso.get().load(actors.getImage()).into(holder.imageActor);

        holder.setActors(actors);
    }

    @Override
    public int getItemCount() {
        return actorsList.size();
    }

    public class ActorsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Actors actors;

        @BindView(R.id.imageActor)
        ImageView imageActor;
        @BindView(R.id.textName)
        TextView textName;
        @BindView(R.id.textDob)
        TextView textDob;

        public ActorsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        public void setActors(Actors actors) {
            this.actors = actors;
        }

        @Override
        public void onClick(View view) {
           itemClickListener.onItemClickListener(view, actors);
        }
    }
}
