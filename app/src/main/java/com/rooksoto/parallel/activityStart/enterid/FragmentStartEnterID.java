package com.rooksoto.parallel.activityStart.enterid;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityStart.welcome.FragmentStartWelcome;

public class FragmentStartEnterID extends Fragment implements BaseView {
    private FragmentStartEnterIDPresenter fragmentStartEnterIDPresenter = new FragmentStartEnterIDPresenter();

    private View view;
    private EditText textViewEventID;
    private Button buttonEnter;

    private int containerID = R.id.activity_start_fragment_container;
    private String eventID;

    private FirebaseDatabase database;
    private DatabaseReference rootReference;

    @Override
    public void onStart() {
        super.onStart();
        database = FirebaseDatabase.getInstance();
        rootReference = database.getReference();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_start_enterid, container, false);
        initialize();
        return view;
    }

    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {
        textViewEventID = (EditText) view.findViewById(R.id.fragment_start_enterid_eventid);
        eventID = textViewEventID.getText().toString();

        textViewEventID.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    buttonEnter.performClick();
                    return false;
                }
                return false;
            }
        });

        buttonEnter = (Button) view.findViewById(R.id.enter_button);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                fragmentStartEnterIDPresenter.checkEventID(eventID, rootReference);
                fragmentStartEnterIDPresenter.setOnClickReplace(new FragmentStartWelcome(), buttonEnter, containerID, "Questions");
            }
        });
    }

    @Override
    public void onBackPressed () {
        fragmentStartEnterIDPresenter.onBackPressedOverride(view);
    }

}