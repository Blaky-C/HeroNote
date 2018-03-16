package com.example.heronote.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heronote.R;
import com.example.heronote.base.BaseActivity;
import com.example.heronote.base.BaseApplication;
import com.example.heronote.bean.Note;
import com.example.heronote.db.NoteDbOperate;
import com.example.heronote.util.CommonUtils;
import com.example.heronote.util.DateUtils;
import com.example.heronote.util.ImageUtils;
import com.example.heronote.util.LogUtils;
import com.example.heronote.util.MyGlideEngine;
import com.example.heronote.util.ScreenUtils;
import com.example.heronote.util.Utils;
import com.example.heronote.view.MyRichTextEditor;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.sendtion.xrichtext.SDCardUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EditActivity extends BaseActivity {

    private BoomMenuButton boomMenuButton;

    private ProgressDialog insertDialog;
    private SwitchCompat switchCompat;
    private EditText title;
    private TextView time;
    private TextInputEditText quote;
    private TextInputEditText quoteFrom;
    private ImageView imageView;
    private MyRichTextEditor etNewContent;

    private String coverPicPath;

    private final int[] imageResources = {R.mipmap.insert_photo, R.mipmap.insert_audio, R.mipmap.insert_location};
    private final int[] colorResources = {R.color.blueGreyPrimary, R.color.deepOrangePrimary, R.color.amberPrimary};
    private final int REQUEST_CODE_FOR_IMGS = 23;
    private final int REQUEST_CODE_FOR_COVER = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        setSwipeBack(false);
        transparentStatusBar();
        initActionBar(R.id.toolbar, "新建记录");

        initPage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
            case R.id.cancel:
                cancelAlert();
                return true;
            case R.id.save:
                saveNoteData();
                CommonUtils.showToast("You click save!");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            switch (requestCode){
                case 23:
                    insertImagesSync(data);
                    break;
                case 24:
                    List<Uri> selected = Matisse.obtainResult(data);
                    coverPicPath = SDCardUtil.getFilePathByUri(BaseApplication.getContext(), selected.get(0));
                    Glide.with(BaseApplication.getContext()).load(coverPicPath).into(imageView);
                    imageView.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    @Override
    protected void actionAfterPermiss(int requestCode) {
        super.actionAfterPermiss(requestCode);

        switch (requestCode){
            case 0:
                getImgWithMatisse(REQUEST_CODE_FOR_IMGS);
                break;
            case 1:
                getImgWithMatisse(REQUEST_CODE_FOR_COVER);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_cover_pic:
                checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 1);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        cancelAlert();
    }

    private void initPage(){
        title = (EditText)findViewById(R.id.et_new_title);
        switchCompat = (SwitchCompat)findViewById(R.id.switch_compat);
        quote = (TextInputEditText)findViewById(R.id.quote);
        quoteFrom = (TextInputEditText)findViewById(R.id.quote_from);
        time = (TextView)findViewById(R.id.tv_new_time);
        imageView = (ImageView)findViewById(R.id.cover_pic);
        etNewContent = (MyRichTextEditor)findViewById(R.id.et_new_content);

        Date date = new Date();
        time.setText(DateUtils.date2string(date, "yyyy-MM-dd  EEEE"));

        insertDialog = new ProgressDialog(this);
        insertDialog.setMessage("正在插入图片...");
        insertDialog.setCanceledOnTouchOutside(false);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    title.setVisibility(EditText.VISIBLE);
                    title.setFocusable(true);
                    title.setFocusableInTouchMode(true);
                    title.requestFocus();
                }else {
                    title.setVisibility(EditText.GONE);
                }
            }
        });

        boomMenuButton = (BoomMenuButton)findViewById(R.id.bmb);
        for (int i = 0; i < boomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            SimpleCircleButton.Builder builder = new SimpleCircleButton.Builder()
                    .normalImageRes(imageResources[i])
                    .pieceColorRes(R.color.grey_000)
                    .normalColorRes(colorResources[i])
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int i) {
                            switch (i){
                                case 0:
                                    checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 0);
                                    break;
                                case 1:
                                case 2:
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
            boomMenuButton.addBuilder(builder);
        }

        initListenerToThis(R.id.add_cover_pic);
    }

    private void cancelAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("取消编辑？");
//        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Utils.toast("已取消！");
                finish();
            }
        });
        builder.setNegativeButton("取消", null);
        builder.create().show();
    }

    private void getImgWithMatisse(int requestCode){
        switch (requestCode){
            case REQUEST_CODE_FOR_IMGS:
                Matisse.from(EditActivity.this)
                        .choose(MimeType.allOf())
                        .countable(true)
                        .maxSelectable(9)
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new MyGlideEngine())
                        .forResult(REQUEST_CODE_FOR_IMGS);
                etNewContent.requestFocus();
                break;
            case REQUEST_CODE_FOR_COVER:
                Matisse.from(EditActivity.this)
                        .choose(MimeType.allOf())
                        .countable(true)
                        .maxSelectable(1)
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new MyGlideEngine())
                        .forResult(REQUEST_CODE_FOR_COVER);
                break;
            default:
                break;
        }

    }

    /**
     * 异步方式插入图片
     */
    private void insertImagesSync(final Intent data){
        insertDialog.show();

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try{
                    etNewContent.measure(0, 0);
                    int width = ScreenUtils.getScreenWidth(EditActivity.this);
                    int height = ScreenUtils.getScreenHeight(EditActivity.this);
                    //ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    //可以同时插入多张图片
                    List<Uri> mSelected = Matisse.obtainResult(data);
                    ArrayList<String> photos = new ArrayList<String>();
                    for (Uri s: mSelected){
                        photos.add(SDCardUtil.getFilePathByUri(EditActivity.this, s));
                    }

                    for (String imagePath : photos) {
                        //Log.i("NewActivity", "###path=" + imagePath);
                        Bitmap bitmap = ImageUtils.getSmallBitmap(imagePath, width, height);//压缩图片
                        //bitmap = BitmapFactory.decodeFile(imagePath);
                        //imagePath = SDCardUtil.saveToSdCard(bitmap);
                        //Log.i("NewActivity", "###imagePath="+imagePath);
                        subscriber.onNext(imagePath);
                    }
                    subscriber.onCompleted();
                }catch (Exception e){
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
            })
            .onBackpressureBuffer()
            .subscribeOn(Schedulers.io())//生产事件在io
            .observeOn(AndroidSchedulers.mainThread())//消费事件在UI线程
            .subscribe(new Observer<String>() {
                @Override
                public void onCompleted() {
                    insertDialog.dismiss();
                    etNewContent.addEditTextAtIndex(etNewContent.getLastIndex(), " ");
                    CommonUtils.showToast("图片插入成功");
                }

                @Override
                public void onError(Throwable e) {
                    insertDialog.dismiss();
                    CommonUtils.showToast("图片插入失败:"+e.getMessage());
                    LogUtils.d("Matisse", e.getMessage());
                }

                @Override
                public void onNext(String imagePath) {
                    etNewContent.insertImage(imagePath, etNewContent.getMeasuredWidth());
                }
            });
    }

    /* 负责处理编辑数据提交等事宜，请自行实现 */
    private String getEditData() {
        List<MyRichTextEditor.EditData> editList = etNewContent.buildEditData();
        StringBuffer content = new StringBuffer();
        for (MyRichTextEditor.EditData itemData : editList) {
            if (itemData.inputStr != null) {
                content.append(itemData.inputStr);
                LogUtils.d("RichEditor", "commit inputStr=" + itemData.inputStr);
            } else if (itemData.imagePath != null) {
                content.append("<img src=\"").append(itemData.imagePath).append("\"/>");
                LogUtils.d("RichEditor", "commit imgePath=" + itemData.imagePath);
                //imageList.add(itemData.imagePath);
            }
        }
        return content.toString();
    }

    /* 保存数据 */
    private void saveNoteData() {
        Note note = new Note();

        if (switchCompat.isChecked()){
//            note.setHasTitleOrNot(true);
            note.setTitle(String.valueOf(title.getText()));
        }
        note.setQuote(quote.getText().toString());
        note.setQuoteFrom(quoteFrom.getText().toString());
        note.setTimeDate(new Date());
        note.setCoverPicPath(coverPicPath);
        note.setContent(getEditData());

        NoteDbOperate operator = new NoteDbOperate();
        operator.insertNote(note);
    }
}
