package com.example.anitharaj.volunteer_ph3;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * Created by natar on 4/16/2017.
 */

@IgnoreExtraProperties
public class User {

    public String FirstName, LastName, Email, DOB;
    public List<String> preferences;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String Firstname, String Lastname, String email, String Dob, List preference) {
        this.FirstName = Firstname;
        this.LastName = Lastname;
        this.Email = email;
        this.DOB = Dob;
        preferences   = new ArrayList<String>(preference);
        //Collections.copy(preferences, preference);
    }
}
