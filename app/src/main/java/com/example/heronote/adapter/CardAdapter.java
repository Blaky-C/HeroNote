package com.example.heronote.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heronote.activity.DetailActivity;
import com.example.heronote.R;
import com.example.heronote.base.BaseApplication;
import com.example.heronote.bean.Note;
import com.example.heronote.db.NoteDbOperate;
import com.example.heronote.util.DateUtils;
import com.example.heronote.util.Utils;

import java.util.Date;
import java.util.List;

/**
 * Created by Jack on 2018/2/14.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    private PopupMenu popupMenu;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView day_of_month;
        TextView month_year;
        TextView day_of_week;
        TextView time;
        ImageView cover;
        TextView quote;
        Button more;

        private ViewHolder(View view) {
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
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
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
            public void onClick(final View view) {
                popupMenu = new PopupMenu(parent.getContext(), view);
                popupMenu.inflate(R.menu.card_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.collect:
                                Utils.snackbar(view, "已收藏", "撤销", Utils.listenerToToast("已撤销"));
                                return true;
                            case R.id.delete:
                                final NoteDbOperate operator = new NoteDbOperate();
                                final int i = holder.getAdapterPosition()-1;
                                final Note note = noteList.get(i);
                                Utils.snackbar(view, "已删除", "撤销", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Utils.toast("已撤销");
                                        noteList.add(i, note);
                                        notifyDataSetChanged();
                                        operator.insertNote(note);
                                    }
                                });
                                noteList.remove(note);
                                notifyDataSetChanged();
                                operator.deleteNote(note.getTime());
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Note note = noteList.get(position);
        holder.day_of_month.setText(note.formatDate("dd"));
        holder.month_year.setText(note.formatDate("MM-yyyy"));
        holder.day_of_week.setText(note.formatDate("EEE"));
        holder.time.setText(note.formatDate("HH:mm"));

        Glide.with(BaseApplication.getContext()).load(note.getCoverPicPath()).into(holder.cover);
        holder.quote.setText(note.getQuote());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
