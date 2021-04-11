package com.example.cbr.fragments.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cbr.databinding.FragmentNotificationBinding;
import com.example.cbr.fragments.base.BaseFragment;

public class NotificationFragment extends BaseFragment implements NotificationContract.View {
    private FragmentNotificationBinding binding;
    private NotificationContract.Presenter notificationContractPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setPresenter(new NotificationPresenter(this));

        binding = FragmentNotificationBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        return view;
    }

    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    public static String getFragmentTag() {
        return NotificationFragment.class.getSimpleName();
    }


    @Override
    public void setPresenter(NotificationContract.Presenter presenter) {
        notificationContractPresenter = presenter;
    }
}
