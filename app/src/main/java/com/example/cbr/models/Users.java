package com.example.cbr.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Users implements Parcelable {
    private static Users instance;

    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String zones;
    private String userType;
    private String password;

    private Users() {
    }

    protected Users(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        phoneNumber = in.readString();
        zones = in.readString();
        userType = in.readString();
        password = in.readString();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    // Resort to using singleton because it would be hard to
    // trace through the object getting passed around all over the place.
    public static Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZones() {
        return zones;
    }

    public void setZones(String zones) {
        this.zones = zones;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(phoneNumber);
        dest.writeString(zones);
        dest.writeString(userType);
        dest.writeString(password);
    }
}
