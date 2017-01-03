package com.example.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.controller.SoundManager;


public class ScoreDiaglog extends Dialog {

    private LinearLayout layoutScoreDialog;
    private TextView tvScoreLevel1;
    private TextView tvScoreLevel2;
    private TextView tvScoreLevel3;
    private TextView tvScoreLevel4;
    private TextView tvScoreLevel5;

    private TextView tvScoreLevel6;
    private TextView tvScoreLevel7;
    private TextView tvScoreLevel8;
    private TextView tvScoreLevel9;
    private TextView tvScoreLevel10;

    private TextView tvScoreLevel11;
    private TextView tvScoreLevel12;
    private TextView tvScoreLevel13;
    private TextView tvScoreLevel14;
    private TextView tvScoreLevel15;

    private int levelCurrent;

    private SoundManager soundManager;

    public ScoreDiaglog(Context context) {
        super(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        soundManager = new SoundManager(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scoredialog);
        initView();
    }

    private void initView() {
        layoutScoreDialog = (LinearLayout) findViewById(R.id.layout_score_dialog);

        tvScoreLevel1 = (TextView) findViewById(R.id.tv_score_level_1);
        tvScoreLevel2 = (TextView) findViewById(R.id.tv_score_level_2);
        tvScoreLevel3 = (TextView) findViewById(R.id.tv_score_level_3);
        tvScoreLevel4 = (TextView) findViewById(R.id.tv_score_level_4);
        tvScoreLevel5 = (TextView) findViewById(R.id.tv_score_level_5);

        tvScoreLevel6 = (TextView) findViewById(R.id.tv_score_level_6);
        tvScoreLevel7 = (TextView) findViewById(R.id.tv_score_level_7);
        tvScoreLevel8 = (TextView) findViewById(R.id.tv_score_level_8);
        tvScoreLevel9 = (TextView) findViewById(R.id.tv_score_level_9);
        tvScoreLevel10 = (TextView) findViewById(R.id.tv_score_level_10);

        tvScoreLevel11 = (TextView) findViewById(R.id.tv_score_level_11);
        tvScoreLevel12 = (TextView) findViewById(R.id.tv_score_level_12);
        tvScoreLevel13 = (TextView) findViewById(R.id.tv_score_level_13);
        tvScoreLevel14 = (TextView) findViewById(R.id.tv_score_level_14);
        tvScoreLevel15 = (TextView) findViewById(R.id.tv_score_level_15);
    }

    private void setCurrentLevel() {

        tvScoreLevel1.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
        tvScoreLevel2.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
        tvScoreLevel3.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
        tvScoreLevel4.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
        tvScoreLevel5.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));

        tvScoreLevel6.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
        tvScoreLevel7.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
        tvScoreLevel8.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
        tvScoreLevel9.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
        tvScoreLevel10.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));

        tvScoreLevel11.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
        tvScoreLevel12.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
        tvScoreLevel13.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
        tvScoreLevel14.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));
        tvScoreLevel15.setBackgroundColor(getContext().getResources().getColor(android.R.color.transparent));

        switch (levelCurrent) {
            case 1:
                tvScoreLevel1.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                break;
            case 2:
                tvScoreLevel2.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                break;
            case 3:
                tvScoreLevel3.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                break;
            case 4:
                tvScoreLevel4.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                break;
            case 5:
                tvScoreLevel5.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                break;
            case 6:
                tvScoreLevel6.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                break;
            case 7:
                tvScoreLevel7.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                break;
            case 8:
                tvScoreLevel8.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                break;
            case 9:
                tvScoreLevel9.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                break;
            case 10:
                tvScoreLevel10.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                break;
            case 11:
                tvScoreLevel11.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                break;
            case 12:
                tvScoreLevel12.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                break;
            case 13:
                tvScoreLevel13.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                break;
            case 14:
                tvScoreLevel14.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                break;
            case 15:
                tvScoreLevel15.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                break;
        }
    }

    public void setQuestionNumber(int level) {
        this.levelCurrent = level;
    }

    public void appear() {
        show();
        setCurrentLevel();
        Animation dialogAppear = AnimationUtils.loadAnimation(getContext(), R.anim.dialog_appear);
        layoutScoreDialog.startAnimation(dialogAppear);
        switch (levelCurrent) {
            case 1:
                soundManager.playSound(R.raw.ques1);
                break;
            case 2:
                soundManager.playSound(R.raw.ques2);
                break;
            case 3:
                soundManager.playSound(R.raw.ques3);
                break;
            case 4:
                soundManager.playSound(R.raw.ques4);
                break;
            case 5:
                soundManager.playSound(R.raw.ques5);
                break;
            case 6:
                soundManager.playSound(R.raw.ques6);
                break;
            case 7:
                soundManager.playSound(R.raw.ques7);
                break;
            case 8:
                soundManager.playSound(R.raw.ques8);
                break;
            case 9:
                soundManager.playSound(R.raw.ques9);
                break;
            case 10:
                soundManager.playSound(R.raw.ques10);
                break;
            case 11:
                soundManager.playSound(R.raw.ques11);
                break;
            case 12:
                soundManager.playSound(R.raw.ques12);
                break;
            case 13:
                soundManager.playSound(R.raw.ques13);
                break;
            case 14:
                soundManager.playSound(R.raw.ques14);
                break;
            case 15:
                soundManager.playSound(R.raw.ques15);
                break;
        }
    }

    public void disappear() {
        Animation dialogDisappear = AnimationUtils.loadAnimation(getContext(), R.anim.dialog_disappear);
        dialogDisappear.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        layoutScoreDialog.startAnimation(dialogDisappear);
    }
}