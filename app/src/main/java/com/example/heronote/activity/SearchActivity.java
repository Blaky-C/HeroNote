package com.example.heronote.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.heronote.R;
import com.example.heronote.base.BaseActivity;
import com.example.heronote.util.Utils;

public class SearchActivity extends BaseActivity implements FloatingSearchView.OnSearchListener, FloatingSearchView.OnFocusChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        transparentStatusBar();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        final FloatingSearchView searchView = (FloatingSearchView) findViewById(R.id.search_view);
//        searchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
//            @Override
//            public void onHomeClicked() {
//                onBackPressed();
//            }
//        });
//        searchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
//            @Override
//            public void onActionMenuItemSelected(MenuItem item) {
//                onSearchAction(searchView.getQuery());
//            }
//        });
        searchView.setOnFocusChangeListener(this);
        searchView.setOnSearchListener(this);
        searchView.setSearchFocused(true);
    }

    @Override
    public void onFocus() {

    }

    @Override
    public void onFocusCleared() {
        onBackPressed();
    }

    @Override
    public void onSearchAction(String currentQuery) {
        if (!currentQuery.equals("")) {
            Utils.toast(currentQuery);
        }
    }

    @Override
    public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

    }
}
