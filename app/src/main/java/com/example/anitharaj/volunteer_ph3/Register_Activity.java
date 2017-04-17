package com.example.anitharaj.volunteer_ph3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;

public class Register_Activity extends AppCompatActivity {
    private static final String TAG = Register_Activity.class.getSimpleName();
    private EditText inputEmail, inputPassword, inputFirstName, inputLastName, inputDOB;
    private Button buttonSignUp;
    private ProgressDialog progressDialog;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    private List<String> pref = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        auth = FirebaseAuth.getInstance();

        buttonSignUp = (Button) findViewById(R.id.buttonRegSignUp);
        inputEmail = (EditText) findViewById(R.id.edittextRegEmail);
        inputPassword = (EditText) findViewById(R.id.edittextRegPassword);
        inputFirstName = (EditText) findViewById(R.id.editRegFirstName);
        inputLastName = (EditText) findViewById(R.id.editRegLastName);
        inputDOB = (EditText) findViewById(R.id.editRegDate);


        progressDialog = new ProgressDialog(this);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("app_title").setValue("User Detail Database");

        // app_title change listener
        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, "App title updated");

                String appTitle = dataSnapshot.getValue(String.class);

                // update toolbar title
                getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read app title value.", error.toException());
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.setMessage("Registering..");
                progressDialog.show();

                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register_Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Register_Activity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Register_Activity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {

                                    if (TextUtils.isEmpty(userId)) {
                                        String FirstName = inputFirstName.getText().toString().trim();
                                        String LastName = inputLastName.getText().toString().trim();
                                        String email = inputEmail.getText().toString().trim();
                                        String DOB = inputDOB.getText().toString().trim();
                                        createUser(FirstName, LastName, email, DOB, pref);
                                    }

                                    startActivity(new Intent(Register_Activity.this, HomeFeed_Activity.class));
                                    finish();
                                }
                            }
                        });

            }
        });

    }

    public void onCheckClicked (View v){
        boolean checked = ((CheckBox) v).isChecked();

        switch(v.getId()) {
            case R.id.prefGames:
                if (!checked && pref.contains("Games")){
                    pref.remove("Games");
                }
                if (checked) {
                    pref.add("Games");
                }
                break;
            case R.id.prefNature:
                if (!checked && pref.contains("Nature")){
                    pref.remove("Nature");
                }
                if (checked) {
                    pref.add("Nature");
                }
                break;

            case R.id.prefScience:
                if (!checked && pref.contains("Science")){
                    pref.remove("Science");
                }
                if (checked) {
                    pref.add("Science");
                }
                break;
            case R.id.prefAnimal:
                if (!checked && pref.contains("Animals")){
                    pref.remove("Animals");
                }
                if (checked) {
                    pref.add("Animals");
                }
                break;

        }
    }

    /**
     * Creating new user node under 'users'
     */
    private void createUser(String Firstname, String Lastname, String Email, String DOB, List pref) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        User user = new User(Firstname, Lastname, Email, DOB, pref);

        mFirebaseDatabase.child(userId).setValue(user);

        //addUserChangeListener();
    }

}
