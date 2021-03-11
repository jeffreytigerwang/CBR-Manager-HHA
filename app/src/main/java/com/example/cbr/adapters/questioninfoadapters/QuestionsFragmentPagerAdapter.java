package com.example.cbr.adapters.questioninfoadapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cbr.adapters.questioninfoadapters.BaseInfoAdapter;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;
import com.example.cbr.fragments.QuestionsPageFragment;

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
        return QuestionsPageFragment.newInstance(totalFragmentsList.get(position).getViewHolderDataList());
    }

    @Override
    public int getItemCount() {
        return totalFragmentsList.size();
    }

//    @Override
//    public long getItemId(int position) {
//
//    }

    public static class ViewPagerContainer {
        private final List<QuestionDataContainer> viewHolderDataList;
        private boolean isActive;
        private boolean isOnScreen;

        public ViewPagerContainer(List<QuestionDataContainer> viewHolderDataList, boolean isActive) {
            this.viewHolderDataList = viewHolderDataList;
            this.isActive = isActive;
            isOnScreen = false;
        }

        public List<QuestionDataContainer> getViewHolderDataList() {
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
