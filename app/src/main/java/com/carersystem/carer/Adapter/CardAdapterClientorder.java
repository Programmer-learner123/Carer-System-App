package com.carersystem.carer.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.carersystem.carer.Model.Modalorderview;
import com.carersystem.carer.R;

import java.util.List;

public class CardAdapterClientorder extends RecyclerView.Adapter<CardAdapterClientorder.ViewHolder> {

    private Context context;

    SharedPreferences sp;
    SharedPreferences.Editor ed;

    //List of superHeroes
    List<Modalorderview> Heroes;

    public CardAdapterClientorder(List<Modalorderview> Heroes, Context context) {
        super();
        //Getting all the heroes
        this.Heroes = Heroes;
        this.context = context;

        sp = context.getSharedPreferences("CART", Context.MODE_PRIVATE);
        ed = sp.edit();
    }

    @Override
    public CardAdapterClientorder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orderview, parent, false);
        CardAdapterClientorder.ViewHolder viewHolder = new CardAdapterClientorder.ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final CardAdapterClientorder.ViewHolder holder, int position) {

        final Modalorderview Hero = Heroes.get(position);


        holder.textorderid.setText(Hero.getId());
        holder.textName.setText(Hero.getName());
        holder.textEmailId.setText(Hero.getEmail());
        holder.Textcategory.setText(Hero.getCategory());
        holder.Textservices.setText(Hero.getServices());
        holder.Textitemdetail.setText(Hero.getDescription());
        holder.Textitemamount.setText(Hero.getPrice());

        holder.textdate.setText(Hero.getDate());


        // holder.imageView.setImageUrl(Hero.getImage(), imageLoader);


    }

    @Override
    public int getItemCount() {
        return Heroes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textorderid;
        public TextView textEmailId;
        public TextView textName;

        public TextView Textcategory;
        public TextView Textservices;


        public TextView Textitemdetail;
        public TextView Textitemamount;
        public TextView textdate;


        public ViewHolder(View itemView) {
            super(itemView);


            textorderid = (TextView) itemView.findViewById(R.id.orderId);
            textName = (TextView) itemView.findViewById(R.id.Name);
            textEmailId = (TextView) itemView.findViewById(R.id.careremailId);


            Textcategory = (TextView) itemView.findViewById(R.id.categoryName);
            Textservices = (TextView) itemView.findViewById(R.id.carerServices);


            Textitemamount = (TextView) itemView.findViewById(R.id.servicePrice);

            Textitemdetail = (TextView) itemView.findViewById(R.id.description);
            textdate = (TextView) itemView.findViewById(R.id.carerDate);


        }
    }
}