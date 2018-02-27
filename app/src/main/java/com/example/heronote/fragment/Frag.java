package com.example.heronote.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by SQS on 2018/2/23.
 */

public class Frag extends Fragment {

    private int layout;

    private Deed deed;

    private int id;

    private Object tag;

    private boolean idSet = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layout, container, false);
        if (idSet) {
            view.setId(id);
        }
        if (tag != null) {
            view.setTag(tag);
        }
        if (deed != null) {
            deed.runInFrag(view);
        }
        return view;
    }

    public Frag setIdForView(int id) {
        this.id = id;
        idSet = true;
        return this;
    }

    public Frag setTagForView(Object tag) {
        this.tag = tag;
        return this;
    }

    public Frag setLayout(int layout) {
        this.layout = layout;
        return this;
    }

    public Frag setDeed(Deed deed) {
        this.deed = deed;
        return this;
    }

    public static interface Deed {
        void runInFrag(View view);
    }
}