package com.rooksoto.parallel.activitylogin.createaccount;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activitylogin.login.FragmentLoginLogin;
import com.rooksoto.parallel.utility.AppContext;

public class FragmentLoginCreateAccount extends Fragment implements BaseView {
    private FragmentLoginCreateAccountPresenter fragmentLoginCreateAccountPresenter = new FragmentLoginCreateAccountPresenter();

    private View view;
    private int containerID = R.id.activity_login_fragment_container;
    private final String TAG = getClass().toString();
    private String email;
    private String username;
    private String password;
    private FirebaseAuth.AuthStateListener authListener;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login_createaccount, container, false);
        initialize();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }


    @Override
    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {
        final EditText editTextEmail = (EditText) view.findViewById(R.id.fragment_login_createaccount_edittext_email);
        final EditText editTextUsername = (EditText) view.findViewById(R.id.fragment_login_createaccount_edittext_username);
        final EditText editTextPassword = (EditText) view.findViewById(R.id.fragment_login_createaccount_edittext_password);
        Button buttonCreateAccount = (Button) view.findViewById(R.id.fragment_login_createaccount_button_createaccount);

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                if (editTextEmail.getText().toString().isEmpty()) {
                    Toast.makeText(AppContext.getAppContext(), "Missing Email Address - Please Enter", Toast.LENGTH_SHORT).show();
                } else {
                    email = editTextEmail.getText().toString();
                }
                if (editTextUsername.getText().toString().isEmpty()) {
                    Toast.makeText(AppContext.getAppContext(), "Missing Username - Please Enter", Toast.LENGTH_SHORT).show();
                } else {
                    username = editTextUsername.getText().toString();
                }
                if (editTextPassword.getText().toString().isEmpty()) {
                    Toast.makeText(AppContext.getAppContext(), "Missing Password - Please Enter", Toast.LENGTH_SHORT).show();
                } else {
                    password = editTextPassword.getText().toString();
                }

                fragmentLoginCreateAccountPresenter.createNewAccount(email, username, password);
                fragmentLoginCreateAccountPresenter.setOnClickReplace(new FragmentLoginLogin(), view, containerID, "Login");
            }
        });
    }

    @Override
    public void onBackPressed () {
    }
}
