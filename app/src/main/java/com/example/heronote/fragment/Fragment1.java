package com.example.heronote.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heronote.R;
import com.example.heronote.adapter.CardAdapter;
import com.example.heronote.base.BaseApplication;
import com.example.heronote.bean.CardInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    private CardInfo[] cardInfos = {
            new CardInfo("22", "星期五", "2017-12  14:30", R.drawable.img_1, "我必须付出超出常人数倍的努力啊！"),
            new CardInfo("22", "星期五", "2017-12  14:30", R.drawable.img_2, "所谓英雄，乃是能够逐渐打破逆境的人！"),
            new CardInfo("22", "星期五", "2017-12  14:30", R.drawable.img_3, "你一定能够成为英雄。"),
    };
    private List<CardInfo> cardlist = new ArrayList<>();
    private CardAdapter cardAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        //初始化Fragment1中的RecyclerView
        for (int i=0;i<3;i++){
            cardlist.add(cardInfos[i]);
        }
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(BaseApplication.getContext());
        recyclerView.setLayoutManager(layoutManager);
        cardAdapter = new CardAdapter(cardlist);
        recyclerView.setAdapter(cardAdapter);

        return view;
    }

}
