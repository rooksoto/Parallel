package com.rooksoto.parallel.activityhub;

import android.view.View;

import com.rooksoto.parallel.utility.geolocation.ParallelLocation;

public class ActivityHubPresenter {
    private ParallelLocation locationService = ParallelLocation.getInstance();
    private Listener listener;
    private View view;

    public ActivityHubPresenter(Listener listener) {
        this.listener = listener;
    }

    public void onInitialize() {
        locationService.connect();
        listener.checkLocationServices(locationService);
    }


    public void onStop() {
        listener.disconnectLocationService(locationService);
    }

    public void toViewPager(){
        listener.setupViewpager();
    }

    public void onStopOverride () {
        locationService.disconnect();
    }

    public void checkLocationServices (View viewP) {
        this.view = viewP;

        locationService = ParallelLocation.getInstance();
        locationService.connect();
        locationService.startGeofenceMonitoring(view.getContext());
    }

    public interface Listener {
        void setupViewpager();

        void checkLocationServices(ParallelLocation locationService);

        void disconnectLocationService(ParallelLocation locationService);

        void activateParallelEvent(String enteredEventID);

        void showEventIdError(String enteredEventID);

    }

}
