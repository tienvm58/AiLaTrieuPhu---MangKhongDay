package com.example.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import com.example.ailatrieuphu.R;


public class CallDiaglog extends Dialog implements View.OnClickListener {

    private ImageView ivDoctor;
    private ImageView ivProfessor;
    private ImageView ivEngineer;
    private ImageView ivMaster;
    private int trueAnswer;

    public CallDiaglog(Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calldialog);
        initView();
    }

    private void initView() {
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
                TrueAnswerDialog trueAnswerDialog = new TrueAnswerDialog(getContext(), trueAnswer);
                trueAnswerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                trueAnswerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                trueAnswerDialog.show();
                this.dismiss();
                break;
        }
    }

    public void setTrueAnswer(int trueAnswer) {
        this.trueAnswer = trueAnswer;
    }
}
