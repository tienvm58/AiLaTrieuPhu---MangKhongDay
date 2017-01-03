package com.example.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.example.ailatrieuphu.fragment.GameFragment;
import com.example.ailatrieuphu.R;

public class TrueAnswerDialog extends Dialog {

    private TextView tvAnswer;
    private int trueAnswer;

    public TrueAnswerDialog(Context context, int trueAnswer) {
        super(context);
        this.trueAnswer = trueAnswer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trueanswerdialog);
        initView();
    }

    private void initView() {
        tvAnswer = (TextView) findViewById(R.id.tv_true_answer);
        setAnswer(trueAnswer);
    }

    public void setAnswer(int trueAnswer) {
        switch (trueAnswer) {
            case GameFragment.CASE_A:
                tvAnswer.setText("A");
                break;
            case GameFragment.CASE_B:
                tvAnswer.setText("B");
                break;
            case GameFragment.CASE_C:
                tvAnswer.setText("C");
                break;
            case GameFragment.CASE_D:
                tvAnswer.setText("D");
                break;
        }
    }
}
