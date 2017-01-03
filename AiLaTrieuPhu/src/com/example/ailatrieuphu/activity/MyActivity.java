package com.example.ailatrieuphu.activity;

import android.app.Activity;
import android.os.Bundle;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.controller.SoundManager;
import com.example.ailatrieuphu.dialog.ScoreDiaglog;
import com.example.ailatrieuphu.fragment.GameFragment;
import com.example.ailatrieuphu.fragment.MenuFragment;
import com.example.ailatrieuphu.fragment.SplashScreenFragment;

public class MyActivity extends Activity {

    private SplashScreenFragment splashScreenFragment;
    private MenuFragment menuFragment;
    private GameFragment gameFragment;
    private ScoreDiaglog scoreDiaglog;
    private boolean gameFragmentIsOpen;
    private boolean menuFragmentIsOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        openSplashScreenFragment();
    }

    public void openScoreDialog() {
        scoreDiaglog = new ScoreDiaglog(this);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                scoreDiaglog.dismiss();
            }
        };
        thread.start();
    }

    public void openSplashScreenFragment() {
        splashScreenFragment = new SplashScreenFragment();
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, splashScreenFragment)
                .commit();
    }

    public void openMenuFragment() {
        menuFragment = new MenuFragment();
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, menuFragment)
                .commit();
        menuFragmentIsOpen = true;
    }

    public void openGameFragment() {
        gameFragment = new GameFragment();
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, gameFragment)
                .commit();
        gameFragmentIsOpen = true;
    }

    @Override
    public void onBackPressed() {
        if (gameFragmentIsOpen) {
            gameFragment.stop();
            openMenuFragment();
            gameFragmentIsOpen = false;
        }
        else super.onBackPressed();
    }

    @Override
    public void onStop() {
        super.onStop();
        SoundManager soundManager = new SoundManager(getApplicationContext());
        soundManager.stopSound();
    }
}
