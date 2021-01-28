package com.tengshi.basetengshi;

import com.tengshi.basemodule.base.BaseActivity;
import com.tengshi.basemodule.databases.GoodsDao;
import com.tengshi.basemodule.databases.GoodsEntity;
import com.tengshi.basemodule.databases.RoomDataBases;
import com.tengshi.basemodule.retrofitNet.download.DownloadUtils;
import com.tengshi.basemodule.utils.LogUtils;
import com.tengshi.basemodule.widget.dialog.MDialog;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends BaseActivity {


    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        refreshView(STATE_LOAD_SUCCESS);
        mTvBaseTitle.setText("基础库");
        isMainActivity(true);
        UserBean bean = new UserBean();
        bean.setAge(123f);
        bean.setName("张三");
        bean.setPwd("1234523pwd");
        bean.setClasses(123.432);
        bean.setDebug(true);
        bean.setRule(13);
        GoodsDao dao = RoomDataBases.getInstance(getApplicationContext()).getDao();
        findViewById(R.id.btn_save).setOnClickListener(v -> {
            for (int i = 0; i < 5; i++) {
                GoodsEntity entity = new GoodsEntity();
                entity.setName("苹果" + i);
                entity.setPrice(13.3f);
                entity.setDetail("商品描述信息" + i);
                dao.insert(entity);
            }
        });
        findViewById(R.id.btn_1).setOnClickListener(v -> {
            List<GoodsEntity> all = dao.getAll();
            LogUtils.e(all);
        });
        findViewById(R.id.btn_2).setOnClickListener(v -> {
            MDialog mDialog = MDialog
                    .showLoading(MainActivity.this, "加载中...");
            mDialog.show();
            Observable.timer(10, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull Long aLong) {
                            mDialog.dismiss();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        });
        findViewById(R.id.btn_3).setOnClickListener(v -> {
        });
    }
}
