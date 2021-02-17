package com.example.cbr.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.cbr.R;

public class RegisterDialog extends AppCompatDialogFragment {

    private EditText edt_firstName;
    private EditText edt_lastName;
    private EditText edt_email;
    private EditText edt_password;
    private registerDialogListener listener;


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
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String firstName = edt_firstName.getText().toString();
                        String lastName = edt_lastName.getText().toString();
                        String email = edt_email.getText().toString();
                        String password = edt_password.getText().toString();

                        listener.applyInfo(firstName, lastName, email, password);
                    }
                });

        edt_firstName = view.findViewById(R.id.edt_firstName);
        edt_lastName = view.findViewById(R.id.edt_lastName);
        edt_email = view.findViewById(R.id.edt_email);
        edt_password = view.findViewById(R.id.edt_password);

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
        void applyInfo(String firstName, String lastName, String email, String password);
    }
}
