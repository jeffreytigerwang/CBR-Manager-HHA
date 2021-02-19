package com.example.cbr.fragments.discussion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cbr.R;

public class DiscussionFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dicussion, container, false);
    }

    public static DiscussionFragment newInstance() {
        return new DiscussionFragment();
    }

    public static String getFragmentTag() {
        return DiscussionFragment.class.getSimpleName();
    }
}
