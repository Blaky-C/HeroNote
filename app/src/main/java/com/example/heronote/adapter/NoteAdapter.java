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

import com.example.heronote.DetailActivity;
import com.example.heronote.R;
import com.example.heronote.base.BaseApplication;
import com.example.heronote.bean.Note;

import java.util.List;

/**
 * Created by SQS on 2018/2/23.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {


    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView dayOfMonth;
        TextView monthYear;
        TextView dayOfWeek;
        TextView time;
        ImageView cover;
        TextView slogan;
        Button buttonMore;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            dayOfMonth = (TextView) view.findViewById(R.id.day_of_month);
            monthYear = (TextView) view.findViewById(R.id.month_year);
            dayOfWeek = (TextView) view.findViewById(R.id.day_of_week);
            time = (TextView) view.findViewById(R.id.time);
            cover = (ImageView) view.findViewById(R.id.cover);
            slogan = (TextView) view.findViewById(R.id.slogan);
            buttonMore = (Button) view.findViewById(R.id.button_more);
        }
    }

    private List<Note> notes = null;

    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.card_item, parent, false);
        final NoteAdapter.ViewHolder holder = new NoteAdapter.ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseApplication.getContext(), DetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                BaseApplication.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(NoteAdapter.ViewHolder holder, int position) {
        final Note note = notes.get(position);
        holder.dayOfMonth.setText(note.formatDate("dd"));
        holder.monthYear.setText(note.formatDate("MM-yyyy"));
        holder.dayOfWeek.setText(note.formatDate("EEE"));
        holder.time.setText(note.formatDate("HH:mm"));
        holder.cover.setImageResource(note.getCover());
        holder.slogan.setText(note.getSlogan());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
