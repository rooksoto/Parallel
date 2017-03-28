package com.rooksoto.parallel.activitylogin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activitylogin.login.FragmentLoginLogin;
import com.rooksoto.parallel.activitylogin.splash.FragmentLoginSplash;
import com.rooksoto.parallel.activitylogin.wait.FragmentLoginWait;
import com.rooksoto.parallel.utility.Constants;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.firebase.CustomFirebaseMessagingService;

public class ActivityLogin extends AppCompatActivity implements BaseView {
    private ActivityLoginPresenter activityLoginPresenter = new ActivityLoginPresenter();
    private int containerID = R.id.activity_login_fragment_container;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getIntent().getExtras() != null && getIntent().getExtras().getBoolean(Constants.Exit, false)) {
            finishAffinity();
            finish();
        }
        initialize();
    }

    public void initialize () {
        CustomFirebaseMessagingService.giveView(getWindow().getDecorView().getRootView());
        setViews();
    }

    @Override
    public void setViews () {
        activityLoginPresenter.giveActivity(this, getWindow().getDecorView().getRootView());
        loadFragmentSplash();
    }

    private void loadFragmentSplash () {
        FragmentLoginSplash mFragmentLoginSplash = new FragmentLoginSplash();
        activityLoginPresenter.loadFragment(mFragmentLoginSplash,
                R.animator.animator_nothing, R.animator.animator_fade_out, containerID, "Splash");
    }

    @Override
    public void onBackPressed () {
        Fragment currentFrag = getFragmentManager().findFragmentById(containerID);
        if (currentFrag instanceof FragmentLoginWait || currentFrag instanceof FragmentLoginSplash) {
            CustomAlertDialog customAlertDialog = new CustomAlertDialog();
            customAlertDialog.exit(this);
        } else if (currentFrag instanceof FragmentLoginLogin) {
            getFragmentManager().beginTransaction().replace(containerID, new FragmentLoginSplash()).commit();
        } else {
            super.onBackPressed();
        }
    }
}
