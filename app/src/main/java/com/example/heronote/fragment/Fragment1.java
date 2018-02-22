package com.example.heronote.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heronote.R;
import com.example.heronote.adapter.CardAdapter;
import com.example.heronote.adapter.NoteAdapter;
import com.example.heronote.base.BaseApplication;
import com.example.heronote.bean.CardInfo;
import com.example.heronote.bean.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

//    private CardInfo[] cardInfos = {
//            new CardInfo("22", "星期五", "2017-12  14:30", R.drawable.img_1, "我必须付出超出常人数倍的努力啊！"),
//            new CardInfo("22", "星期五", "2017-12  14:30", R.drawable.img_2, "所谓英雄，乃是能够逐渐打破逆境的人！"),
//            new CardInfo("22", "星期五", "2017-12  14:30", R.drawable.img_3, "你一定能够成为英雄。"),
//    };
//    private List<CardInfo> cardlist = new ArrayList<>();
//    private CardAdapter cardAdapter;
//    private RecyclerView recyclerView;

//        @Override
//
//    }
//        return view;
//
//        recyclerView.setAdapter(cardAdapter);
//        cardAdapter = new CardAdapter(cardlist);
//        recyclerView.setLayoutManager(layoutManager);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(BaseApplication.getContext());
//        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
//        }
//            cardlist.add(cardInfos[i]);
//        for (int i=0;i<3;i++){
//        //初始化Fragment1中的RecyclerView
//
//        View view = inflater.inflate(R.layout.fragment_1, container, false);
//                             Bundle savedInstanceState) {
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,

    private List<Note> notes = Arrays.asList(
        new Note("我必须付出超出常人数倍的努力啊！", "绿谷出九", R.drawable.img_1),
        new Note("所谓英雄，乃是能够逐渐打破逆境的人！","All Might" , R.drawable.img_2),
        new Note("你一定能够成为英雄。", "All Might", R.drawable.img_3));
    private NoteAdapter noteAdapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(BaseApplication.getContext());
        recyclerView.setLayoutManager(layoutManager);
        noteAdapter = new NoteAdapter(notes);
        recyclerView.setAdapter(noteAdapter);
        return view;
    }

}
