package com.example.cbr.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cbr.models.ClientEducationAspect;
import com.example.cbr.models.ClientHealthAspect;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientSocialAspect;
import com.example.cbr.models.Users;
import com.example.cbr.models.VisitEducationQuestionSetData;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.models.VisitHealthQuestionSetData;
import com.example.cbr.models.VisitSocialQuestionSetData;
import com.example.cbr.util.Constants;

public class DBHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String CLIENT_TABLE = "CLIENT_TABLE";
    public static final String DISABILITY_TABLE = "DISABILITY_TABLE";
    public static final String VISIT_TABLE = "VISIT_TABLE";
    public static final String EDUCATION_ASPECT_TABLE = "EDUCATION_ASPECT_TABLE";
    public static final String HEALTH_ASPECT_TABLE = "HEALTH_ASPECT_TABLE";
    public static final String SOCIAL_STATUS_ASPECT_TABLE = "SOCIAL_STATUS_ASPECT_TABLE";
    public static final String EDUCATION_PROGRESS_TABLE = "EDUCATION_PROGRESS_TABLE";
    public static final String HEALTH_PROGRESS_TABLE = "HEALTH_PROGRESS_TABLE";
    public static final String SOCIAL_PROGRESS_TABLE = "SOCIAL_PROGRESS_TABLE";




    public static final String ID = "ID";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String USER_PHONE_NUMBER = "PHONE_NUMBER";
    public static final String USER_PASSWORD = "PASSWORD";
    public static final String USER_TYPE = "USER_TYPE";
    public static final String ZONE = "ZONE";


    public DBHelper (Context context) {
        super(context, Constants.LOCAL_DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUsers = "CREATE TABLE " + USER_TABLE + " (" + ID + " INT PRIMARY KEY, "
                + FIRST_NAME + " TEXT, " + LAST_NAME + " TEXT, " +  USER_PHONE_NUMBER + " INT, " + USER_PASSWORD
                + " TEXT, " + USER_TYPE + " TEXT, " + ZONE + " TEXT)";
        sqLiteDatabase.execSQL(createUsers);

        String createClients = "CREATE TABLE CLIENT_TABLE (ID INT PRIMARY KEY, " + FIRST_NAME +
                " TEXT, " + LAST_NAME + " TEXT, " + "GENDER TEXT, AGE INT, CONTACT_NUMBER TEXT, " +
                "DATE_JOINED TEXT, VILLAGE_NUMBER TEXT, " + ZONE + " TEXT, GPS_LOCATION TEXT, " +
                "CAREGIVER_PRESENT BOOL, CAREGIVER_CONTACT_NUMBER TEXT)";
        sqLiteDatabase.execSQL(createClients);

        String createDisability = "CREATE TABLE DISABILITY_TABLE (CLIENT_ID INT, AMPUTEE BOOL, " +
                "POLIO BOOL, SPINAL_CORD_INJURY BOOL, CEREBRAL_PALSY BOOL, SPINAL_BIFIDA BOOL, " +
                "HYDROCEPHALUS BOOL, VISUAL_IMPAIRMENT BOOL, DO_KNOW_KNOW BOOL, OTHER BOOL)";
        sqLiteDatabase.execSQL(createDisability);

        String createVisits = "CREATE TABLE VISITS_TABLE (CLIENT_ID INT, VISIT_ID INTEGER, " +
                "IS_HEALTH_CHECKED BOOL, IS_EDUCATION_CHECKED, BOOL, IS_SOCIAL_CHECKED BOOL, " +
                "PURPOSE_OF_VISIT TEXT, DATE_OF_VISIT DATE, WORKER_NAME TEXT, GPS_LOCATION TEXT, " +
                "ZONE_LOCATION TEXT, VILLAGE_NUMBER INT)";
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
                "RATE_HEALTH TEXT, DESCRIBE_HEALTH TEXT, SET_GOAL_FOR_HEALTH TEXT)";
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
                "IS_SOCIAL_REFERRAL_CHECKED BOOL, IS_SOCIAL_ENCOURAGEMENT_CHECKED BOOL, SOCIAL_ADVICE_DESC TEXT, " +
                "SOCIAL_ADVOCACY_DESC TEXT, SOCIAL_REF_DESC TEXT, SOCIAL_ENCOURAGEMENT_DESC TEXT, " +
                "SOCIAL_OUTCOME_DESC TEXT, SOCIAL_GOAL_STATUS_DESC TEXT)";
        sqLiteDatabase.execSQL(createSocialProgress);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addUser(Users user){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIRST_NAME, user.getFirstName());
        cv.put(LAST_NAME, user.getLastName());
        cv.put(USER_PHONE_NUMBER, user.getPhoneNumber());
        cv.put(USER_PASSWORD, user.getPassword());
        cv.put(USER_TYPE, user.getUserType());
        cv.put(ZONE, user.getZones());

        long success = db.insert(USER_TABLE, null, cv);

        return success != -1;
    }

    public boolean addClient(ClientInfo client) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIRST_NAME, client.getFirstName());
        cv.put(LAST_NAME, client.getLastName());
        cv.put(ID, client.getId());
        cv.put("GENDER", client.getGender());
        cv.put("AGE", client.getAge());
        cv.put("CONTACT_NUMBER", client.getContactNumber());
        cv.put("DATE_JOINED", client.getDateJoined());
        cv.put("VILLAGE_NUMBER", client.getVillageNumber());
        cv.put(ZONE, client.getZoneLocation());
        cv.put("GPS_LOCATION", client.getGpsLocation());
        cv.put("CAREGIVER_PRESENT", client.getCaregiverPresentForInterview());
        cv.put("CAREGIVER_CONTACT_NUMBER", client.getCaregiverContactNumber());

        long success = db.insert(CLIENT_TABLE, null, cv);
        return success != -1;

    }

    public boolean addDisability(ClientInfo client) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("CLIENT_ID", client.getId());
        cv.put("AMPUTEE", client.isAmputeeDisability());
        cv.put("POLIO", client.isPolioDisability());
        cv.put("SPINAL_CORD_INJURY", client.isSpinalCordInjuryDisability());
        cv.put("CEREBRAL_PALSY", client.isCerebralPalsyDisability());
        cv.put("SPINAL_BIFIDA", client.isSpinaBifidaDisability());
        cv.put("HYDROCEPHALUS", client.isHydrocephalusDisability());
        cv.put("VISUAL_IMPAIRMENT", client.isVisualImpairmentDisability());
        cv.put("DO_NOT_KNOW", client.isDoNotKnowDisability());
        cv.put("OTHER", client.isOtherDisability());

        long success = db.insert(DISABILITY_TABLE, null, cv);
        return success != -1;
    }

    public boolean addVisit(VisitGeneralQuestionSetData visit) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("CLIENT_ID", visit.getClientId());
        cv.put("VISIT_ID", visit.getVisitId());
        cv.put("IS_HEALTH_CHECKED", visit.isHealthChecked());
        cv.put("IS_EDUCATION_CHECKED", visit.isEducationChecked());
        cv.put("IS_SOCIAL_CHECKED", visit.isSocialChecked());
        cv.put("PURPOSE_OF_VISIT", visit.getPurposeOfVisit());
        cv.put("DATE_OF_VISIT", String.valueOf(visit.getDateOfVisit()));
        cv.put("WORKER_NAME", visit.getWorkerName());
        cv.put("GPS_LOCATION", visit.getVisitGpsLocation());
        cv.put("ZONE_LOCATION", visit.getVisitZoneLocation());
        cv.put("VILLAGE_NUMBER", visit.getVillageNumber());

        long success = db.insert(VISIT_TABLE, null, cv);
        return success != -1;
    }

    public boolean addEducationAspect(ClientEducationAspect aspect) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("CLIENT_ID", aspect.getClientId());
        cv.put("RATE_EDUCATION", aspect.getRateEducation());
        cv.put("DESCRIBE_EDUCATION", aspect.getDescribeEducation());
        cv.put("SET_GOAL_FOR_EDUCATION", aspect.getSetGoalForEducation());

        long success = db.insert(EDUCATION_ASPECT_TABLE, null, cv);
        return success != -1;

    }

    public boolean addEducationProgress(VisitEducationQuestionSetData progress) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("CLIENT_ID", progress.getClientId());
        cv.put("VISIT_ID", progress.getVisitId());
        cv.put("IS_EDUCATION_ADVICE_CHECKED", progress.isEducationAdviceChecked());
        cv.put("IS_EDUCATION_ADVOCACY_CHECKED", progress.isEducationAdvocacyChecked());
        cv.put("IS_EDUCATION_REFERRAL_CHECKED BOOL", progress.isEducationReferralChecked());
        cv.put("IS_EDUCATION_ENCOURAGEMENT_CHECKED BOOL", progress.isEducationEncouragementChecked());
        cv.put("EDUCATION_ADVICE_DESC", progress.getEducationAdviceDesc());
        cv.put("EDUCATION_ADVOCACY_DESC", progress.getEducationAdvocacyDesc());
        cv.put("EDUCATION_REFERRAL_DESC", progress.getEducationReferralDesc());
        cv.put("EDUCATION_ENCOURAGEMENT_DESC", progress.getEducationEncouragementDesc());
        cv.put("EDUCATION_OUTCOME_DESC", progress.getEducationOutcomeDesc());
        cv.put("EDUCATION_GOAL_STATUS", progress.getEducationGoalStatus());

        long success = db.insert(EDUCATION_PROGRESS_TABLE, null, cv);
        return success != -1;
    }

    public boolean addHealthAspect(ClientHealthAspect aspect) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("CLIENT_ID", aspect.getClientId());
        cv.put("RATE_HEALTH", aspect.getRateHealth());
        cv.put("DESCRIBE_HEALTH", aspect.getDescribeHealth());
        cv.put("SET_GOAL_FOR_HEALTH", aspect.getSetGoalForHealth());

        long success = db.insert(HEALTH_ASPECT_TABLE, null, cv);
        return success != -1;

    }

    public boolean addHealthProgress(VisitHealthQuestionSetData progress) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("CLIENT_ID", progress.getClientId());
        cv.put("VISIT_ID", progress.getVisitId());
        cv.put("IS_WHEEL_CHAIR_CHECKED", progress.isWheelChairChecked());
        cv.put("IS_PROSTHETIC_CHECKED", progress.isWheelChairChecked());
        cv.put("IS_ORTHOTIC_CHECKED", progress.isWheelChairChecked());
        cv.put("IS_WHEEL_CHAIR_REPAIR_CHECKED", progress.isWheelChairRepairChecked());
        cv.put("IS_REFERRAL_TO_HCC_CHECKED", progress.isWheelChairChecked());
        cv.put("IS_HEALTH_ADVICE_CHECKED", progress.isHealthAdviceChecked());
        cv.put("IS_HEALTH_ADVOCACY_CHECKED", progress.isHealthAdvocacyChecked());
        cv.put("IS_HEALTH_ENCOURAGEMENT_CHECKED BOOL", progress.isHealthEncouragementChecked());
        cv.put("WHEEL_CHAIR_DESC", progress.getWheelChairRepairDesc());
        cv.put("PROSTHETIC_DESC", progress.getProstheticDesc());
        cv.put("ORTHOTIC_DESC", progress.getOrthoticDesc());
        cv.put("WHEEL_CHAIR_REPAIR_DESC", progress.getWheelChairRepairDesc());
        cv.put("REFERRAL_TO_HC_DESC", progress.getReferralToHCDesc());
        cv.put("HEALTH_ADVICE_DESC", progress.getHealthAdviceDesc());
        cv.put("HEALTH_ADVOCACY_DESC", progress.getHealthAdvocacyDesc());
        cv.put("HEALTH_ENCOURAGEMENT_DESC", progress.getHealthEncouragementDesc());
        cv.put("HEALTH_OUTCOME_DESC", progress.getHealthOutcomeDesc());
        cv.put("HEALTH_GOAL_STATUS", progress.getHealthGoalStatus());

        long success = db.insert(HEALTH_PROGRESS_TABLE, null, cv);
        return success != -1;
    }

    public boolean addSocialAspect(ClientSocialAspect aspect) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("CLIENT_ID", aspect.getClientId());
        cv.put("RATE_SOCIAL_STATUS", aspect.getRateSocialStatus());
        cv.put("DESCRIBE_SOCIAL_STATUS", aspect.getDescribeSocialStatus());
        cv.put("SET_GOAL_FOR_SOCIAL_STATUS", aspect.getSetGoalForSocialStatus());

        long success = db.insert(SOCIAL_STATUS_ASPECT_TABLE, null, cv);
        return success != -1;
    }

    public boolean addSocialProgress(VisitSocialQuestionSetData progress) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("CLIENT_ID", progress.getClientId());
        cv.put("VISIT_ID", progress.getVisitId());
        cv.put("IS_SOCIAL_ADVICE_CHECKED", progress.isSocialAdviceChecked());
        cv.put("IS_SOCIAL_ADVOCACY_CHECKED", progress.isSocialAdvocacyChecked());
        cv.put("IS_SOCIAL_REFERRAL_CHECKED BOOL", progress.isSocialReferralChecked());
        cv.put("IS_SOCIAL_ENCOURAGEMENT_CHECKED BOOL", progress.isSocialEncouragementChecked());
        cv.put("SOCIAL_ADVICE_DESC", progress.getSocialAdviceDesc());
        cv.put("SOCIAL_ADVOCACY_DESC", progress.getSocialAdvocacyDesc());
        cv.put("SOCIAL_REFERRAL_DESC", progress.getSocialReferralDesc());
        cv.put("SOCIAL_ENCOURAGEMENT_DESC", progress.getSocialEncouragementDesc());
        cv.put("SOCIAL_OUTCOME_DESC", progress.getSocialOutcomeDesc());
        cv.put("SOCIAL_GOAL_STATUS", progress.getSocialGoalStatus());

        long success = db.insert(SOCIAL_PROGRESS_TABLE, null, cv);
        return success != -1;
    }


}
