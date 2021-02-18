package com.example.cbr.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String CBR = "CBR";
    public static final String DCR = "Disability Centre referral";
    public static final String DCRFU = "Disability Centre referral follow up";

    public static final String CONCLUDED = "concluded";
    public static final String ONGOING = "ongoing";
    public static final String CANCELLED = "cancelled";

    public static final ArrayList<String> ZONES = new ArrayList<>(Arrays.asList(
            "BidiBidi Zone 1",
            "BidiBidi Zone 2",
            "BidiBidi Zone 3",
            "BidiBidi Zone 4",
            "BidiBidi Zone 5",
            "Palorinya Basecamp",
            "Palorinya Zone 1",
            "Palorinya Zone 2",
            "Palorinya Zone 3"));
    public static final ArrayList<String> CLIENTRATINGS = new ArrayList<>(Arrays.asList(
            "Critical risk",
            "High risk",
            "Medium risk",
            "Low risk"));

    // Disabilities
    public static String AMPUTEE_DISABILITY = "Amputee";
    public static String POLIO_DISABILITY = "Polio";
    public static String SPINAL_CORD_INJURY_DISABILITY = "Spinal cord injury";
    public static String CEREBRAL_PALSY_DISABILITY = "Cerebral palsy";
    public static String SPINA_BIFIDA_DISABILITY = "Spina bifida";
    public static String HYDROCEPHALUS_DISABILITY = "Hydrocephalus";
    public static String VISUAL_IMPAIRMENT_DISABILITY = "Visual impairment";
    public static String HEARING_IMPAIRMENT_DISABILITY = "Hearing impairment";
    public static String UNKNOWN_DISABILITY = "Unknown";
    public static String OTHER_DISABILITY = "Other";
}
