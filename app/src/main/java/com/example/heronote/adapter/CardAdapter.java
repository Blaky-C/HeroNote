package com.example.heronote.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heronote.DetailActivity;
import com.example.heronote.R;
import com.example.heronote.base.BaseApplication;
import com.example.heronote.bean.Note;
import com.example.heronote.util.DateUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Jack on 2018/2/14.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView day_of_month;
        TextView month_year;
        TextView day_of_week;
        TextView time;
        ImageView cover;
        TextView quote;
        Button more;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView)view;
            day_of_month = (TextView)view.findViewById(R.id.day_of_month);
            month_year = (TextView)view.findViewById(R.id.month_year);
            day_of_week = (TextView)view.findViewById(R.id.day_of_week);
            time = (TextView)view.findViewById(R.id.time);
            cover = (ImageView)view.findViewById(R.id.cover);
            quote = (TextView)view.findViewById(R.id.quote);
            more = (Button)view.findViewById(R.id.button_more);
        }
    }

    private List<Note> noteList = null;

    public CardAdapter(List<Note> noteList) {
        this.noteList = noteList;
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
                intent.putExtra("note_data", noteList.get(holder.getAdapterPosition()-1));
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
        final Note card = noteList.get(position);
        Date d = new Date(card.getTime());
        holder.day_of_month.setText(DateUtils.date2string(d, DateUtils.DD));
        holder.month_year.setText(DateUtils.date2string(d, "MM-yyyy"));
        holder.day_of_week.setText(DateUtils.date2string(d, DateUtils.E));
        holder.time.setText(DateUtils.date2string(d, "HH:mm"));

        Glide.with(BaseApplication.getContext()).load(card.getCoverPicPath()).into(holder.cover);
        holder.quote.setText(card.getQuote());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
