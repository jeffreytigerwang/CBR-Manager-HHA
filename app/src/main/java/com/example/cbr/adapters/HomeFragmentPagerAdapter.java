package com.example.cbr.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cbr.fragments.DashboardFragment;
import com.example.cbr.fragments.clientlist.ClientListFragment;
import com.example.cbr.fragments.discussion.DiscussionFragment;
import com.example.cbr.fragments.home.HomePageFragment;
import com.example.cbr.fragments.notification.NotificationFragment;

public class HomeFragmentPagerAdapter extends FragmentStateAdapter {

    public static final int HOME_POSITION = 0;
    public static final int DASHBOARD_POSITION = 1;
    public static final int LIST_POSITION = 2;
    public static final int DISCUSSION_POSITION = 3;
    public static final int NOTIFICATION_POSITION = 4;

    public HomeFragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        initBaseFragments();
    }


    private void initBaseFragments() {
        createFragment(HOME_POSITION);
        createFragment(DASHBOARD_POSITION);
        createFragment(LIST_POSITION);
        createFragment(DISCUSSION_POSITION);
        createFragment(NOTIFICATION_POSITION);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case HOME_POSITION:
                return HomePageFragment.newInstance();
            case DASHBOARD_POSITION:
                return DashboardFragment.newInstance();
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
        return 5;
    }
}
