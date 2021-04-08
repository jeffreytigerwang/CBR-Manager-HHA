package com.example.cbr.fragments.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.cbr.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Map;

public abstract class BaseFragment extends Fragment {

    protected AlertDialog showErrorDialog(String message, DialogInterface.OnClickListener okListener) {
        return new MaterialAlertDialogBuilder(getContext())
                .setTitle(getString(R.string.error))
                .setMessage(message)
                .setPositiveButton(R.string.ok, okListener)
                .show();
    }

    protected AlertDialog showOkDialog(String title, String message, DialogInterface.OnClickListener okListener) {
        return new MaterialAlertDialogBuilder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, okListener)
                .show();
    }

    protected AlertDialog showOkCancelDialog(String title,
                                   String message,
                                   DialogInterface.OnClickListener okListener,
                                   DialogInterface.OnClickListener cancelListener) {
        return new MaterialAlertDialogBuilder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, okListener)
                .setNegativeButton(R.string.cancel, cancelListener)
                .show();
    }

    protected void launchOneRequestPermission(
            @NonNull String permission,
            @NonNull ActivityResultCallback<Boolean> callback) {

        ActivityResultLauncher<String> requestPermissionLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), callback);

        requestPermissionLauncher.launch(permission);
    }

    protected void launchRequestPermissions(
            @NonNull String[] permissions,
            @NonNull ActivityResultCallback<Map<String, Boolean>> callback) {

        ActivityResultLauncher<String[]> requestPermissionLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.RequestMultiplePermissions(),
                        callback);

        requestPermissionLauncher.launch(permissions);
    }

    protected boolean isPermissionGranted(@NonNull Context context, @NonNull String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }
}
