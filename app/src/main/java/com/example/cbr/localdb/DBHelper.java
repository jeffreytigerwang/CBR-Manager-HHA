package com.example.cbr.localdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cbr.util.Constants;

public class DBHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String ID = "ID";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String USER_PHONE_NUMBER = "PHONE_NUMBER";
    public static final String USER_PASSWORD = "PASSWORD";
    public static final String USER_PRIORITY_LEVEL = "PRIORITY_LEVEL";
    public static final String ZONE = "ZONE";


    public DBHelper (Context context) {
        super(context, Constants.LOCAL_DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUsers = "CREATE TABLE " + USER_TABLE + " (" + ID + " INT PRIMARY KEY, "
                + FIRST_NAME + " TEXT, " + LAST_NAME + " TEXT, " +  USER_PHONE_NUMBER + " INT, " + USER_PASSWORD
                + " TEXT, " + USER_PRIORITY_LEVEL + " TEXT, " + ZONE + " TEXT)";
        sqLiteDatabase.execSQL(createUsers);

        String createClients = "CREATE TABLE CLIENT_TABLE (ID INT PRIMARY KEY, " + FIRST_NAME +
                " TEXT, " + LAST_NAME + " TEXT, " + "GENDER TEXT, AGE INT, CONTACT_NUMBER TEXT, " +
                "DATE_JOINED TEXT, VILLAGE_NUMBER TEXT, " + ZONE + " TEXT, GPS_LOCATION TEXT, " +
                "CAREGIVER_PRESENT BOOL, CAREGIVER_CONTACT_NUMBER TEXT, PHOTO BLOB)";
        sqLiteDatabase.execSQL(createClients);

        String createDisability = "CREATE TABLE DISABILITY_TABLE (CLIENT_ID INT, AMPUTEE BOOL, " +
                "POLIO BOOL, SPINAL_CORD_INJURY BOOL, CEREBRAL_PALSY BOOL, SPINAL_BIFIDA BOOL, " +
                "HYDROCEPHALUS BOOL, VISUAL_IMPAIRMENT BOOL, DO_KNOW_KNOW BOOL, OTHER BOOL, SPECIFY" +
                "TEXT)";
        sqLiteDatabase.execSQL(createDisability);

        String createVisits = "CREATE TABLE VISITS_TABLE (CLIENT_ID INT, VISIT_ID INTEGER, " +
                "IS_HEALTH_CHECKED BOOL, IS_EDUCATION_CHECKED, BOOL, IS_SOCIAL_CHECKED BOOL, " +
                "PURPOSE_OF_VISIT STRING, DATE_OF_VISIT DATE, WORKER_NAME TEXT, GPS_LOCATION TEXT, " +
                "ZONE_LOCATION STRING, VILLAGE_NUMBER INT)";
        sqLiteDatabase.execSQL(createVisits);

        String createEducationAspect = "CREATE TABLE EDUCATION_ASPECT_TABLE (CLIENT_ID INT, " +
                "RATE_EDUCATION TEXT, DESCRIBE_EDUCATION TEXT, SET_GOAL_FOR_EDUCATION TEXT)";
        sqLiteDatabase.execSQL(createEducationAspect);

        String createEducationProgress = "CREATE TABLE EDUCATION_PROGRESS_TABLE (CLIENT_ID INT, " +
                "VISIT_ID INT, IS_EDUCATION_ADVICE_CHECKED BOOL, IS_EDUCATION_ADVOCACY_CHECKED BOOL, " +
                "IS_EDUCATION_REFERRAL_CHECKED BOOL, IS_EDUCATION_ENCOURAGEMENT_CHECKED BOOL, " +
                "EDUCATION_ADVICE_DESC TEXT, EDUCATION_ADVOCACY_DESC TEXT, EDUCATION_REFERRAL_DESC TEXT, " +
                "EDUCATION_ENCOURAGEMENT_DESC TEXT, EDUCATION_OUTCOME_DESC TEXT, EDUCATION_GOAL_STATUS TEXT)";
        sqLiteDatabase.execSQL(createEducationProgress);

        String createHealthAspect = "CREATE TABLE HEALTH_ASPECT_TABLE (CLIENT_ID INT, " +
                "RATE_EDUCATION TEXT, DESCRIBE_EDUCATION TEXT, SET_GOAL_FOR_EDUCATION TEXT)";
        sqLiteDatabase.execSQL(createHealthAspect);

        String createHealthProgress = "CREATE TABLE HEALTH_PROGRESS_TABLE (CLIENT_ID INT, " +
                "VISIT_ID INT, IS_WHEEL_CHAIR_CHECKED BOOL, IS_PROSTHETIC_CHECKED BOOL, " +
                "IS_ORTHOTIC_CHECKED BOOL, IS_WHEEL_CHAIR_REPAIR_CHECKED BOOL, IS_REFERRAL_TO_HCC_CHECKED BOOL, " +
                "IS_HEALTH_ADVICE_CHECKED BOOL, IS_HEALTH_ADVOCACY_CHECKED BOOL, IS_HEALTH_ENCOURAGEMENT_CHECKED BOOL," +
                "WHEEL_CHAIR_DESC TEXT, PROSTHETIC_DESC TEXT, ORTHOTIC_DESC TEXT, WHEEL_CHAIR_REPAIR_DESC TEXT, " +
                "REFERRAL_TO_HC_DESC TEXT, HEALTH_ADVICE_DESC TEXT, HEALTH_ADVOCACY_DESC TEXT, " +
                "HEALTH_ENCOURAGEMENT_DESC TEXT, HEALTH_OUTCOME_DESC TEXT)";
        sqLiteDatabase.execSQL(createHealthProgress);

        String createSocialAspect = "CREATE TABLE SOCIAL_ASPECT_TABLE (CLIENT_ID INT, " +
                "RATE_SOCIAL_STATUS TEXT, DESCRIBE_SOCIAL_STATUS TEXT, SET_GOAL_FOR_SOCIAL_STATUS TEXT)";
        sqLiteDatabase.execSQL(createSocialAspect);

        String createSocialProgress = "CREATE TABLE SOCIAL_PROGRESS_TABLE (CLIENT_ID INT, " +
                "VISIT_ID INT, IS_SOCIAL_ADVICE_CHECKED BOOL, IS_SOCIAL_ADVOCACY_CHECKED BOOL, " +
                "IS_SOCIAL_REF_CHECKED BOOL, IS_SOCIAL_ENCOURAGEMENT_CHECKED BOOL, SOCIAL_ADVICE_DESC TEXT, " +
                "SOCIAL_ADVOCACY_DESC TEXT, SOCIAL_REF_DESC TEXT, SOCIAL_ENCOURAGEMENT_DESC TEXT, " +
                "SOCIAL_OUTCOME_DESC TEXT, SOCIAL_GOAL_STATUS_DESC TEXT)";
        sqLiteDatabase.execSQL(createSocialProgress);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
