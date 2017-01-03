package com.example.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.controller.SoundManager;


public class AudienceDiaglog extends Dialog implements View.OnClickListener {

    private ImageView ivDoctor;
    private ImageView ivProfessor;
    private ImageView ivEngineer;
    private ImageView ivMaster;
    private int trueAnswer;
    private SoundManager soundManager;

    public AudienceDiaglog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audiencedialog);
        initView();
    }

    private void initView() {
        soundManager = new SoundManager(getContext());

        ivDoctor = (ImageView) findViewById(R.id.iv_doctor);
        ivEngineer = (ImageView) findViewById(R.id.iv_engineer);
        ivMaster = (ImageView) findViewById(R.id.iv_master);
        ivProfessor = (ImageView) findViewById(R.id.iv_professor);

        ivDoctor.setOnClickListener(this);
        ivEngineer.setOnClickListener(this);
        ivMaster.setOnClickListener(this);
        ivProfessor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_doctor:
//                break;
            case R.id.iv_master:
//                break;
            case R.id.iv_professor:
//                break;
            case R.id.iv_engineer:
//                break;
            default:
                this.dismiss();
                AudienceAnswerDialog audienceAnswerDialog = new AudienceAnswerDialog(getContext(), trueAnswer);
                audienceAnswerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                audienceAnswerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                audienceAnswerDialog.show();
                soundManager.playSound(R.raw.hoi_y_kien_chuyen_gia_01b);
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    public void setTrueAnswer(int trueAnswer) {
        this.trueAnswer = trueAnswer;
    }
}
