package com.example.ailatrieuphu.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ailatrieuphu.activity.MyActivity;
import com.example.ailatrieuphu.R;

public class SplashScreenFragment extends Fragment implements Runnable {

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.splashscreen, container,false);
        Thread thread = new Thread(this);
        thread.start();
        return rootView;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((MyActivity) getActivity()).openMenuFragment();
    }
}
