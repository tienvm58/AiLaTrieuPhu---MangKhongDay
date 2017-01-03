package com.example.ailatrieuphu.controller;

import android.content.Context;
import android.util.Log;
import com.example.ailatrieuphu.model.DBManagerExactly;
import com.example.ailatrieuphu.model.ItemQuestion;
import com.example.ailatrieuphu.model.SQLcont;

import java.util.List;


public class QuestionManager {
    private List<ItemQuestion> arrsQuestion;
    private Context mContext;
    private DBManagerExactly dbManager;

    public QuestionManager(Context context) {
        Log.i("bug", "QuestionManager line 1");
        this.mContext = context;
        Log.i("bug", "QuestionManager initdata()");
        initData();
        Log.i("bug", "QuestionManager initdata();");
    }

    private void initData() {
        Log.i("bug", "QuestionManager initdata() line 1");
//        DBManager dbManager = new DBManager(mContext);
        dbManager = new DBManagerExactly(mContext);
        Log.i("bug", "QuestionManager initdata() line 2");
        arrsQuestion = dbManager.getArrsQuestion();
    }

   public ItemQuestion getItemQuestion(int i ) {
       return arrsQuestion.get(i);
   }

    public ItemQuestion getItemQuestionAlternate(int questionIndex) {
        ItemQuestion itemQuestion = null;
        switch (questionIndex) {
            case 0:
                itemQuestion = dbManager.getOnceQuestionTable(SQLcont.TABLE_QUESTION1);
                break;
            case 1:
                itemQuestion = dbManager.getOnceQuestionTable(SQLcont.TABLE_QUESTION2);
                break;
            case 2:
                itemQuestion = dbManager.getOnceQuestionTable(SQLcont.TABLE_QUESTION3);
                break;
            case 3:
                itemQuestion = dbManager.getOnceQuestionTable(SQLcont.TABLE_QUESTION4);
                break;
            case 4:
                itemQuestion = dbManager.getOnceQuestionTable(SQLcont.TABLE_QUESTION5);
                break;

            case 5:
                itemQuestion = dbManager.getOnceQuestionTable(SQLcont.TABLE_QUESTION6);
                break;
            case 6:
                itemQuestion = dbManager.getOnceQuestionTable(SQLcont.TABLE_QUESTION7);
                break;
            case 7:
                itemQuestion = dbManager.getOnceQuestionTable(SQLcont.TABLE_QUESTION8);
                break;
            case 8:
                itemQuestion = dbManager.getOnceQuestionTable(SQLcont.TABLE_QUESTION9);
                break;
            case 9:
                itemQuestion = dbManager.getOnceQuestionTable(SQLcont.TABLE_QUESTION10);
                break;

            case 10:
                itemQuestion = dbManager.getOnceQuestionTable(SQLcont.TABLE_QUESTION11);
                break;
            case 11:
                itemQuestion = dbManager.getOnceQuestionTable(SQLcont.TABLE_QUESTION12);
                break;
            case 12:
                itemQuestion = dbManager.getOnceQuestionTable(SQLcont.TABLE_QUESTION13);
                break;
            case 13:
                itemQuestion = dbManager.getOnceQuestionTable(SQLcont.TABLE_QUESTION14);
                break;
            case 14:
                itemQuestion = dbManager.getOnceQuestionTable(SQLcont.TABLE_QUESTION15);
                break;

            default:
                break;
        }
        return itemQuestion;
    }
}
