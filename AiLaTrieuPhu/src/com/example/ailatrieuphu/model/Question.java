package com.example.ailatrieuphu.model;


public class Question {

    public static final int DAP_AN_A = 1;
    public static final int DAP_AN_B = 2;
    public static final int DAP_AN_C = 3;
    public static final int DAP_AN_D = 4;

    private String cauhoi;
    private String dapAnA, dapAnB, dapAnC, dapAnD;
    private int dapAnDung;

    public Question(String cauhoi, String dapAnA, String dapAnB, String dapAnC, String dapAnD, int dapAnDung) {
        this.cauhoi = cauhoi;
        this.dapAnA = dapAnA;
        this.dapAnB = dapAnB;
        this.dapAnC = dapAnC;
        this.dapAnD = dapAnD;
        this.dapAnDung = dapAnDung;
    }

    public int getDapAnDung() {
        return dapAnDung;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public String getDapAnA() {
        return dapAnA;
    }

    public String getDapAnB() {
        return dapAnB;
    }

    public String getDapAnC() {
        return dapAnC;
    }

    public String getDapAnD() {
        return dapAnD;
    }
}
