package com.example.heronote.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.heronote.R;
import com.example.heronote.adapter.NoteBriefAdapter;
import com.example.heronote.base.BaseApplication;
import com.example.heronote.bean.Note;
import com.example.heronote.db.NoteDbOperate;
import com.example.heronote.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */

public class Fragment1 extends Fragment {

    private List<Note> noteList = new ArrayList<>();
    private NoteBriefAdapter adapter;
    private NoteDbOperate operator = new NoteDbOperate();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_1, container, false);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(BaseApplication.getContext());
        recyclerView.setLayoutManager(layoutManager);
        initData();
        adapter = new NoteBriefAdapter(noteList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addNotesFromHttpToSQL();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

//        XRecyclerView xRecyclerView = (XRecyclerView)view.findViewById(R.id.x_recycler_view);
//        xRecyclerView.setLayoutManager(layoutManager);
//        xRecyclerView.setAdapter(noteBriefAdapter);
//        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//                initData();
//
//                noteBriefAdapter.notifyDataSetChanged();
//                xRecyclerView.refreshComplete();
//            }
//
//            @Override
//            public void onLoadMore() {
//                xRecyclerView.refreshComplete();
//            }
//        });

        return view;
    }

    private void initData() {
        noteList.clear();
        noteList.addAll(operator.queryNotesAll());
        if (noteList.size() < 3) {
            operator.insertNote(new Note(null, "我必须付出超出常人数倍的努力啊！", "绿谷出九", "http://www.craftmanjack.cn/home.jpg", "xxx"));
            operator.insertNote(new Note(1437027902781L, "22", "所谓英雄，乃是能够逐渐打破逆境的人！", "绿谷出九", R.drawable.img_2, "xxxx"));
            operator.insertNote(new Note(875921400000L, null, "你一定能够成为英雄。", "欧尔迈特", R.drawable.img_3, "xxxxx"));
            noteList.addAll(operator.queryNotesAll());
        }
//        adapter.notifyDataSetChanged();
//        初始化Fragment1中的RecyclerView
//        NoteDbOperate operator = new NoteDbOperate();
//        noteList = operator.queryNotesAll();
//        if (noteList.size() < 3) {
//            for (Note note : noteInit) {
//                operator.insertNote(note);
//            }
//            noteList = operator.queryNotesAll();
//        }
//        noteList.addAll(Arrays.asList(noteInit));
    }

    private void addNotesFromHttpToSQL() {
        Utils.sendOkHttpRequest("https://raw.githubusercontent.com/ChuniSaver/private/master/Hero(in)es/notes.json", new okhttp3.Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                List<Note> noteList = null;
                try {
                    noteList = new Gson().fromJson(response.body().string(), new TypeToken<List<Note>>() {}.getType());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (noteList != null) {
                    for (Note note : noteList) {
                        note.setTimeDate(new Date());
                        operator.insertNote(note);
                    }
                }
                initData();
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }
        });
    }

//    private void refreshNotes() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        initData();
//                        cardAdapter.notifyDataSetChanged();
//                        swipeRefresh.setRefreshing(false);
//                    }
//                });
//            }
//        }).start();
//    }

//    @Override
//    public void onResume() {
//        super.onResume();/*
//        initData();
//        *//*cardAdapter.notifyDataSetChanged();
//        xRecyclerView.refreshComplete();*/
//    }
}
