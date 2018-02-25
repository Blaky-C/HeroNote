package com.example.heronote.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heronote.R;
import com.example.heronote.adapter.CardAdapter;
import com.example.heronote.base.BaseApplication;
import com.example.heronote.bean.Note;
import com.example.heronote.db.NoteDbOperate;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class Fragment1 extends Fragment {

    private Note[] noteInfos = {
            new Note(false, null, 1437027902781L, "我必须付出超出常人数倍的努力啊！", "绿谷出久", "xxxx", "http://www.craftmanjack.cn/home.jpg"),
            new Note(true, "Title", 1437027902781L, "所谓英雄，乃是能够逐渐打破逆境的人！", "绿谷出久", "xxxx", "http://www.craftmanjack.cn/home.jpg"),
            new Note(false, null, 1437027902781L, "你一定能够成为英雄。", "欧尔迈特", "xxxx", "http://www.craftmanjack.cn/home.jpg")
    };

    private List<Note> notelist = new ArrayList<>();
    private CardAdapter cardAdapter;
    private XRecyclerView xRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        xRecyclerView = (XRecyclerView)view.findViewById(R.id.x_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(BaseApplication.getContext());
        xRecyclerView.setLayoutManager(layoutManager);

        initData();

        cardAdapter = new CardAdapter(notelist);
        xRecyclerView.setAdapter(cardAdapter);

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initData();

                cardAdapter.notifyDataSetChanged();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xRecyclerView.refreshComplete();
            }
        });

        return view;
    }

    public void initData(){

        notelist.clear();

        //初始化Fragment1中的RecyclerView
        NoteDbOperate db_operator = new NoteDbOperate();
        List<Note> notes = db_operator.queryNotesAll();
        for (Note n: notes){
            notelist.add(n);
        }

        for (int i=0;i<3;i++){
            notelist.add(noteInfos[i]);
        }
    }
/*
    private void refreshNotes(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        cardAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }*/

    @Override
    public void onResume() {
        super.onResume();/*
        initData();
        *//*cardAdapter.notifyDataSetChanged();
        xRecyclerView.refreshComplete();*/
    }
}
