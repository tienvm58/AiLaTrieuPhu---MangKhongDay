package com.example.ailatrieuphu.model;

import android.util.Log;


public class ItemQuestion {

    public static final int CASE_A = 1;
    public static final int CASE_B = 2;
    public static final int CASE_C = 3;
    public static final int CASE_D = 4;
    private int id;
    private String question, caseA, caseB, caseC, caseD;
    private int trueCase;

    public ItemQuestion(int id, String question, String caseA, String caseB, String caseC, String caseD, int trueCase) {
        this.question = question;
        this.caseA = caseA;
        this. caseB = caseB;
        this. caseC = caseC;
        this. caseD = caseD;
        this.trueCase = trueCase;
        Log.i("dap an", toString());
    }

    public int getId() {
        return id;
    }

    public int getTrueCase() {
        return trueCase;
    }

    public String getCaseA() {
        return caseA;
    }

    public String getCaseB() {
        return caseB;
    }

    public String getCaseC() {
        return caseC;
    }

    public String getCaseD() {
        return caseD;
    }

    public String getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("ID: " + id)
                .append("\nQuestion: " + question)
                .append("\nCaseA: " + caseA)
                .append("\nCaseB: " + caseB)
                .append("\nCaseC: " + caseC)
                .append("\nCaseD: " + caseD)
                .append("\nTrueCase: " + trueCase);

        return str.toString();
    }
}
