package com.example.cbr.util;

/**
 * A class to wrap all created custom exceptions.
 * */

public class CustomExceptions {

    /**
     * Thrown whenever checking for privacy permission fails.
     * For example, checking if {@code Manifest.permission.ACCESS_FINE_LOCATION} does not equal to
     * {@code PackageManager.PERMISSION_GRANTED}, then throw {@code PermissionNotGranted}.
     * */
    public static class PermissionNotGranted extends RuntimeException {
        /**
         * @param message should contain the context of what permission(s) has not been granted.
         * */
        public PermissionNotGranted(String message) {
            super(message);
        }
    }

    /**
     * Thrown when checking if the user has enabled GPS fails.
     * Case: if {@code LocationManager#isLocationEnabled(LocationManager.GPS_PROVIDER)} is false.
     * */
    public static class GPSNotEnabled extends Exception {
        public GPSNotEnabled(String message) {
            super(message);
        }
    }

    /**
     * Thrown when checking an instance of {@link android.location.Location} is null.
     * */
    public static class LocationNotFound extends RuntimeException {
        public LocationNotFound(String message) {
            super(message);
        }
    }
}
