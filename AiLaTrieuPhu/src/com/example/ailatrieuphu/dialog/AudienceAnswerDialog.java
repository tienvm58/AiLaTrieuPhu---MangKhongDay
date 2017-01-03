package com.example.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.example.ailatrieuphu.fragment.GameFragment;
import com.example.ailatrieuphu.R;


public class AudienceAnswerDialog extends Dialog {

    private int trueAnswer;
    private TextView tvPercentCaseA;
    private TextView tvPercentCaseB;
    private TextView tvPercentCaseC;
    private TextView tvPercentCaseD;



    public AudienceAnswerDialog(Context context, int trueAnswer) {
        super(context);
        this.trueAnswer = trueAnswer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audienanswerdialog);
        initView();
    }

    private void initView() {
        tvPercentCaseA = (TextView) findViewById(R.id.tv_percent_case_a);
        tvPercentCaseB = (TextView) findViewById(R.id.tv_percent_case_b);
        tvPercentCaseC = (TextView) findViewById(R.id.tv_percent_case_c);
        tvPercentCaseD = (TextView) findViewById(R.id.tv_percent_case_d);

        // caculate 100dp to pixel
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int dps = 100;
        int pixelsTrue = (int) (dps * scale + 0.5f);
        dps = 25;
        int pixelsFalse1 = (int) (dps * scale + 0.5f);
        int pixelsFalse2 = (int) (dps * scale + 0.5f);
        int pixelsFalse3 = (int) (dps * scale + 0.5f);
        tvPercentCaseA.getLayoutParams().height = pixelsFalse1;
        tvPercentCaseB.getLayoutParams().height = pixelsFalse2;
        tvPercentCaseC.getLayoutParams().height = pixelsFalse3;
        tvPercentCaseD.getLayoutParams().height = pixelsFalse1;

        switch (trueAnswer) {
            case GameFragment.CASE_A:
                tvPercentCaseA.getLayoutParams().height = pixelsTrue;
                break;
            case GameFragment.CASE_B:
                tvPercentCaseB.getLayoutParams().height = pixelsTrue;
                break;
            case GameFragment.CASE_C:
                tvPercentCaseC.getLayoutParams().height = pixelsTrue;
                break;
            case GameFragment.CASE_D:
                tvPercentCaseD.getLayoutParams().height = pixelsTrue;
                break;
            default:
                break;
        }

    }
}
