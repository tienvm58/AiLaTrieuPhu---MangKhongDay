package com.example.ailatrieuphu.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import com.example.ailatrieuphu.activity.MyActivity;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.controller.SoundManager;
import com.example.ailatrieuphu.dialog.ConfirmDialog;
import com.example.ailatrieuphu.dialog.ScoreDiaglog;


public class MenuFragment extends Fragment implements View.OnClickListener {

    public static boolean hack = false;
    private static final String KEY_REQUEST = "key_request";
    private static final String OPEN_FRAGMENT = "open_fragment";
    private View rootView;
    private Button btChoiThu;
    private Button btChoiDon;
    private Button btThachDau;
    private Button btDangNhap;
    private SoundManager soundManager;
    private ScoreDiaglog scoreDiaglog;
    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.menu, container,false);
        initView();
        return rootView;
    }

    private void initView() {
        soundManager = new SoundManager(getActivity());
        soundManager.playSoundBackgroundLoop(R.raw.bgmusic);

        btChoiThu = (Button) rootView.findViewById(R.id.bt_choi_thu);
        btChoiDon = (Button) rootView.findViewById(R.id.bt_choi_don);
        btThachDau = (Button) rootView.findViewById(R.id.bt_thach_dau);
        btDangNhap = (Button) rootView.findViewById(R.id.bt_dang_nhap);

        btChoiThu.setOnClickListener(this);
        btChoiDon.setOnClickListener(this);
        btThachDau.setOnClickListener(this);
        btDangNhap.setOnClickListener(this);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                String request = msg.getData().getString(KEY_REQUEST);
                if (request != null)
                    if (request.equals(OPEN_FRAGMENT)) ((MyActivity) getActivity()).openGameFragment();
            }
        };
    }

    @Override
    public void onClick(View v) {
        hack = false;
        switch (v.getId()) {
            case R.id.bt_choi_thu:
                soundManager.playSound(R.raw.touch_sound);
                choiThu();
                break;
            case R.id.bt_choi_don:
                soundManager.playSound(R.raw.touch_sound);
                choiThu();
                hack = true;
                break;
            case R.id.bt_thach_dau:
                soundManager.playSound(R.raw.touch_sound);
                choiThu();
                break;
            case R.id.bt_dang_nhap:
                soundManager.playSound(R.raw.touch_sound);
                break;
            default:
                break;
        }
    }

    private boolean speakLaw = true;

    private void choiThu() {
        final ConfirmDialog confirmDialog = new ConfirmDialog(getActivity());
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt_yes:
                        confirmDialog.dismiss();
                        speakLaw = false;
                        soundManager.playSound(R.raw.gofind);
                        disapearAllButton();
                        threadOpenFragment.start();
                        break;
                    case R.id.bt_no:
                        confirmDialog.dismiss();
                        break;
                }
            }
        };
        confirmDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        confirmDialog.show();
        confirmDialog.setNotification("       Bạn đã sẵn sàng chơi chưa?     ");
        confirmDialog.setYesNoButton("Sẵn sàng", "Chưa", onClickListener);
    }

    private void disapearAllButton() {
        Animation disappearAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.disappearcombo);
        disappearAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btChoiThu.setVisibility(View.INVISIBLE);
                btDangNhap.setVisibility(View.INVISIBLE);
                btThachDau.setVisibility(View.INVISIBLE);
                btChoiDon.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        btChoiThu.startAnimation(disappearAnimation);
        btChoiDon.startAnimation(disappearAnimation);
        btThachDau.startAnimation(disappearAnimation);
        btDangNhap.startAnimation(disappearAnimation);
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    Thread threadOpenFragment = new Thread() {
        @Override
        public void run() {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putString(KEY_REQUEST,OPEN_FRAGMENT);
            msg.setData(bundle);
            msg.setTarget(handler);
            msg.sendToTarget();
        }
    };
}
