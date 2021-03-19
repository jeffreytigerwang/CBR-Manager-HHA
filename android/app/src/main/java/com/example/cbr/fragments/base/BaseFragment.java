package com.example.cbr.fragments.base;

import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.cbr.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

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
}
