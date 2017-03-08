package com.rooksoto.parallel.view.activityhub;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.R;

/**
 * Created by huilin on 3/4/17.
 */

public class FragmentAttendees extends Fragment implements FragmentAttendeesContract.View {

    private FragmentAttendeesContract.Presenter attendeePresenter;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_hub_screen_page, container, false);

        return rootView;
    }

    @Override
    public void setPresenter(FragmentAttendeesContract.Presenter presenter) {

        attendeePresenter = presenter;

    }

    @Override
    public void displayRv() {

    }

    @Override
    public void updateRv() {

    }

    @Override
    public void clickToSeeProfile() {

    }
}
