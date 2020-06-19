package com.carersystem.carer.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.carersystem.carer.Model.Modalcarerview;
import com.carersystem.carer.Model.Modalhomecare;
import com.carersystem.carer.R;

import java.util.List;

public class CardAdapterCarer extends RecyclerView.Adapter<CardAdapterCarer.ViewHolder> {

    private Context context;

    SharedPreferences sp;
    SharedPreferences.Editor ed;

    //List of superHeroes
    List<Modalcarerview> Heroes;

    public CardAdapterCarer(List<Modalcarerview> Heroes, Context context){
        super();
        //Getting all the heroes
        this.Heroes = Heroes;
        this.context = context;

        sp=context.getSharedPreferences("CART",Context.MODE_PRIVATE);
        ed=sp.edit();
    }

    @Override
    public CardAdapterCarer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carervisit, parent, false);
        CardAdapterCarer.ViewHolder viewHolder = new CardAdapterCarer.ViewHolder(v);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(final CardAdapterCarer.ViewHolder holder, int position) {

        final Modalcarerview Hero = Heroes.get(position);



        holder.textcarename.setText(Hero.getCarer_name());
        holder.textcareremail.setText(Hero.getCarer_email());
        holder.textcarenamernum.setText(Hero.getCarer_phone());
        holder.textcarenamedate.setText(Hero.getStart_date());
        holder.textstime.setText(Hero.getStart_time());
        holder.textetime.setText(Hero.getEnd_time());





        // holder.imageView.setImageUrl(Hero.getImage(), imageLoader);


    }

    @Override
    public int getItemCount() {
        return Heroes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textcarename;
        public TextView textcareremail;


        public TextView textcarenamernum;

        public TextView textcarenamedate;
        public TextView textstime;
        public TextView textetime;




        public ViewHolder(View itemView) {
            super(itemView);


            textcarename= (TextView) itemView.findViewById(R.id.textcarername);
            textcareremail= (TextView) itemView.findViewById(R.id.textcareremail);

            textcarenamernum = (TextView) itemView.findViewById(R.id.textcarernumber);



            textcarenamedate= (TextView) itemView.findViewById(R.id.textcaredate);

            textstime= (TextView) itemView.findViewById(R.id.textcarestime);
            textetime= (TextView) itemView.findViewById(R.id.textcareretime);


        }
    }
}
