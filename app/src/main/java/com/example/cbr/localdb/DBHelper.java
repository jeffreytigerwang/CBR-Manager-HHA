package com.example.cbr.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cbr.R;
import com.example.cbr.models.ClientDisability;
import com.example.cbr.models.ClientEducationAspect;
import com.example.cbr.models.ClientHealthAspect;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientSocialAspect;
import com.example.cbr.models.ReferralInfo;
import com.example.cbr.models.VisitEducationQuestionSetData;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.models.VisitHealthQuestionSetData;
import com.example.cbr.models.VisitSocialQuestionSetData;
import com.example.cbr.util.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String CLIENT_TABLE = "CLIENT_TABLE";
    public static final String DISABILITY_TABLE = "DISABILITY_TABLE";
    public static final String VISIT_TABLE = "VISIT_TABLE";
    public static final String EDUCATION_ASPECT_TABLE = "EDUCATION_ASPECT_TABLE";
    public static final String HEALTH_ASPECT_TABLE = "HEALTH_ASPECT_TABLE";
    public static final String SOCIAL_STATUS_ASPECT_TABLE = "SOCIAL_STATUS_ASPECT_TABLE";
    public static final String EDUCATION_PROGRESS_TABLE = "EDUCATION_PROGRESS_TABLE";
    public static final String HEALTH_PROGRESS_TABLE = "HEALTH_PROGRESS_TABLE";
    public static final String SOCIAL_PROGRESS_TABLE = "SOCIAL_PROGRESS_TABLE";
    public static final String REFERRAL_TABLE = "REFERRAL_TABLE";
    public static final String SELECT_ALL_FROM = "SELECT * FROM ";




    public static final String ID = "ID";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String ZONE = "ZONE";
    private Context context;


    public DBHelper (Context context) {
        super(context, Constants.LOCAL_DATABASE, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String createClients = "CREATE TABLE CLIENT_TABLE (ID INT PRIMARY KEY, " + FIRST_NAME +
                " TEXT, " + LAST_NAME + " TEXT, " + "GENDER TEXT, AGE INTEGER, CONTACT_NUMBER TEXT, " +
                "DATE_JOINED TEXT, VILLAGE_NUMBER TEXT, " + ZONE + " TEXT, GPS_LOCATION TEXT, " +
                "CAREGIVER_PRESENT BOOL, CAREGIVER_FIRST_NAME TEXT, CAREGIVER_LAST_NAME TEXT, " +
                "CAREGIVER_CONTACT_NUMBER TEXT, PHOTO BLOB)";
        sqLiteDatabase.execSQL(createClients);

        String createDisability = "CREATE TABLE DISABILITY_TABLE (CLIENT_ID INT, AMPUTEE BOOL, " +
                "POLIO BOOL, SPINAL_CORD_INJURY BOOL, CEREBRAL_PALSY BOOL, SPINAL_BIFIDA BOOL, " +
                "HYDROCEPHALUS BOOL, VISUAL_IMPAIRMENT BOOL, HEARING_IMPAIRMENT BOOL, " +
                "DO_KNOW_KNOW BOOL, OTHER BOOL, OTHER_DESC TEXT)";
        sqLiteDatabase.execSQL(createDisability);

        String createVisits = "CREATE TABLE VISITS_TABLE (CLIENT_ID INT, VISIT_ID INTEGER, " +
                "IS_HEALTH_CHECKED BOOL, IS_EDUCATION_CHECKED BOOL, IS_SOCIAL_CHECKED BOOL, " +
                "PURPOSE_OF_VISIT TEXT, DATE_OF_VISIT TEXT, WORKER_NAME TEXT, GPS_LOCATION TEXT, " +
                "ZONE_LOCATION TEXT, VILLAGE_NUMBER TEXT)";
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
                "HEALTH_ENCOURAGEMENT_DESC TEXT, HEALTH_OUTCOME_DESC TEXT, HEALTH_GOAL_DESC TEXT)";
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

        String createReferral = "CREATE TABLE REFERRAL_TABLE (CLIENT_ID INT, REFERRAL_ID INT, " +
                "REQUIRE_PHYSIOTHERAPY BOOL, REQUIRE_PROSTHETIC BOOL, REQUIRE_ORTHOTIC BOOL, " +
                "REQUIRE_WHEEL_CHAIR BOOL, REQUIRE_OTHER BOOL, OTHER_DESC TEXT, " +
                "AMPUTEE BOOL, POLIO BOOL, SPINAL_CORD_INJURY BOOL, CEREBRAL_PALSY BOOL, " +
                "SPINA_BIFIDA BOOL, HYDROCEPHALUS BOOL, VISUAL_IMPAIRMENT BOOL, " +
                "HEARING_IMPAIRMENT BOOL, OTHER BOOL, INJURY_ABOVE_KNEE BOOL, INJURY_BELOW_KNEE BOOL, " +
                "INJURY_ABOVE_ELBOW BOOL, INJURY_BELOW_ELBOW BOOL, INTERMEDIATE_WHEEL_CHAIR_USER BOOL, " +
                "HIP_WIDTH INTEGER, HAS_EXISTING_WHEEL_CHAIR BOOL, " +
                "CAN_REPAIR_WHEEL_CHAIR BOOL, OUTCOME TEXT, PHYSIOTHERAPY_PHOTO BLOB)";
        sqLiteDatabase.execSQL(createReferral);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addClient(ClientInfo client) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIRST_NAME, client.getFirstName());
        cv.put(LAST_NAME, client.getLastName());
        cv.put(ID, client.getClientId());
        cv.put("GENDER", client.getGender());
        cv.put("AGE", client.getAge());
        cv.put("CONTACT_NUMBER", client.getContactNumber());
        cv.put("DATE_JOINED", client.getDateJoined());
        cv.put("VILLAGE_NUMBER", client.getVillageNumber());
        cv.put(ZONE, client.getZoneLocation());
        cv.put("GPS_LOCATION", client.getGpsLocation());
        cv.put("CAREGIVER_PRESENT", client.getCaregiverPresentForInterview());
        cv.put("CAREGIVER_CONTACT_NUMBER", client.getCaregiverContactNumber());
        cv.put("CAREGIVER_FIRST_NAME", client.getCaregiverFirstName());
        cv.put("CAREGIVER_LAST_NAME", client.getCaregiverLastName());
        cv.put("PHOTO", client.getPhoto());

        long success = db.insert(CLIENT_TABLE, null, cv);
        return success != -1;

    }

    public boolean addDisability(ClientDisability clientDisability) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("CLIENT_ID", clientDisability.getId());
        cv.put("AMPUTEE", clientDisability.isAmputeeDisability());
        cv.put("POLIO", clientDisability.isPolioDisability());
        cv.put("SPINAL_CORD_INJURY", clientDisability.isSpinalCordInjuryDisability());
        cv.put("CEREBRAL_PALSY", clientDisability.isCerebralPalsyDisability());
        cv.put("SPINAL_BIFIDA", clientDisability.isSpinaBifidaDisability());
        cv.put("HYDROCEPHALUS", clientDisability.isHydrocephalusDisability());
        cv.put("VISUAL_IMPAIRMENT", clientDisability.isVisualImpairmentDisability());
        cv.put("HEARING_IMPAIRMENT", clientDisability.isHearingImpairmentDisability());
        cv.put("DO_NOT_KNOW", clientDisability.isDoNotKnowDisability());
        cv.put("OTHER", clientDisability.isOtherDisability());
        cv.put("OTHER_DESC", clientDisability.getDescribeOtherDisability());


        long success = db.insert(DISABILITY_TABLE, null, cv);
        return success != -1;
    }

    public boolean addVisit(VisitGeneralQuestionSetData visit) {
        SQLiteDatabase db = this.getWritableDatabase();
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
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("CLIENT_ID", aspect.getClientId());
        cv.put("RATE_EDUCATION", aspect.getRateEducation());
        cv.put("DESCRIBE_EDUCATION", aspect.getDescribeEducation());
        cv.put("SET_GOAL_FOR_EDUCATION", aspect.getSetGoalForEducation());

        long success = db.insert(EDUCATION_ASPECT_TABLE, null, cv);
        return success != -1;

    }

    public boolean addEducationProgress(VisitEducationQuestionSetData progress) {
        SQLiteDatabase db = this.getWritableDatabase();
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
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("CLIENT_ID", aspect.getClientId());
        cv.put("RATE_HEALTH", aspect.getRateHealth());
        cv.put("DESCRIBE_HEALTH", aspect.getDescribeHealth());
        cv.put("SET_GOAL_FOR_HEALTH", aspect.getSetGoalForHealth());

        long success = db.insert(HEALTH_ASPECT_TABLE, null, cv);
        return success != -1;

    }

    public boolean addHealthProgress(VisitHealthQuestionSetData progress) {
        SQLiteDatabase db = this.getWritableDatabase();
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
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("CLIENT_ID", aspect.getClientId());
        cv.put("RATE_SOCIAL_STATUS", aspect.getRateSocialStatus());
        cv.put("DESCRIBE_SOCIAL_STATUS", aspect.getDescribeSocialStatus());
        cv.put("SET_GOAL_FOR_SOCIAL_STATUS", aspect.getSetGoalForSocialStatus());

        long success = db.insert(SOCIAL_STATUS_ASPECT_TABLE, null, cv);
        return success != -1;
    }

    public boolean addSocialProgress(VisitSocialQuestionSetData progress) {
        SQLiteDatabase db = this.getWritableDatabase();
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

    public boolean addReferral(ReferralInfo referral) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("CLIENT_ID", referral.getClientId());
        cv.put("REFERRAL_ID", referral.getReferralId());
        cv.put("REQUIRE_PHYSIOTHERAPY", referral.isRequirePhysiotherapy());
        cv.put("REQUIRE_PROSTHETIC", referral.isRequireProsthetic());
        cv.put("REQUIRE_ORTHOTIC", referral.isRequireOrthotic());
        cv.put("REQUIRE_WHEEL_CHAIR", referral.isRequireWheelchair());
        cv.put("REQUIRE_OTHER", referral.isRequireOther());
        cv.put("OTHER_DESC", referral.getOtherDescription());
        cv.put("AMPUTEE", referral.isAmputeeDisability());
        cv.put("POLIO", referral.isPolioDisability());
        cv.put("SPINAL_CORD_INJURY", referral.isSpinalCordInjuryDisability());
        cv.put("CEREBRAL_PALSY", referral.isCerebralPalsyDisability());
        cv.put("SPINA_BIFIDA", referral.isSpinaBifidaDisability());
        cv.put("HYDROCEPHALUS", referral.isHydrocephalusDisability());
        cv.put("VISUAL_IMPAIRMENT", referral.isVisualImpairmentDisability());
        cv.put("HEARING_IMPAIRMENT", referral.isHearingImpairmentDisability());
        cv.put("OTHER", referral.isOtherDisability());
        cv.put("INJURY_ABOVE_KNEE", referral.isInjuryAboveKnee());
        cv.put("INJURY_BELOW_KNEE", referral.isInjuryBelowKnee());
        cv.put("INJURY_ABOVE_ELBOW", referral.isInjuryAboveElbow());
        cv.put("INJURY_BELOW_ELBOW", referral.isInjuryBelowElbow());

        cv.put("INTERMEDIATE_WHEEL_CHAIR_USER", referral.isIntermediateWheelchairUser());
        cv.put("HIP_WIDTH", referral.getHipWidth());
        cv.put("HAS_EXISTING_WHEEL_CHAIR", referral.isHasExistingWheelchair());
        cv.put("CAN_REPAIR_WHEEL_CHAIR", referral.isCanRepairWheelchair());
        cv.put("OUTCOME", referral.getOutcome());

        Byte[] wBytes = referral.getPhysiotherapyPhoto();
        byte[] bytes = new byte[wBytes.length];
        for(int i = 0; i < wBytes.length; i++) {
            bytes[i] = wBytes[i];
        }
        cv.put("PHYSIOTHERAPY_PHOTO", bytes);

        long success = db.insert(REFERRAL_TABLE, null, cv);
        return success != -1;
    }

    public List<ClientInfo> getAllClients() {
        List<ClientInfo> clientInfoList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = SELECT_ALL_FROM + CLIENT_TABLE;
        Cursor clientCursor = db.rawQuery(query, null);

        if(clientCursor.moveToFirst()) {
            do {

                int id = clientCursor.getInt(0);
                String firstName = clientCursor.getString(1);
                String lastName = clientCursor.getString(2);
                String gender = clientCursor.getString(3);
                int age = clientCursor.getInt(4);
                String contactNumber = clientCursor.getString(5);
                String dateJoined = clientCursor.getString(6);
                int villageNumber = clientCursor.getInt(7);
                String zone = clientCursor.getString(8);
                String gpsLocation = clientCursor.getString(9);
                boolean caregiverPresent = clientCursor.getInt(10) == 1;

                String caregiverFirstName = clientCursor.getString(11);
                String caregiverLastName = clientCursor.getString(12);
                String caregiverContact = clientCursor.getString(13);
                byte[] photo = clientCursor.getBlob(14);

                ClientInfo client = new ClientInfo();
                client.setClientId(id);
                client.setFirstName(firstName);
                client.setLastName(lastName);
                client.setGender(gender);
                client.setAge(age);
                client.setContactNumber(contactNumber);
                client.setDateJoined(dateJoined);
                client.setVillageNumber(villageNumber);
                client.setZoneLocation(zone);
                client.setGpsLocation(gpsLocation);
                client.setCaregiverPresentForInterview(caregiverPresent);
                client.setCaregiverFirstName(caregiverFirstName);
                client.setCaregiverLastName(caregiverLastName);
                client.setCaregiverContactNumber(caregiverContact);
                client.setPhoto(photo);

                clientInfoList.add(client);

            } while (clientCursor.moveToNext());
        }
        return clientInfoList;
    }

    public List<VisitGeneralQuestionSetData> getAllVisitGeneralQuestions() {
        List<VisitGeneralQuestionSetData> visitsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = SELECT_ALL_FROM + VISIT_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                int clientId = cursor.getInt(0);
                int visitId = cursor.getInt(1);
                boolean isHealthChecked = cursor.getInt(2) == 1;
                boolean isEducationChecked = cursor.getInt(3) == 1;
                boolean isSocialChecked = cursor.getInt(4) == 1;
                String visitPurpose = cursor.getString(5);

                Date dateOfVisit = null;

                try {
                    dateOfVisit = new SimpleDateFormat(context.getString(R.string.date_pattern_database)).parse(cursor.getString(6));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String workerName = cursor.getString(7);
                String gpsLocation = cursor.getString(8);
                String zoneLocation = cursor.getString(9);
                String villageNumber = cursor.getString(10);

                VisitGeneralQuestionSetData visit = new VisitGeneralQuestionSetData();
                visit.setClientId(clientId);
                visit.setVisitId(visitId);
                visit.setHealthChecked(isHealthChecked);
                visit.setEducationChecked(isEducationChecked);
                visit.setSocialChecked(isSocialChecked);
                visit.setPurposeOfVisit(visitPurpose);
                visit.setDateOfVisit(dateOfVisit);
                visit.setWorkerName(workerName);
                visit.setVisitGpsLocation(gpsLocation);
                visit.setVisitZoneLocation(zoneLocation);
                visit.setVillageNumber(villageNumber);

                visitsList.add(visit);

            } while (cursor.moveToNext());
        }
        return visitsList;
    }

    public List<VisitHealthQuestionSetData> getAllVisitHealthQuestions(){
        List<VisitHealthQuestionSetData> visitsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = SELECT_ALL_FROM + HEALTH_PROGRESS_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                int clientId = cursor.getInt(0);
                int visitId = cursor.getInt(1);
                boolean isWheelChairChecked = cursor.getInt(2) == 1;
                boolean isProstheticChecked = cursor.getInt(3) == 1;
                boolean isOrthoticChecked = cursor.getInt(4) == 1;
                boolean isWheelChairRepairChecked = cursor.getInt(5) == 1;
                boolean isReferralToHccChecked = cursor.getInt(6) == 1;
                boolean isHealthAdviceChecked = cursor.getInt(7) == 1;
                boolean isHealthAdvocacyChecked = cursor.getInt(8) == 1;
                boolean isHealthEncouragementChecked = cursor.getInt(9) == 1;
                String wheelChairDesc = cursor.getString(10);
                String prostheticDesc = cursor.getString(11);
                String orthoticDesc = cursor.getString(12);
                String wheelChairRepairDesc = cursor.getString(13);
                String referralToHcc = cursor.getString(14);
                String healthAdvice = cursor.getString(15);
                String healthAdvocacy = cursor.getString(16);
                String healthEncouragement = cursor.getString(17);
                String healthOutcome = cursor.getString(18);
                String healthGoal = cursor.getString(19);

                VisitHealthQuestionSetData visit = new VisitHealthQuestionSetData();
                visit.setClientId(clientId);
                visit.setVisitId(visitId);
                visit.setWheelChairChecked(isWheelChairChecked);
                visit.setProstheticChecked(isProstheticChecked);
                visit.setOrthoticChecked(isOrthoticChecked);
                visit.setWheelChairRepairChecked(isWheelChairRepairChecked);
                visit.setReferralToHCChecked(isReferralToHccChecked);
                visit.setHealthAdviceChecked(isHealthAdviceChecked);
                visit.setHealthAdvocacyChecked(isHealthAdvocacyChecked);
                visit.setHealthEncouragementChecked(isHealthEncouragementChecked);
                visit.setWheelChairDesc(wheelChairDesc);
                visit.setProstheticDesc(prostheticDesc);
                visit.setOrthoticDesc(orthoticDesc);
                visit.setWheelChairRepairDesc(wheelChairRepairDesc);
                visit.setReferralToHCDesc(referralToHcc);
                visit.setHealthAdviceDesc(healthAdvice);
                visit.setHealthAdvocacyDesc(healthAdvocacy);
                visit.setHealthEncouragementDesc(healthEncouragement);
                visit.setHealthOutcomeDesc(healthOutcome);
                visit.setHealthGoalStatus(healthGoal);

                visitsList.add(visit);

            } while(cursor.moveToNext());
        }

        return visitsList;
    }

    public List<VisitEducationQuestionSetData> getAllVisitEducationQuestions() {
        List<VisitEducationQuestionSetData> visitsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = SELECT_ALL_FROM + EDUCATION_PROGRESS_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                int clientId = cursor.getInt(0);
                int visitId = cursor.getInt(1);

                boolean isEducationAdviceChecked = cursor.getInt(2) == 1;
                boolean isEducationAdvocacyChecked = cursor.getInt(3) == 1;
                boolean isEducationReferralChecked = cursor.getInt(4) == 1;
                boolean isEducationEncouragementChecked = cursor.getInt(5) == 1;
                String educationAdvice = cursor.getString(6);
                String educationAdvocacy = cursor.getString(7);
                String educationReferral = cursor.getString(8);
                String educationEncouragement = cursor.getString(9);
                String educationOutcome = cursor.getString(10);
                String educationGoal = cursor.getString(11);

                VisitEducationQuestionSetData visit = new VisitEducationQuestionSetData();
                visit.setClientId(clientId);
                visit.setVisitId(visitId);
                visit.setEducationAdviceChecked(isEducationAdviceChecked);
                visit.setEducationAdvocacyChecked(isEducationAdvocacyChecked);
                visit.setEducationEncouragementChecked(isEducationEncouragementChecked);
                visit.setEducationReferralChecked(isEducationReferralChecked);
                visit.setEducationAdviceDesc(educationAdvice);
                visit.setEducationAdvocacyDesc(educationAdvocacy);
                visit.setEducationEncouragementDesc(educationEncouragement);
                visit.setEducationReferralDesc(educationReferral);
                visit.setEducationOutcomeDesc(educationOutcome);
                visit.setEducationGoalStatus(educationGoal);

                visitsList.add(visit);

            } while(cursor.moveToNext());
        }

        return visitsList;
    }

    public List<VisitSocialQuestionSetData> getAllVisitSocialQuestions() {
        List<VisitSocialQuestionSetData> visitsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = SELECT_ALL_FROM + SOCIAL_PROGRESS_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                int clientId = cursor.getInt(0);
                int visitId = cursor.getInt(1);

                boolean isSocialAdviceChecked = cursor.getInt(2) == 1;
                boolean isSocialAdvocacyChecked = cursor.getInt(3) == 1;
                boolean isSocialReferralChecked = cursor.getInt(4) == 1;
                boolean isSocialEncouragementChecked = cursor.getInt(5) == 1;
                String socialAdvice = cursor.getString(6);
                String socialAdvocacy = cursor.getString(7);
                String socialReferral = cursor.getString(8);
                String socialEncouragement = cursor.getString(9);
                String socialOutcome = cursor.getString(10);
                String socialGoal = cursor.getString(11);

                VisitSocialQuestionSetData visit = new VisitSocialQuestionSetData();
                visit.setClientId(clientId);
                visit.setVisitId(visitId);
                visit.setSocialAdviceChecked(isSocialAdviceChecked);
                visit.setSocialAdvocacyChecked(isSocialAdvocacyChecked);
                visit.setSocialEncouragementChecked(isSocialEncouragementChecked);
                visit.setSocialReferralChecked(isSocialReferralChecked);
                visit.setSocialAdviceDesc(socialAdvice);
                visit.setSocialAdvocacyDesc(socialAdvocacy);
                visit.setSocialEncouragementDesc(socialEncouragement);
                visit.setSocialReferralDesc(socialReferral);
                visit.setSocialOutcomeDesc(socialOutcome);
                visit.setSocialGoalStatus(socialGoal);

                visitsList.add(visit);

            } while(cursor.moveToNext());
        }

        return visitsList;
    }

    public List<ClientHealthAspect> getAllClientHealthAspects() {

        List<ClientHealthAspect> clientList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = SELECT_ALL_FROM + HEALTH_ASPECT_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                int clientId = cursor.getInt(0);
                String rateHealth = cursor.getString(1);
                String describeHealth = cursor.getString(2);
                String setGoalForHealth = cursor.getString(3);

                ClientHealthAspect client = new ClientHealthAspect(clientId, rateHealth,
                        describeHealth, setGoalForHealth);

                clientList.add(client);

            } while(cursor.moveToNext());
        }

        return clientList;
    }

    public List<ClientEducationAspect> getAllClientEducationAspects() {
        List<ClientEducationAspect> clientList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = SELECT_ALL_FROM + EDUCATION_ASPECT_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                int clientId = cursor.getInt(0);
                String rateEducation = cursor.getString(1);
                String describeEducation = cursor.getString(2);
                String setGoalForEducation = cursor.getString(3);

                ClientEducationAspect client = new ClientEducationAspect(clientId, rateEducation,
                        describeEducation, setGoalForEducation);

                clientList.add(client);

            } while(cursor.moveToNext());
        }

        return clientList;
    }

    public List<ClientSocialAspect> getAllClientSocialAspects() {
        List<ClientSocialAspect> clientList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = SELECT_ALL_FROM + SOCIAL_STATUS_ASPECT_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                int clientId = cursor.getInt(0);
                String rateSocial = cursor.getString(1);
                String describeSocial = cursor.getString(2);
                String setGoalForSocial = cursor.getString(3);

                ClientSocialAspect client = new ClientSocialAspect(clientId, rateSocial,
                        describeSocial, setGoalForSocial);

                clientList.add(client);

            } while(cursor.moveToNext());
        }

        return clientList;
    }

    public List<ClientDisability> getAllDisability() {
        List<ClientDisability> clientDisabilities = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = SELECT_ALL_FROM + DISABILITY_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                int clientId = cursor.getInt(0);
                boolean amputeeDisability = cursor.getInt(1) == 1;
                boolean polioDisability = cursor.getInt(2) == 1;
                boolean spinalCordInjuryDisability = cursor.getInt(3) == 1;
                boolean cerebralPalsyDisability = cursor.getInt(4) == 1;
                boolean spinaBifidaDisability = cursor.getInt(5) == 1;
                boolean hydrocephalusDisability = cursor.getInt(6) == 1;
                boolean visualImpairmentDisability = cursor.getInt(7) == 1;
                boolean hearingImpairmentDisability = cursor.getInt(8) == 1;
                boolean doNotKnowDisability = cursor.getInt(9) == 1;
                boolean otherDisability = cursor.getInt(10) == 1;
                String describeOtherDisability = cursor.getString(11);

                ClientDisability client = new ClientDisability(clientId, amputeeDisability,
                        polioDisability, spinalCordInjuryDisability, cerebralPalsyDisability,
                        spinaBifidaDisability, hydrocephalusDisability, visualImpairmentDisability,
                        hearingImpairmentDisability, doNotKnowDisability, otherDisability, describeOtherDisability);

                clientDisabilities.add(client);

            } while(cursor.moveToNext());
        }

        return clientDisabilities;
    }


    public List<ReferralInfo> getAllReferrals() {
        List<ReferralInfo> referralInfos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = SELECT_ALL_FROM + REFERRAL_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                int clientId = cursor.getInt(0);
                int referralId = cursor.getInt(1);
                boolean requirePhysiotherapy = cursor.getInt(2) == 1;
                boolean requireProsthetic = cursor.getInt(3) == 1;
                boolean requireOrthotic = cursor.getInt(4) == 1;
                boolean requireWheelchair = cursor.getInt(5) == 1;
                boolean requireOther = cursor.getInt(6) == 1;
                String otherDesc = cursor.getString(7);

                boolean amputeeDisability = cursor.getInt(8) == 1;
                boolean polioDisability = cursor.getInt(9) == 1;
                boolean spinalCordInjuryDisability = cursor.getInt(10) == 1;
                boolean cerebralPalsyDisability = cursor.getInt(11) == 1;
                boolean spinaBifidaDisability = cursor.getInt(12) == 1;
                boolean hydrocephalusDisability = cursor.getInt(13) == 1;
                boolean visualImpairmentDisability = cursor.getInt(14) == 1;
                boolean hearingImpairmentDisability = cursor.getInt(15) == 1;
                boolean otherDisability = cursor.getInt(16) == 1;

                boolean injuryAboveKnee = cursor.getInt(17) == 1;
                boolean injuryBelowKnee = cursor.getInt(18) == 1;
                boolean injuryAboveElbow = cursor.getInt(19) == 1;
                boolean injuryBelowElbow = cursor.getInt(20) == 1;

                boolean intermediateWheelChair = cursor.getInt(21) == 1;
                int hipWidth = cursor.getInt(22);
                boolean hasExistingWheelchair = cursor.getInt(23) == 1;
                boolean canRepairWheelchair = cursor.getInt(24) == 1;
                String outcome = cursor.getString(25);

                byte[] bytes = cursor.getBlob(26);
                Byte[] physiotherapyPhoto = new Byte[bytes.length];
                for(int i = 0; i < bytes.length; i++) {
                    physiotherapyPhoto[i] = bytes[i];
                }

                ReferralInfo referral = new ReferralInfo(clientId, referralId, requirePhysiotherapy,
                        requireProsthetic, requireOrthotic, requireWheelchair, requireOther, otherDesc,
                        physiotherapyPhoto, amputeeDisability, polioDisability, spinalCordInjuryDisability,
                        cerebralPalsyDisability, spinaBifidaDisability, hydrocephalusDisability,
                        visualImpairmentDisability, hearingImpairmentDisability, otherDisability,
                        injuryAboveKnee, injuryBelowKnee, injuryAboveElbow, injuryBelowElbow,
                        intermediateWheelChair, hipWidth, hasExistingWheelchair, canRepairWheelchair,
                        outcome);
                referralInfos.add(referral);

            } while(cursor.moveToNext());
        }

        return referralInfos;
    }


}
