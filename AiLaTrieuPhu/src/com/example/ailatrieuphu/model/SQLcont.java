package com.example.ailatrieuphu.model;

public class SQLcont {
//    public static final String SQL_GET_QUESTION = "select * from (select * from Question1 order by random() limit 1) " +
//            "union " +
//            "select * from (select * from Question2 order by random() limit 1) " +
//            "union " +
//            "select * from (select * from Question3 order by random() limit 1) " +
//            "union " +
//            "select * from (select * from Question4 order by random() limit 1) " +
//            "union " +
//            "select * from (select * from Question5 order by random() limit 1) " +
//            "union " +
//            "select * from (select * from Question6 order by random() limit 1) " +
//            "union " +
//            "select * from (select * from Question7 order by random() limit 1) " +
//            "union " +
//            "select * from (select * from Question8 order by random() limit 1) " +
//            "union " +
//            "select * from (select * from Question9 order by random() limit 1) " +
//            "union " +
//            "select * from (select * from Question10 order by random() limit 1) " +
//            "union " +
//            "select * from (select * from Question11 order by random() limit 1) " +
//            "union " +
//            "select * from (select * from Question12 order by random() limit 1) " +
//            "union " +
//            "select * from (select * from Question13 order by random() limit 1) " +
//            "union " +
//            "select * from (select * from Question14 order by random() limit 1) " +
//            "union " +
//            "select * from (select * from Question15 order by random() limit 1);";

    public static final String SQL_GET_QUESTION_TEMP = "select * from Question order by random() limit 1;";

    public final static String TABLE_QUESTION = "Question";
    public final static String TABLE_QUESTION1 = "Question1";
    public final static String TABLE_QUESTION2 = "Question2";
    public final static String TABLE_QUESTION3 = "Question3";
    public final static String TABLE_QUESTION4 = "Question4";
    public final static String TABLE_QUESTION5 = "Question5";
    public final static String TABLE_QUESTION6 = "Question6";
    public final static String TABLE_QUESTION7 = "Question7";
    public final static String TABLE_QUESTION8 = "Question8";
    public final static String TABLE_QUESTION9 = "Question9";
    public final static String TABLE_QUESTION10 = "Question10";
    public final static String TABLE_QUESTION11 = "Question11";
    public final static String TABLE_QUESTION12 = "Question12";
    public final static String TABLE_QUESTION13 = "Question13";
    public final static String TABLE_QUESTION14 = "Question14";
    public final static String TABLE_QUESTION15 = "Question15";

//    public final static String queryQuestion1 = "select * from Question1 order by random() limit 1;";

    public static String queryQuestionTable(String table) {
        return "select * from " + table + " order by random() limit 1;";
    }
}