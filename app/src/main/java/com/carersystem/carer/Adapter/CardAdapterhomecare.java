package com.carersystem.carer.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.carersystem.carer.Model.Modalcarerview;
import com.carersystem.carer.Model.Modalhomecare;
import com.carersystem.carer.R;

import java.util.List;

public class CardAdapterhomecare extends RecyclerView.Adapter<CardAdapterhomecare.ViewHolder>{


    public interface OnItemClickListener {
        void onItemClick(Modalhomecare item);
    }

    private Context context;

    private final OnItemClickListener listener;
    //List of superHeroes
    List<Modalhomecare> Hero;

    public CardAdapterhomecare(List<Modalhomecare> Hero, Context context, OnItemClickListener listener){
        super();
        //Getting all the superheroes
        this.Hero = Hero;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public CardAdapterhomecare.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.homecareservice, parent, false);
        CardAdapterhomecare.ViewHolder viewHolder = new CardAdapterhomecare.ViewHolder(v);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(final CardAdapterhomecare.ViewHolder holder, int position) {

        final Modalhomecare Hero1 = Hero.get(position);
        holder.bind(Hero.get(position), listener);




        holder.category.setText(Hero1.getCategory());
        holder.name.setText(Hero1.getName());
        holder.price.setText(Hero1.getPrice());
        holder.detail.setText(Hero1.getDetail());






        // holder.imageView.setImageUrl(Hero.getImage(), imageLoader);


    }

    @Override
    public int getItemCount() {
        return Hero.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView category;


        public TextView name;

        public TextView price;
        public TextView detail;





        public ViewHolder(View itemView) {
            super(itemView);


            category= (TextView) itemView.findViewById(R.id.cateogry);;

            name = (TextView) itemView.findViewById(R.id.name);



            price= (TextView) itemView.findViewById(R.id.price);

            detail= (TextView) itemView.findViewById(R.id.detail);


        }

        public void bind(final Modalhomecare item, final OnItemClickListener listener) {
            //name.setText(item.name);
            // Picasso.with(itemView.getContext()).load(item.imageUrl).into(image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //listener.onItemClick(item);
                    listener.onItemClick(item);
                }
            });

        }
    }


}
