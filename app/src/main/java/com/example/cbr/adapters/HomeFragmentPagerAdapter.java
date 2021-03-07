package com.example.cbr.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cbr.fragments.TempHomeFragment;
import com.example.cbr.fragments.clientlist.ClientListFragment;
import com.example.cbr.fragments.discussion.DiscussionFragment;
import com.example.cbr.fragments.notification.NotificationFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentPagerAdapter extends FragmentStateAdapter {

    public static final int HOME_POSITION = 0;
    public static final int LIST_POSITION = 1;
    public static final int DISCUSSION_POSITION = 2;
    public static final int NOTIFICATION_POSITION = 3;

    public HomeFragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        initBaseFragments();
    }


    private void initBaseFragments() {
        createFragment(HOME_POSITION);
        createFragment(LIST_POSITION);
        createFragment(DISCUSSION_POSITION);
        createFragment(NOTIFICATION_POSITION);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case HOME_POSITION:
                return TempHomeFragment.newInstance();
            case LIST_POSITION:
                return ClientListFragment.newInstance();
            case DISCUSSION_POSITION:
                return DiscussionFragment.newInstance();
            case NOTIFICATION_POSITION:
                return NotificationFragment.newInstance();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
