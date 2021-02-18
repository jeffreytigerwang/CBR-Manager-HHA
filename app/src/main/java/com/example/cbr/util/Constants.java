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
    public static final ArrayList<String> CLIENT_RATINGS = new ArrayList<>(Arrays.asList(
            "Critical risk",
            "High risk",
            "Medium risk",
            "Low risk"));
}