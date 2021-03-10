package com.example.cbr.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.cbr.R;
import com.example.cbr.models.Users;
import com.example.cbr.retrofit.AES;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterDialog extends AppCompatDialogFragment {

    private EditText edt_firstName;
    private EditText edt_lastName;
    private EditText edt_phone;
    private EditText edt_password;
    private EditText edt_confirm_password;
    private registerDialogListener listener;
    private final String key = "Bar12345Bar12345";

    // Init API
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Init Retrofit & NodeJs stuff
        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_register_dialog, null);

        builder.setView(view)
                .setTitle("Register")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Register", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        final AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean isPasswordMatch = false;
                boolean isUserNameExist = false;

                String firstName = edt_firstName.getText().toString();
                String lastName = edt_lastName.getText().toString();
                String phone = edt_phone.getText().toString();
                String password = edt_password.getText().toString();
                String confirmPassword = edt_confirm_password.getText().toString();

                if (password.equals(confirmPassword)) {
                    isPasswordMatch = true;
                }

                try {
                    if (getUsers(phone))
                        isUserNameExist = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (isUserNameExist)
                    Toast.makeText(getActivity(), "Phone number already exists!", Toast.LENGTH_SHORT).show();
                else if (!isPasswordMatch)
                    Toast.makeText(getActivity(), "Password not match!", Toast.LENGTH_SHORT).show();
                else {
                    String encryptPassword = AES.encrypt(password);
                    listener.applyInfo(firstName, lastName, phone, encryptPassword, confirmPassword);
                    dialog.dismiss();
                }
            }
        });


        edt_firstName = view.findViewById(R.id.registerDialog_firstNameEditText);
        edt_lastName = view.findViewById(R.id.registerDialog_lastNameEditText);
        edt_phone = view.findViewById(R.id.registerDialog_phoneNumberEditText);
        edt_password = view.findViewById(R.id.registerDialog_passwordEditText);
        edt_confirm_password = view.findViewById(R.id.registerDialog_confirmPasswordEditText);

        return dialog;
    }


    private boolean getUsers(String username) throws IOException {
        Call<List<Users>> call = jsonPlaceHolderApi.getUsers();

        Response<List<Users>> response = call.execute();
        List<Users> usersList = response.body();

        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getPhone().equals(username)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (registerDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement dialog");
        }
    }

    public interface registerDialogListener {
        void applyInfo(String firstName, String lastName, String phone, String password, String confirmPassword);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String encrypt(String password) {
        try {
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(password.getBytes("UTF-8"));

            return Base64.getEncoder().encodeToString(encrypted);

        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "NULL";
    }
}
