package com.example.ailatrieuphu.controller;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundManager {

    private static MediaPlayer mediaPlayer;
    private static MediaPlayer mediaPlayerBackground;
    private Context context;
    private int soundID;

    public SoundManager(Context context) {
        this.context = context;
    }

    public void playSound(int resourceId){
        if (mediaPlayer != null) mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(context,resourceId);
        mediaPlayer.start();
    }

    public void playSoundLoop(int resourceId) {
        if (mediaPlayer != null) mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(context,resourceId);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void playSoundBackground(int resourceId) {
        if (mediaPlayerBackground != null) mediaPlayerBackground.stop();
        mediaPlayerBackground = MediaPlayer.create(context,resourceId);
        mediaPlayerBackground.start();
    }

    public void playSoundBackgroundLoop(int resourceId) {
        if (mediaPlayerBackground != null) mediaPlayerBackground.stop();
        mediaPlayerBackground = MediaPlayer.create(context,resourceId);
        mediaPlayerBackground.setLooping(true);
        mediaPlayerBackground.start();
    }

    public void stopSound() {
        if (mediaPlayerBackground != null) mediaPlayerBackground.stop();
        if (mediaPlayer != null) mediaPlayer.stop();
    }
}
