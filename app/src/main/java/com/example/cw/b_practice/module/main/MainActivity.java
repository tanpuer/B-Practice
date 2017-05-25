package com.example.cw.b_practice.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.cw.b_practice.R;
import com.example.cw.b_practice.base.RxBaseActivity;
import com.example.cw.b_practice.module.mainfragment.MainFragment;
import com.example.cw.b_practice.module.offLine.OffLineActivity;
import com.example.cw.b_practice.util.ConstantsUtil;
import com.example.cw.b_practice.util.PreferencesUtil;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by cw on 2017/5/15.
 */

public class MainActivity extends RxBaseActivity implements IMainContract.IMainView, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    private static final String TAG = "MainActivity";
    private IMainContract.IMainPresenter mPresenter;
    private FrameLayout mContainer;
    private NavigationView mNavigationView;
    private CircleImageView head_avatar;
    private ImageView head_notify;
    private ImageView head_switch_mode;
    private DrawerLayout mDrawerLayout;

    private MainFragment mMainFragment;
    private Fragment[] mFragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter(savedInstanceState);
    }

    private void initPresenter(Bundle savedInstanceState) {
        if (mPresenter == null){
            mPresenter = new MainPresenter(this);
        }
        mPresenter.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mContainer = (FrameLayout) findViewById(R.id.container);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        //注意Navigation里的header menu获取
        View headerView = mNavigationView.getHeaderView(0);
        head_avatar = (CircleImageView) headerView.findViewById(R.id.header_avatar);
        head_notify = (ImageView) headerView.findViewById(R.id.iv_head_noftiy);
        head_switch_mode = (ImageView) headerView.findViewById(R.id.iv_head_switch_mode);
        head_avatar.setOnClickListener(this);
        head_notify.setOnClickListener(this);
        head_switch_mode.setOnClickListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        changeDayNightImage();
        initFragments();
        initNavigationView();
    }

    @Override
    public void initToolBar() {

    }

    private void initNavigationView() {

    }


    /**
     * 初始化fragment
     */
    private void initFragments() {
        mMainFragment = MainFragment.newInstance();

        Log.d(TAG, "initFragments: " + mMainFragment);

        //显示第一个fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, mMainFragment)
                .show(mMainFragment)
                .commit();
    }

    @Override
    public void setPresenter(IMainContract.IMainPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.item_download:{
                startActivity(new Intent(this, OffLineActivity.class));
                return true;
            }
            default:
                return false;
        }
    }

    @Override
    public void onClick(View v) {
        mPresenter.onClick(v);
    }

    @Override
    public void changeDayNightImage() {
        boolean isNight = PreferencesUtil.getBoolean(ConstantsUtil.SWITCH_MODE_KEY, false);
        if (isNight){
            head_switch_mode.setImageResource(R.drawable.ic_switch_night);
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            head_switch_mode.setImageResource(R.drawable.ic_switch_daily);
        }
    }

    @Override
    public void reCreateView() {
        // TODO: 2017/5/19 日夜间切换bug
        getSupportFragmentManager().beginTransaction().hide(mMainFragment).commit();
        getWindow().setWindowAnimations(R.style.windowFadeInOutAnimation);
        this.recreate();
    }
}
