package com.example.heronote.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heronote.DetailActivity;
import com.example.heronote.R;
import com.example.heronote.base.BaseActivity;
import com.example.heronote.base.BaseApplication;
import com.example.heronote.bean.CardInfo;

import java.util.List;

/**
 * Created by Jack on 2018/2/14.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView date;
        TextView day;
        TextView time;
        ImageView image;
        TextView quote;
        Button more;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            date = (TextView)view.findViewById(R.id.date);
            day = (TextView)view.findViewById(R.id.day);
            time = (TextView)view.findViewById(R.id.time);
            image = (ImageView)view.findViewById(R.id.img);
            quote = (TextView)view.findViewById(R.id.quote);
            more = (Button)view.findViewById(R.id.more);
        }
    }

    private List<CardInfo> cardList = null;

    public CardAdapter(List<CardInfo> cardList) {
        this.cardList = cardList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.card_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseApplication.getContext(), DetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                BaseApplication.getContext().startActivity(intent);
            }
        });

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*PopupMenu popup = new PopupMenu(BaseApplication.getContext(), view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.card_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.collect:
                                Toast.makeText(BaseApplication.getContext(), "已收藏", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.delete:
                                Toast.makeText(BaseApplication.getContext(), "已删除", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();*/
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CardInfo card = cardList.get(position);
        holder.date.setText(card.getDate());
        holder.day.setText(card.getDay());
        holder.time.setText(card.getTime());
        holder.image.setImageResource(card.getImg());
        holder.quote.setText(card.getQuote());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }
}
