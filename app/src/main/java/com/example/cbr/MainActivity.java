package com.example.cbr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.cbr.dialog.RegisterDialog;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.Users;
import com.example.cbr.retrofit.INodeJS;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;
import com.example.cbr.activities.HomeActivity;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.material.button.MaterialButton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements RegisterDialog.registerDialogListener{


    // Init API
    Retrofit retrofit;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    Button btn_register;
    Button btn_login;
    EditText edt_username;
    EditText edt_password;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init Retrofit & NodeJs stuff
        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        // Get button and edit text
        btn_register = (MaterialButton) findViewById(R.id.btn_register);
        btn_login = (MaterialButton) findViewById(R.id.btn_login);

        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        setupLoginButton();
    }


    private void setupLoginButton() {
        Button button = findViewById(R.id.button_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = HomeActivity.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    private void userLogin(String email, String password) {

    }


    private void registerUser() {
        RegisterDialog registerDialog = new RegisterDialog();
        registerDialog.show(getSupportFragmentManager(), "Register");
    }

    @Override
    public void applyInfo(String firstName, String lastName, String email, String password) {
        createUser(firstName, lastName, email, password);
    }


    // API call for registration users
    private void createUser(String firstName, String lastName, String email, String password) {
        Users users = new Users(firstName, lastName, email, password);

        System.out.println(users.getFirstName() + "-------------");

        Call<Users> call = jsonPlaceHolderApi.createUser(users);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(@NonNull Call<Users> call, @NonNull Response<Users> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(MainActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();

                Users usersResponse = response.body();
                edt_username.setText(usersResponse.getEmail());
                edt_password.setText(usersResponse.getPassword());
            }

            @Override
            public void onFailure(@NonNull Call<Users> call, @NonNull Throwable t) {

            }
        });
    }



}














//    private void registerUser(final String email, final String password) {
//        final View enter_name_view = LayoutInflater.from(this).inflate(R.layout.enter_name_layout, null);
//
//        new MaterialStyledDialog.Builder(this)
//                .setTitle("Register")
//                .setDescription("One more step!")
//                .setCustomView(enter_name_view)
//                .setNegativeText("Cancel")
//                .onNegative(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        dialog.dismiss();
//                    }
//                })
//                .setPositiveText("Register")
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        // Get the value from edt)name
//                        EditText edt_name = (EditText)enter_name_view.findViewById(R.id.edt_name);
//
//                        compositeDisposable.add(myAPI.registerUser(email, edt_name.getText().toString(), password)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Consumer<String>() {
//                            @Override
//                            public void accept(String s) throws Exception {
//                                Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
//                            }
//                        }));
//                    }
//                }).show();
//    }


//    private void loginUser(String email, String password) {
//        compositeDisposable.add(myAPI.loginUser(email, password)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        if (s.contains("password"))
//                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(MainActivity.this, "" + s, Toast.LENGTH_LONG).show();
//                    }
//                })
//        );
//    }
//

//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loginUser(edt_username.getText().toString(), edt_password.getText().toString());
//            }
//        });
