package com.intent.pegawai.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.intent.pegawai.activity.DetailActivity;
import com.intent.pegawai.helper.CustomOnItemClickListener;
import com.intent.pegawai.model.Foodie;
import com.intent.pegawai.R;

import java.util.ArrayList;

public class CardViewFoodieAdapter extends RecyclerView.Adapter<CardViewFoodieAdapter.CardViewViewHolder> {
    private ArrayList<Foodie> listFoodie;
    private Context context;
    private Activity activity;


    public CardViewFoodieAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<Foodie> getListFoodie() {
        return listFoodie;
    }

    public void setListFoodie(ArrayList<Foodie> listFoodie) {
        this.listFoodie = listFoodie;
    }

    @Override
    public CardViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_ajaib, parent, false);
        return new CardViewViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardViewViewHolder holder, int position) {
        final Foodie a = getListFoodie().get(position);

        Glide.with(context)
                .load(a.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);

        holder.tvName.setText(a.getTitle());
        holder.tvRemarks.setText(a.getDepartment());
        holder.foodie = a;

        holder.btnShare.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View v, int position) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TITLE, a.getTitle());
                intent.putExtra(Intent.EXTRA_SUBJECT, a.getDepartment());
                intent.putExtra(Intent.EXTRA_TEXT, a.getTitle() + "\n\n" + a.getContent());
                context.startActivity(Intent.createChooser(intent, context.getResources().getString(R.string.share)));
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getListFoodie().size();
    }

    public void startactivity(Intent intent) {

    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPhoto;
        TextView tvName, tvRemarks;
        Button btnDetail, btnShare;
        Foodie foodie;

        public CardViewViewHolder(View v) {
            super(v);
            imgPhoto = v.findViewById(R.id.img_item_photo);
            imgPhoto.setOnClickListener(this);
            tvName = v.findViewById(R.id.tv_item_name);
            tvName.setOnClickListener(this);
            tvRemarks = v.findViewById(R.id.tv_item_remarks);
            btnDetail = v.findViewById(R.id.btn_set_detail);
            btnDetail.setOnClickListener(this);
            btnShare = v.findViewById(R.id.btn_set_share);

        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("key", foodie);
            context.startActivity(intent);
        }
    }

}
