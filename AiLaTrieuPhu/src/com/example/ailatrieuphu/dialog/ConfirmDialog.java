package com.example.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.ailatrieuphu.R;


public class ConfirmDialog extends Dialog {

    private Button btYes;
    private Button btNo;
    private TextView tvNotification;

    public ConfirmDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmdialog);
        this.setCancelable(false);
        initView();
    }

    private void initView() {
        tvNotification = (TextView) findViewById(R.id.tv_notification);
        btYes = (Button) findViewById(R.id.bt_yes);
        btNo = (Button) findViewById(R.id.bt_no);
    }

    public void setNotification(String notification) {
        tvNotification.setText(notification);
    }

    public void setYesNoButton(String textYes, String textNo, View.OnClickListener onClickListener) {
        btYes.setText(textYes);
        btNo.setText(textNo);

        btYes.setOnClickListener(onClickListener);
        btNo.setOnClickListener(onClickListener);
    }
}
