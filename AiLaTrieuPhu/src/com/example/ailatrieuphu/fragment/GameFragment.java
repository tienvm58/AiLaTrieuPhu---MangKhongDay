package com.example.ailatrieuphu.fragment;

import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.example.ailatrieuphu.activity.MyActivity;
import com.example.ailatrieuphu.controller.QuestionManager;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.controller.SoundManager;
import com.example.ailatrieuphu.dialog.AudienceDiaglog;
import com.example.ailatrieuphu.dialog.CallDiaglog;
import com.example.ailatrieuphu.dialog.ConfirmDialog;
import com.example.ailatrieuphu.dialog.ScoreDiaglog;
import com.example.ailatrieuphu.model.ItemQuestion;

public class GameFragment extends Fragment implements View.OnClickListener, Animation.AnimationListener {

    public final static String SCORE_LEVEL_0 = "0";
    public final static String SCORE_LEVEL_1 = "200,000";
    public final static String SCORE_LEVEL_2 = "400,000";
    public final static String SCORE_LEVEL_3 = "600,000";
    public final static String SCORE_LEVEL_4 = "1,000,000";
    public final static String SCORE_LEVEL_5 = "2,000,000";
    public final static String SCORE_LEVEL_6 = "3,000,000";
    public final static String SCORE_LEVEL_7 = "6,000,000";
    public final static String SCORE_LEVEL_8 = "10,000,000";
    public final static String SCORE_LEVEL_9 = "14,000,000";
    public final static String SCORE_LEVEL_10 = "22,000,000";
    public final static String SCORE_LEVEL_11 = "30,000,000";
    public final static String SCORE_LEVEL_12 = "40,000,000";
    public final static String SCORE_LEVEL_13 = "60,000,000";
    public final static String SCORE_LEVEL_14 = "85,000,000";
    public final static String SCORE_LEVEL_15 = "150,000,000";

    public static final int CASE_A = 1;
    public static final int CASE_B = 2;
    public static final int CASE_C = 3;
    public static final int CASE_D = 4;

    private static final String SET_BACKGROUND = "SET_BACKGROUND";
    private static final String CASE_A_BACKGROUND = "CASE_A_BACKGROUND";
    private static final String CASE_B_BACKGROUND = "CASE_B_BACKGROUND";
    private static final String CASE_C_BACKGROUND = "CASE_C_BACKGROUND";
    private static final String CASE_D_BACKGROUND = "CASE_D_BACKGROUND";
    private static final String KEY_CHECK_DAP_AN = "KEY_CHECK_DAP_AN";
    private static final String KEY_NEXT_QUESTION = "KEY_NEXT_QUESTION";
    private static final String NEXT_QUESTION = "NEXT_QUESTION";
    private static final String KEY_VICTORY = "KEY_VICTORY";
    private static final String VICTORY = "VICTORY";

    private AsyncTask<Integer, Integer, Integer> asyncTask;
    private int time;

    private TextView tvScore;
    private TextView tvTime;
    private TextView tvCauHoi;
    private TextView tvQuestionNumber;
    private TextView tvDapAnA;
    private TextView tvDapAnB;
    private TextView tvDapAnC;
    private TextView tvDapAnD;
    private FrameLayout layoutCauHoi;

    private Button btDungCuocChoi;
    private Button btGoiDienThoai;
    private Button btDoiCauHoi;
    private Button btNamMuoiNamMuoi;
    private Button btHoiYKienKhanGia;

    private boolean caseIsChose = false;

    private ItemQuestion currentQuestion;
    private int questionIndex;
    private QuestionManager questionManager;

    private boolean isRunning;
    private boolean isPause;
    private View rootView;

    private SoundManager soundManager;

    public static String getScore(int level) {
        switch (level) {
            case 0:
                return SCORE_LEVEL_0;
            case 1:
                return SCORE_LEVEL_1;
            case 2:
                return SCORE_LEVEL_2;
            case 3:
                return SCORE_LEVEL_3;
            case 4:
                return SCORE_LEVEL_4;
            case 5:
                return SCORE_LEVEL_5;
            case 6:
                return SCORE_LEVEL_6;
            case 7:
                return SCORE_LEVEL_7;
            case 8:
                return SCORE_LEVEL_8;
            case 9:
                return SCORE_LEVEL_9;
            case 10:
                return SCORE_LEVEL_10;
            case 11:
                return SCORE_LEVEL_11;
            case 12:
                return SCORE_LEVEL_12;
            case 13:
                return SCORE_LEVEL_13;
            case 14:
                return SCORE_LEVEL_14;
            case 15:
                return SCORE_LEVEL_15;
        }
        return "null";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.game, container, false);
        questionManager = new QuestionManager(getActivity().getApplicationContext());
        questionIndex = 0;
        initView();
        isRunning = true;
        isPause = false;
        currentQuestion = questionManager.getItemQuestion(questionIndex);
        startGame();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void initView() {
        soundManager = new SoundManager(getActivity().getApplicationContext());
        soundManager.stopSound();
        soundManager.playSoundBackgroundLoop(R.raw.background_music);

        tvQuestionNumber = (TextView) rootView.findViewById(R.id.tv_question_number);

        tvCauHoi = (TextView) rootView.findViewById(R.id.tv_cau_hoi);
        tvDapAnA = (TextView) rootView.findViewById(R.id.tv_dap_an_a);
        tvDapAnB = (TextView) rootView.findViewById(R.id.tv_dap_an_b);
        tvDapAnC = (TextView) rootView.findViewById(R.id.tv_dap_an_c);
        tvDapAnD = (TextView) rootView.findViewById(R.id.tv_dap_an_d);

        tvDapAnA.setOnClickListener(this);
        tvDapAnB.setOnClickListener(this);
        tvDapAnC.setOnClickListener(this);
        tvDapAnD.setOnClickListener(this);

        tvDapAnA.setVisibility(View.INVISIBLE);
        tvDapAnB.setVisibility(View.INVISIBLE);
        tvDapAnC.setVisibility(View.INVISIBLE);
        tvDapAnD.setVisibility(View.INVISIBLE);

        layoutCauHoi = (FrameLayout) rootView.findViewById(R.id.layout_cau_hoi);
        layoutCauHoi.setVisibility(View.INVISIBLE);

        tvScore = (TextView) rootView.findViewById(R.id.tv_score);
        tvScore.setText(getScore(questionIndex) + " ");
        tvTime = (TextView) rootView.findViewById(R.id.tv_time);

        btDungCuocChoi = (Button) rootView.findViewById(R.id.bt_dung_cuoc_choi);
        btDoiCauHoi = (Button) rootView.findViewById(R.id.bt_doi_cau_hoi);
        btGoiDienThoai = (Button) rootView.findViewById(R.id.bt_goi_dien_thoai);
        btHoiYKienKhanGia = (Button) rootView.findViewById(R.id.bt_hoi_y_kien_khan_gia);
        btNamMuoiNamMuoi = (Button) rootView.findViewById(R.id.bt_nam_muoi_nam_muoi);

        btDungCuocChoi.setOnClickListener(this);
        btDoiCauHoi.setOnClickListener(this);
        btGoiDienThoai.setOnClickListener(this);
        btHoiYKienKhanGia.setOnClickListener(this);
        btNamMuoiNamMuoi.setOnClickListener(this);
    }

    private void resetTime() {
        time = 30;
        tvTime.setText(time + "");
        loadQuestion();
    }

    private ScoreDiaglog scoreDiaglog;
    private static final String KEY_SCORE_DIALOG = "SCORE_DIALOG";
    private static final String SCORE_DIALOG_SHOW = "SCORE_DIALOG_SHOW";
    private static final String SCORE_DIALOG_DISMISS = "SCORE_DIALOG_DISMISS";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String messengerShowScoreDialog = msg.getData().getString(KEY_SCORE_DIALOG);
            if (messengerShowScoreDialog != null)
                if (messengerShowScoreDialog.equals(SCORE_DIALOG_SHOW)) {
                    if (scoreDiaglog == null) {
                        scoreDiaglog = new ScoreDiaglog(getActivity());
                        scoreDiaglog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        scoreDiaglog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    }
//                scoreDiaglog.show();
                    scoreDiaglog.setQuestionNumber(questionIndex + 1);
                    scoreDiaglog.appear();
                } else if (messengerShowScoreDialog.equals(SCORE_DIALOG_DISMISS)) {
//                scoreDiaglog.dismiss();
                    scoreDiaglog.disappear();
                    prepareQuestion();
                }

            int dapAnCheck = msg.getData().getInt(KEY_CHECK_DAP_AN, -1);
            if (dapAnCheck > -1) checkDapAnPart2(dapAnCheck);

            String nextQuestion = msg.getData().getString(KEY_NEXT_QUESTION);
            if (nextQuestion != null)
                if (nextQuestion.equals(NEXT_QUESTION)) nextQuestion();

            String victory = msg.getData().getString(KEY_VICTORY);
            if (victory != null)
                if (victory.equals(VICTORY)) victorypart2();
        }
    };

    public void startGame() {
        asyncTask = new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected void onPreExecute() {
                isRunning = true;
                resetTime();
            }

            @Override
            protected Integer doInBackground(Integer... params) {
                while (isRunning) {
                    try {
                        Thread.sleep(1000);
                        if (!isPause) time--;
                        publishProgress(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return 0;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                if (values[0] <= 0) {
                    isRunning = false;
                }
                tvTime.setText(values[0] + "");
            }

            @Override
            protected void onPostExecute(Integer i) {
                super.onPostExecute(i);
                soundManager.playSound(R.raw.lose2);
                ((MyActivity) getActivity()).openMenuFragment();
            }
        };
        asyncTask.execute();
    }

    private void loadQuestion() {
        Thread threadShowScoreDialog = new Thread() {
            @Override
            public void run() {
                Log.i("bug", "GameFragment thread() line 0");
                isPause = true;
                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putString(KEY_SCORE_DIALOG, SCORE_DIALOG_SHOW);
                msg.setData(bundle);
                msg.setTarget(handler);
                msg.sendToTarget();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bundle = new Bundle();
                bundle.putString(KEY_SCORE_DIALOG, SCORE_DIALOG_DISMISS);
                msg = new Message();
                msg.setData(bundle);
                msg.setTarget(handler);
                msg.sendToTarget();
                isPause = false;
            }
        };
        threadShowScoreDialog.start();
//        prepareQuestion();
    }

    private Animation caseAAppearAnimation;
    private Animation caseBAppearAnimation;
    private Animation caseCAppearAnimation;
    private Animation caseDAppearAnimation;
    private Animation appearCombo;

    private void prepareQuestion() {
        isPause = false;
        tvQuestionNumber.setText("Câu " + (questionIndex + 1));

        if (MenuFragment.hack) tvCauHoi.setText(currentQuestion.getQuestion()+ ". " + currentQuestion.getTrueCase());
        else tvCauHoi.setText(currentQuestion.getQuestion());

        if (appearCombo == null) appearCombo = AnimationUtils.loadAnimation(getActivity(),R.anim.appearcombo);
        layoutCauHoi.startAnimation(appearCombo);
        layoutCauHoi.setVisibility(View.VISIBLE);

        if (caseAAppearAnimation == null) {
            caseAAppearAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.case_appear);
            caseAAppearAnimation.setAnimationListener(this);
        }
        if (caseBAppearAnimation == null) {
            caseBAppearAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.case_appear);
            caseBAppearAnimation.setAnimationListener(this);
        }
        if (caseCAppearAnimation == null) {
            caseCAppearAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.case_appear);
            caseCAppearAnimation.setAnimationListener(this);
        }
        if (caseDAppearAnimation == null) {
            caseDAppearAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.case_appear);
            caseDAppearAnimation.setAnimationListener(this);
        }

        tvDapAnA.setText("A." + currentQuestion.getCaseA());
        tvDapAnB.setText("B." + currentQuestion.getCaseB());
        tvDapAnC.setText("C." + currentQuestion.getCaseC());
        tvDapAnD.setText("D." + currentQuestion.getCaseD());

        tvDapAnA.setVisibility(View.INVISIBLE);
        tvDapAnB.setVisibility(View.INVISIBLE);
        tvDapAnC.setVisibility(View.INVISIBLE);
        tvDapAnD.setVisibility(View.INVISIBLE);

        tvDapAnA.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
        tvDapAnB.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
        tvDapAnC.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);
        tvDapAnD.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_normal);

        tvDapAnA.startAnimation(caseAAppearAnimation);

        enableAllButton();
        caseIsChose = false;
    }

    private void checkDapAn(int i) {
        final int dapAn = i;
        isPause = true;

        switch (i) {
            case ItemQuestion.CASE_A:
                soundManager.playSound(R.raw.ans_a);
                tvDapAnA.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_selected);
                break;
            case ItemQuestion.CASE_B:
                soundManager.playSound(R.raw.ans_b);
                tvDapAnB.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_selected);
                break;
            case ItemQuestion.CASE_C:
                soundManager.playSound(R.raw.ans_c);
                tvDapAnC.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_selected);
                break;
            case ItemQuestion.CASE_D:
                soundManager.playSound(R.raw.ans_d);
                tvDapAnD.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_selected);
                break;
        }

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putInt(KEY_CHECK_DAP_AN, dapAn);
                msg.setData(bundle);
                msg.setTarget(handler);
                msg.sendToTarget();
            }
        };
        thread.start();
    }

    private void goodAnswer(int i) {
        Animation fadeLoop = AnimationUtils.loadAnimation(getActivity(), R.anim.fadeloop);
        switch (i) {
            case ItemQuestion.CASE_A:
                tvDapAnA.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_true);
                tvDapAnA.startAnimation(fadeLoop);
                soundManager.playSound(R.raw.true_a);
                break;
            case ItemQuestion.CASE_B:
                tvDapAnB.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_true);
                tvDapAnB.startAnimation(fadeLoop);
                soundManager.playSound(R.raw.true_b);
                break;
            case ItemQuestion.CASE_C:
                tvDapAnC.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_true);
                tvDapAnC.startAnimation(fadeLoop);
                soundManager.playSound(R.raw.true_c);
                break;
            case ItemQuestion.CASE_D:
                tvDapAnD.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_true);
                tvDapAnD.startAnimation(fadeLoop);
                soundManager.playSound(R.raw.true_d2);
                break;
        }
    }

    private void badAnswer(int i){
        Animation fadeLoop = AnimationUtils.loadAnimation(getActivity(), R.anim.fadeloop);
        switch (i) {
            case ItemQuestion.CASE_A:
                tvDapAnA.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_wrong);
                break;
            case ItemQuestion.CASE_B:
                tvDapAnB.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_wrong);
                break;
            case ItemQuestion.CASE_C:
                tvDapAnC.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_wrong);
                break;
            case ItemQuestion.CASE_D:
                tvDapAnD.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_wrong);
                break;
        }
        int dapAnDung = currentQuestion.getTrueCase();
        switch (dapAnDung) {
            case ItemQuestion.CASE_A:
                tvDapAnA.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_true);
                tvDapAnA.startAnimation(fadeLoop);
                soundManager.playSound(R.raw.lose_a);
                break;
            case ItemQuestion.CASE_B:
                tvDapAnB.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_true);
                tvDapAnB.startAnimation(fadeLoop);
                soundManager.playSound(R.raw.lose_b);
                break;
            case ItemQuestion.CASE_C:
                tvDapAnC.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_true);
                tvDapAnC.startAnimation(fadeLoop);
                soundManager.playSound(R.raw.lose_c);
                break;
            case ItemQuestion.CASE_D:
                tvDapAnD.setBackgroundResource(R.drawable.atp__activity_player_layout_play_answer_background_true);
                tvDapAnD.startAnimation(fadeLoop);
                soundManager.playSound(R.raw.lose_d);
                break;
        }
    }

    private void victory(){
        goodAnswer(15);
        Thread threadVictory = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putString(KEY_VICTORY,VICTORY);
                msg.setData(bundle);
                msg.setTarget(handler);
                msg.sendToTarget();
            }
        };
        threadVictory.start();
    }

    private void victorypart2() {
        soundManager.playSound(R.raw.best_player);
        tvQuestionNumber.setText("VICTORY");
        tvCauHoi.setText("Bạn là người chơi xuất sắc");
        tvScore.setText(SCORE_LEVEL_15 + " ");
    }

    private void checkDapAnPart2(int i) {
        Animation fadeLoop = AnimationUtils.loadAnimation(getActivity(), R.anim.fadeloop);
        if (i == currentQuestion.getTrueCase()) {
            if (questionIndex < 14) {

                goodAnswer(i);

                Thread threadTrue = new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString(KEY_NEXT_QUESTION, NEXT_QUESTION);
                        message.setData(bundle);
                        message.setTarget(handler);
                        message.sendToTarget();
                    }
                };
                threadTrue.start();

            } else {
                victory();
//                goodAnswer(i);
            }
        } else {

            badAnswer(i);

            Thread threadLose = new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isRunning = false;
                }
            };
            threadLose.start();
        }
    }

    private void nextQuestion() {
        questionIndex++;
        currentQuestion = questionManager.getItemQuestion(questionIndex);
        resetTime();
        tvScore.setText(getScore(questionIndex) + " ");
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        soundManager.playSound(R.raw.touch_sound);
        switch (v.getId()) {
            case R.id.bt_dung_cuoc_choi:
                if (!caseIsChose) dungCuocChoi();
                break;
            case R.id.bt_doi_cau_hoi:
                if (!caseIsChose) doiCauHoi();
                break;
            case R.id.bt_goi_dien_thoai:
                if (!caseIsChose) goiDienThoai();
                break;
            case R.id.bt_hoi_y_kien_khan_gia:
                if (!caseIsChose) hoiYKienKhanGia();
                break;
            case R.id.bt_nam_muoi_nam_muoi:
                if (!caseIsChose) namMuoiNamMuoi();
                break;
            case R.id.tv_dap_an_a:
                caseIsChose = true;
                disableAllButton();
                checkDapAn(ItemQuestion.CASE_A);
                break;
            case R.id.tv_dap_an_b:
                caseIsChose = true;
                disableAllButton();
                checkDapAn(ItemQuestion.CASE_B);
                break;
            case R.id.tv_dap_an_c:
                caseIsChose = true;
                disableAllButton();
                checkDapAn(ItemQuestion.CASE_C);
                break;
            case R.id.tv_dap_an_d:
                caseIsChose = true;
                disableAllButton();
                checkDapAn(ItemQuestion.CASE_D);
                break;
        }
    }

    private void dungCuocChoi() {

        final ConfirmDialog confirmDialog = new ConfirmDialog(getActivity());
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt_yes:
                        isRunning = false;
                        confirmDialog.dismiss();
                        break;
                    case R.id.bt_no:
                        confirmDialog.dismiss();
                        break;
                }
            }
        };
        confirmDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        confirmDialog.show();
        confirmDialog.setNotification("Bạn muốn dừng cuộc chơi tại đây");
        confirmDialog.setYesNoButton("Đồng ý", "Bỏ qua", onClickListener);
    }

    private void namMuoiNamMuoi() {
        final ConfirmDialog confirmDialog = new ConfirmDialog(getActivity());
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt_yes:
                        soundManager.playSound(R.raw.sound5050);
                        btNamMuoiNamMuoi.setEnabled(false);
                        confirmDialog.dismiss();
                        isPause = true;
                        sleep(3000);
                        isPause = false;
                        if (questionManager.getItemQuestion(questionIndex).getTrueCase() % 2 == 0) {
                            tvDapAnA.setText("");
                            tvDapAnC.setText("");
                            tvDapAnA.setEnabled(false);
                            tvDapAnC.setEnabled(false);
                        } else {
                            tvDapAnB.setText("");
                            tvDapAnD.setText("");
                            tvDapAnB.setEnabled(false);
                            tvDapAnD.setEnabled(false);
                        }
                        break;
                    case R.id.bt_no:
                        confirmDialog.dismiss();
                        break;
                }
            }
        };
        confirmDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        confirmDialog.show();
        confirmDialog.setNotification("Bạn muốn sử dụng sự trợ giúp 50/50?");
        confirmDialog.setYesNoButton("Đúng", "Không", onClickListener);
    }

    private void hoiYKienKhanGia() {
        final ConfirmDialog confirmDialog = new ConfirmDialog(getActivity());
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt_yes:
                        confirmDialog.dismiss();
                        btHoiYKienKhanGia.setEnabled(false);
                        showAudienceDialog();
                        break;
                    case R.id.bt_no:
                        confirmDialog.dismiss();
                        break;
                }
            }
        };
        confirmDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        confirmDialog.show();
        confirmDialog.setNotification("Bạn muốn hỏi ý kiến khán giả trong trường quay?");
        confirmDialog.setYesNoButton("Đúng", "Không", onClickListener);
    }

    private void showAudienceDialog() {
        AudienceDiaglog audienceDiaglog = new AudienceDiaglog(getActivity());
        audienceDiaglog.setTrueAnswer(currentQuestion.getTrueCase());
        audienceDiaglog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        audienceDiaglog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        audienceDiaglog.show();
    }

    private void goiDienThoai() {
        final ConfirmDialog confirmDialog = new ConfirmDialog(getActivity());
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt_yes:
                        btGoiDienThoai.setEnabled(false);
                        soundManager.playSound(R.raw.help_call);
                        showCallDialog();
                        confirmDialog.dismiss();
                        break;
                    case R.id.bt_no:
                        confirmDialog.dismiss();
                        break;
                }
            }
        };
        confirmDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        confirmDialog.show();
        confirmDialog.setNotification("Bạn muốn sử dụng sự trợ giúp gọi điện thoại cho người thân?");
        confirmDialog.setYesNoButton("Đồng ý", "Bỏ qua", onClickListener);
    }

    private void showCallDialog() {
        CallDiaglog callDiaglog = new CallDiaglog(getActivity());
        callDiaglog.setTrueAnswer(currentQuestion.getTrueCase());
        callDiaglog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        callDiaglog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        callDiaglog.show();
    }

    private void doiCauHoi() {
        final ConfirmDialog confirmDialog = new ConfirmDialog(getActivity());
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.bt_yes:
                        btDoiCauHoi.setEnabled(false);
//                        currentQuestion = questionManager.getItemQuestion(15);
                        currentQuestion = questionManager.getItemQuestionAlternate(questionIndex);
                        resetTime();
                        confirmDialog.dismiss();
                        break;
                    case R.id.bt_no:
                        confirmDialog.dismiss();
                        break;
                }
            }
        };
        confirmDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        confirmDialog.show();
        confirmDialog.setNotification("Bạn muốn đổi câu hỏi?");
        confirmDialog.setYesNoButton("Đổi câu hỏi", "Không", onClickListener);
    }

    public void stop() {
        isRunning = false;
        asyncTask.cancel(true);
    }

    private void enableAllButton(){
        tvDapAnA.setEnabled(true);
        tvDapAnB.setEnabled(true);
        tvDapAnC.setEnabled(true);
        tvDapAnD.setEnabled(true);
    }

    private void disableAllButton(){
        tvDapAnA.setEnabled(false);
        tvDapAnB.setEnabled(false);
        tvDapAnC.setEnabled(false);
        tvDapAnD.setEnabled(false);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        if (animation.equals(caseAAppearAnimation)) tvDapAnA.setVisibility(View.VISIBLE);
        else if (animation.equals(caseBAppearAnimation)) tvDapAnB.setVisibility(View.VISIBLE);
        else if (animation.equals(caseCAppearAnimation)) tvDapAnC.setVisibility(View.VISIBLE);
        else if (animation.equals(caseDAppearAnimation)) tvDapAnD.setVisibility(View.VISIBLE);

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation.equals(caseAAppearAnimation)) tvDapAnB.startAnimation(caseBAppearAnimation);
        else if (animation.equals(caseBAppearAnimation)) tvDapAnC.startAnimation(caseCAppearAnimation);
        else if (animation.equals(caseCAppearAnimation)) tvDapAnD.startAnimation(caseDAppearAnimation);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
