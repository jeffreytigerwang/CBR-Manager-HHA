package com.example.cbr.models;

public class Users {
    private static Users instance;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private Users() {
    }

    // Resort to using singleton because it would be hard to
    // trace through the object getting passed around all over the place.
    public static Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
