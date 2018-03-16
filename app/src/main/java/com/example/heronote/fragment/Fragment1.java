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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */

public class Fragment1 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private boolean flag = true;

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
        adapter = new NoteBriefAdapter(noteList);
        recyclerView.setAdapter(adapter);
        initData();

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

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

    @Override
    public void onRefresh() {
        Utils.sendOkHttpRequest("https://raw.githubusercontent.com/ChuniSaver/private/master/Hero(in)es/notes.json", new okhttp3.Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (flag) {
                    addNotesFromHttp(response);
                } else {
                    flag = true;
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    private void initData() {
        noteList.clear();
        noteList.addAll(operator.queryNotesAll());
        if (noteList.size() == 0) {
            operator.insertNote(new Note(null, "对不起，您指挥的喷火龙暂时不听话，请变强后再训练。", "喷火龙", "http://www.craftmanjack.cn/home.jpg", "xxxx"));
            operator.insertNote(new Note(1437027902781L,"22", "我必须付出超出常人数倍的努力啊！", "绿谷出久", R.drawable.img_1, "xxx"));
            operator.insertNote(new Note(875921400000L, null, "你一定能够成为英雄。", "欧尔迈特", R.drawable.img_3, "xxxxx"));
            noteList.addAll(operator.queryNotesAll());
        }
        adapter.notifyDataSetChanged();
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

    private void addNotesFromHttp(Response response) {
        try {
            List<Note> noteList = new Gson().fromJson(response.body().string(), new TypeToken<List<Note>>() {}.getType());
            int i = (int) (Math.random() * noteList.size());
            Note note = noteList.get(i);
            if (note.getTimeMillis() == 0L) {
                note.setTimeDate(new Date());
            }
            operator.insertNote(note);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
