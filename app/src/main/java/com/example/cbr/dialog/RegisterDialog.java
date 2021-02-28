package com.example.cbr.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.cbr.R;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class RegisterDialog extends AppCompatDialogFragment {

    private EditText edt_firstName;
    private EditText edt_lastName;
    private EditText edt_email;
    private EditText edt_password;
    private EditText edt_confirm_password;
    private registerDialogListener listener;
    private static String key = "Bar12345Bar12345";


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
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
                        String firstName = edt_firstName.getText().toString();
                        String lastName = edt_lastName.getText().toString();
                        String email = edt_email.getText().toString();
                        String password = edt_password.getText().toString();
                        String confirmPassword = edt_confirm_password.getText().toString();

                        String encryptPassword = encrypt(password);

                        listener.applyInfo(firstName, lastName, email, encryptPassword, confirmPassword);
                    }
                });

        edt_firstName = view.findViewById(R.id.registration_firstName);
        edt_lastName = view.findViewById(R.id.registration_lastName);
        edt_email = view.findViewById(R.id.registration_email);
        edt_password = view.findViewById(R.id.registration_password);
        edt_confirm_password = view.findViewById(R.id.registration_confirm_password);

        return builder.create();
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
        void applyInfo(String firstName, String lastName, String email, String password, String confirmPassword);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encrypt(String password) {
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
