package com.example.cbr.adapters.questioninfoadapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cbr.adapters.questioninfoadapters.BaseInfoAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuestionsFragmentPagerAdapter extends FragmentStateAdapter {
    private List<ViewPagerContainer> totalFragmentsList;
    private List<ViewPagerContainer> activeFragmentsList;

    public QuestionsFragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<ViewPagerContainer> totalFragmentsList) {
        super(fragmentActivity);
        this.totalFragmentsList = totalFragmentsList;
        activeFragmentsList = new ArrayList<>();
        updatePages();
    }

    private void updatePages() {

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int numActive = 0;
        for (ViewPagerContainer viewPagerContainer : totalFragmentsList) {

        }
    }

    @Override
    public int getItemCount() {
        int i = 0;
        for (ViewPagerContainer viewPagerContainer: totalFragmentsList) {
            if (viewPagerContainer.isActive()) {
                i++;
            }
        }
        return i;
    }

    @Override
    public long getItemId(int position) {

    }

    class ViewPagerContainer {
        private final List<BaseInfoAdapter.QuestionDataContainer> viewHolderDataList;
        private boolean isActive;
        private boolean isOnScreen;

        public ViewPagerContainer(List<BaseInfoAdapter.QuestionDataContainer> viewHolderDataList, boolean isActive) {
            this.viewHolderDataList = viewHolderDataList;
            this.isActive = isActive;
            isOnScreen = false;
        }

        public List<BaseInfoAdapter.QuestionDataContainer> getViewHolderDataList() {
            return viewHolderDataList;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public boolean isOnScreen() {
            return isOnScreen;
        }

        public void setOnScreen(boolean onScreen) {
            isOnScreen = onScreen;
        }
    }
}
