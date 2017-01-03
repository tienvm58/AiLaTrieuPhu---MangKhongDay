package com.example.ailatrieuphu.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class DBManagerExactly {

    private final static String DB_PATH = Environment.getDataDirectory().getPath() + "/data/com.example.ailatrieuphu/databases";
    private final static String DB_NAME = "Question";
    private Context mContext;
    private SQLiteDatabase sqlDB;

    private List<ItemQuestion> arrsQuestion = new ArrayList<>();

    public DBManagerExactly(Context mContext) {
        Log.i("bug", "DBManager line 1");
        this.mContext = mContext;
        Log.i("bug", "DBManager copyDB()");
        copyDB();
        Log.i("bug", "DBManager copyDB();");
    }

    public void copyDB() {
        //1. tao
        File file = new File(DB_PATH);
        file.mkdir();
        file = new File(DB_PATH + "/" + DB_NAME);
        if (file.exists()) return;
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            AssetManager assetManager = mContext.getAssets();
            InputStream input = assetManager.open(DB_NAME);
            FileOutputStream output = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                output.write(buffer, 0, len);
            }
            input.close();
            output.close();
            Log.i("bug", "DB is copied from asset to databases");
        } catch (FileNotFoundException e) {
            Log.i("bug", "DBManager copyDB() File not found exception");
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("bug", "DBManager copyDB() IOE exception");
            e.printStackTrace();
        }
    }

    public void openDB() {
        if (sqlDB == null || !sqlDB.isOpen())
            sqlDB = SQLiteDatabase.openDatabase(DB_PATH + "/" + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDB() {
        if (sqlDB != null && sqlDB.isOpen()) {
            sqlDB.close();
            sqlDB = null;
        }
    }

    public void getQuestion() {
        arrsQuestion.clear();
        // mo DB
        openDB();

        arrsQuestion.add(getQuestionTable(SQLcont.TABLE_QUESTION1));
        arrsQuestion.add(getQuestionTable(SQLcont.TABLE_QUESTION2));
        arrsQuestion.add(getQuestionTable(SQLcont.TABLE_QUESTION3));
        arrsQuestion.add(getQuestionTable(SQLcont.TABLE_QUESTION4));
        arrsQuestion.add(getQuestionTable(SQLcont.TABLE_QUESTION5));

        arrsQuestion.add(getQuestionTable(SQLcont.TABLE_QUESTION6));
        arrsQuestion.add(getQuestionTable(SQLcont.TABLE_QUESTION7));
        arrsQuestion.add(getQuestionTable(SQLcont.TABLE_QUESTION8));
        arrsQuestion.add(getQuestionTable(SQLcont.TABLE_QUESTION9));
        arrsQuestion.add(getQuestionTable(SQLcont.TABLE_QUESTION10));

        arrsQuestion.add(getQuestionTable(SQLcont.TABLE_QUESTION11));
        arrsQuestion.add(getQuestionTable(SQLcont.TABLE_QUESTION12));
        arrsQuestion.add(getQuestionTable(SQLcont.TABLE_QUESTION13));
        arrsQuestion.add(getQuestionTable(SQLcont.TABLE_QUESTION14));
        arrsQuestion.add(getQuestionTable(SQLcont.TABLE_QUESTION15));

        closeDB();
    }

    public ItemQuestion getOnceQuestionTable(String table) {
        openDB();
        Cursor cursor = sqlDB.rawQuery(SQLcont.queryQuestionTable(table), null);
        int indexId, indexQuestion, indexCaseA, indexCaseB, indexCaseC, indexCaseD, indexTrueCase;
        indexId = cursor.getColumnIndex("_id");
        indexCaseA = cursor.getColumnIndex("CaseA");
        indexCaseB = cursor.getColumnIndex("CaseB");
        indexCaseC = cursor.getColumnIndex("CaseC");
        indexCaseD = cursor.getColumnIndex("CaseD");
        indexQuestion = cursor.getColumnIndex("Question");
        indexTrueCase = cursor.getColumnIndex("TrueCase");
        cursor.moveToFirst();
        if (cursor.isAfterLast()) return null;
        int id = cursor.getInt(indexId);
        String caseA = cursor.getString(indexCaseA);
        String caseB = cursor.getString(indexCaseB);
        String caseC = cursor.getString(indexCaseC);
        String caseD = cursor.getString(indexCaseD);
        int trueCase = cursor.getInt(indexTrueCase);
        String question = cursor.getString(indexQuestion);
        closeDB();

        return new ItemQuestion(id,question, caseA, caseB, caseC, caseD, trueCase);
    }

    private ItemQuestion getQuestionTable(String table){

        Cursor cursor = sqlDB.rawQuery(SQLcont.queryQuestionTable(table), null);
        int indexId, indexQuestion, indexCaseA, indexCaseB, indexCaseC, indexCaseD, indexTrueCase;
        indexId = cursor.getColumnIndex("_id");
        indexCaseA = cursor.getColumnIndex("CaseA");
        indexCaseB = cursor.getColumnIndex("CaseB");
        indexCaseC = cursor.getColumnIndex("CaseC");
        indexCaseD = cursor.getColumnIndex("CaseD");
        indexQuestion = cursor.getColumnIndex("Question");
        indexTrueCase = cursor.getColumnIndex("TrueCase");

        cursor.moveToFirst();
        if (cursor.isAfterLast()) return null;
        int id = cursor.getInt(indexId);
        String caseA = cursor.getString(indexCaseA);
        String caseB = cursor.getString(indexCaseB);
        String caseC = cursor.getString(indexCaseC);
        String caseD = cursor.getString(indexCaseD);
        int trueCase = cursor.getInt(indexTrueCase);
        String question = cursor.getString(indexQuestion);

        return new ItemQuestion(id,question, caseA, caseB, caseC, caseD, trueCase);
    }

    public List<ItemQuestion> getArrsQuestion() {
        Log.i("bug","DBManager getArrsQuestion() line 1");
        getQuestion();
        Log.i("bug","DBManager getArrsQuestion() line 2");
        getQuestionTemp();
        Log.i("bug","DBManager getArrsQuestion() line 3");
        return arrsQuestion;
    }

    private void getQuestionTemp() {
        Log.i("bug","DBManager getQuestionTemp() line 1");
        openDB();
        Log.i("bug","DBManager getQuestionTemp() line 2");
        Cursor cursor = sqlDB.rawQuery(SQLcont.SQL_GET_QUESTION_TEMP,null);
        Log.i("bug","DBManager getQuestionTemp() line 3");
        if (cursor == null) return;
        Log.i("bug","DBManager getQuestionTemp() line 4");
        ItemQuestion itemQuestion;
        int indexID, indexQuestion, indexCaseA, indexCaseB, indexCaseC, indexCaseD, indexTrueCase;
        indexID = cursor.getColumnIndex("_id");
        indexQuestion = cursor.getColumnIndex("question");
        indexCaseA = cursor.getColumnIndex("casea");
        indexCaseB = cursor.getColumnIndex("caseb");
        indexCaseC = cursor.getColumnIndex("casec");
        indexCaseD = cursor.getColumnIndex("cased");
        indexTrueCase = cursor.getColumnIndex("truecase");

        String question, caseA, caseB, caseC, caseD;
        int id, trueCase;
        Log.i("bug","DBManager getQuestionTemp() line 5");
        cursor.moveToFirst();
        Log.i("bug","DBManager getQuestionTemp() line 6");
        id = cursor.getInt(indexID);
        Log.i("bug","DBManager getQuestionTemp() line 7");
        question = cursor.getString(indexQuestion);
        Log.i("bug","DBManager getQuestionTemp() line 8");
        caseA = cursor.getString(indexCaseA);
        caseB = cursor.getString(indexCaseB);
        caseC = cursor.getString(indexCaseC);
        caseD = cursor.getString(indexCaseD);
        Log.i("bug","DBManager getQuestionTemp() line 9");
        trueCase = cursor.getInt(indexTrueCase);
        Log.i("bug","DBManager getQuestionTemp() line 10");
        itemQuestion = new ItemQuestion(id, question, caseA, caseB, caseC, caseD, trueCase);
        Log.i("bug","DBManager getQuestionTemp() line 11");
        arrsQuestion.add(itemQuestion);
        Log.i("bug","DBManager getQuestionTemp() line 12");
        closeDB();
    }


}
