package com.example.cbr.activities;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.example.cbr.R;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cbr.dialog.RegisterDialog;
import com.example.cbr.models.Users;
import com.example.cbr.retrofit.AES;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements RegisterDialog.registerDialogListener{


    // Init API
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Button btn_register;
    private EditText edt_username;
    private EditText edt_password;
    private final String key = "Bar12345Bar12345";

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
        btn_register = (Button) findViewById(R.id.btn_register);

        edt_username = findViewById(R.id.mainActivity_username);
        edt_password = findViewById(R.id.mainActivity_password);

        setupRegisterButton();

        setupLoginButton();
    }


    private void setupLoginButton() {
        Button button = findViewById(R.id.button_login);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                userLogin(edt_username.getText().toString(), edt_password.getText().toString());
            }
        });
    }

    private void setupRegisterButton() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }


    // API call for user login functionality including check password
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void userLogin(final String email, final String password) {

        Call<List<Users>> call = jsonPlaceHolderApi.getUserEmail(email);

        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Users> userResponse = response.body();

                int successfulLogin = 0;
                int checkPassword = 0;

                for (Users users: userResponse) {
                    if (users.getEmail().equals(email)) {

                        final String decryptPassword = AES.decrypt(users.getPassword());

                        if (!decryptPassword.equals(password)) {
                            checkPassword = 1;
                            Toast.makeText(MainActivity.this, "You Enter the Wrong Password", Toast.LENGTH_SHORT).show();
                        } else {
                            successfulLogin = 1;
                            Users userInstance = Users.getInstance();
                            userInstance.setFirstName(users.getFirstName());
                            userInstance.setLastName(users.getLastName());
                            userInstance.setEmail(email);
                            userInstance.setPassword(password);

                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                            Intent intent = HomeActivity.makeIntent(MainActivity.this);
                            startActivity(intent);
                        }
                    }
                }

                if (successfulLogin == 0)
                    if (checkPassword == 0)
                        Toast.makeText(MainActivity.this, "Please Register First!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {

                Intent intent = HomeActivity.makeIntent(MainActivity.this);
                startActivity(intent);
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String decrypt(String encryptPassword) {
        try {
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            byte [] encrypted = Base64.getDecoder().decode(encryptPassword);

            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String decrypted = new String(cipher.doFinal(encrypted));
            System.out.println(decrypted);

            return decrypted;

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return "NULL";
    }


    private void registerUser() {
        RegisterDialog registerDialog = new RegisterDialog();
        registerDialog.show(getSupportFragmentManager(), "Register");
    }

    @Override
    public void applyInfo(String firstName, String lastName, String email, String password, String confirmPassword) {
        createUser(firstName, lastName, email, password);
    }


    // API call for registration users
    private void createUser(String firstName, String lastName, String email, String password) {
        Users users = Users.getInstance();
        users.setFirstName(firstName);
        users.setLastName(lastName);
        users.setEmail(email);
        users.setPassword(password);

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
            }

            @Override
            public void onFailure(@NonNull Call<Users> call, @NonNull Throwable t) {

            }
        });
    }

}
