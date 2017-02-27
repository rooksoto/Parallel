package com.rooksoto.parallel.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.fragments.activityStart.FragmentStartQuestions;
import com.rooksoto.parallel.fragments.activityStart.FragmentStartWelcome;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.CustomSoundEffects;

public class ActivityStart extends AppCompatActivity {
    private int containerID = R.id.activity_start_fragment_container;
    private CustomSoundEffects mCustomSoundEffects;
    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initialize();
        loadFragmentWelcome();
    }

    private void initialize () {
        mCustomSoundEffects = new CustomSoundEffects(getWindow().getDecorView().getRootView());
    }

    private void loadFragmentWelcome () {
        FragmentStartWelcome mFragmentStartWelcome = new FragmentStartWelcome();
        getSupportFragmentManager().beginTransaction()
                .replace(containerID, mFragmentStartWelcome)
                .commit();
    }

    private void loadFragmentQuestions () {
        FragmentStartQuestions mFragmentStartQuestions = new FragmentStartQuestions();
        getSupportFragmentManager().beginTransaction()
                .replace(containerID, mFragmentStartQuestions)
                .commit();
    }

    @Override
    public void onBackPressed () {
        mCustomAlertDialog.exit(this);
        //super.onBackPressed();
    }
}
