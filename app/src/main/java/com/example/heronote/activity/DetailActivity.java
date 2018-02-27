package com.example.heronote.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heronote.R;
import com.example.heronote.base.BaseActivity;
import com.example.heronote.bean.Note;
import com.example.heronote.util.CommonUtils;
import com.example.heronote.util.DateUtils;
import com.example.heronote.util.StringUtils;
import com.sendtion.xrichtext.RichTextView;

import java.io.File;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailActivity extends BaseActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    private TextView date;
    private TextView time;
    private ImageView coverPic;
    private TextView quote;
    private TextView quoteFrom;
    private RichTextView rtView;

    private ProgressDialog loadingDialog;
    private Subscription subsLoading;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.search:
                CommonUtils.showToast("You click Search!s");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Note note = getIntent().getParcelableExtra("note");

        transparentStatusBar();
        initActionBar(R.id.toolbar, "详情");

        initPage();
    }

    private void initPage(){
        date = (TextView)findViewById(R.id.date);
        time = (TextView)findViewById(R.id.time);
        coverPic = (ImageView)findViewById(R.id.cover);
        quote = (TextView)findViewById(R.id.quote);
        quoteFrom = (TextView)findViewById(R.id.quote_from);
        rtView = (RichTextView)findViewById(R.id.content);

        loadingDialog = new ProgressDialog(this);
        loadingDialog.setMessage("数据加载中...");
        loadingDialog.setCanceledOnTouchOutside(false);

        final Note note = (Note)getIntent().getParcelableExtra("note_data");
        Date current_date = new Date(note.getTimeMillis());
        date.setText(DateUtils.date2string(current_date, DateUtils.DD));
        time.setText(DateUtils.date2string(current_date, "yyyy.MM  ·  HH:mm  ·  EEEE"));
        Glide.with(DetailActivity.this).load(note.getCoverPicPath()).into(coverPic);
        quote.setText("“"+note.getQuote()+"”");
        quoteFrom.setText("——"+note.getQuoteFrom());

        rtView.post(new Runnable() {
            @Override
            public void run() {
                rtView.clearAllLayout();
                showDataSync(note.getContent());
            }
        });
    }

    /* 异步方式显示数据 */
    private void showDataSync(final String content){
        loadingDialog.show();

        subsLoading = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                showEditData(subscriber, content);
            }
        })
        .onBackpressureBuffer()
        .subscribeOn(Schedulers.io())//生产事件在io
        .observeOn(AndroidSchedulers.mainThread())//消费事件在UI线程
        .subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                loadingDialog.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                loadingDialog.dismiss();
                e.printStackTrace();
                CommonUtils.showToast("解析错误：图片不存在或已损坏");
            }

            @Override
            public void onNext(String text) {
                if (text.contains("/storage/emulated/")){
                    rtView.addImageViewAtIndex(rtView.getLastIndex(), text);
                } else {
                    rtView.addTextViewAtIndex(rtView.getLastIndex(), text);
                }
            }
        });

    }

    /**
     * 显示数据
     * @param html
     */
    private void showEditData(Subscriber<? super String> subscriber, String html) {
        try {
            List<String> textList = StringUtils.cutStringByImgTag(html);
            for (int i = 0; i < textList.size(); i++) {
                String text = textList.get(i);
                if (text.contains("<img") && text.contains("src=")) {
                    String imagePath = StringUtils.getImgSrc(text);
                    if (new File(imagePath).exists()) {
                        subscriber.onNext(imagePath);
                    } else {
                        CommonUtils.showToast("图片已丢失，请重新插入！");
                    }
                } else {
                    subscriber.onNext(text);
                }
            }
            subscriber.onCompleted();
        } catch (Exception e){
            e.printStackTrace();
            subscriber.onError(e);
        }
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//
//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
//
//        toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        actionBar = getSupportActionBar();
//        if (actionBar!=null){
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//        collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
//        //collapsingToolbar.setTitle("NNN");
//
//    }
}
