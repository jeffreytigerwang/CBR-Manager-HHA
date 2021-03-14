package com.example.cbr.adapters.questioninfoadapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cbr.adapters.questioninfoadapters.BaseInfoAdapter;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;
import com.example.cbr.fragments.QuestionsPageFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionsFragmentPagerAdapter extends FragmentStateAdapter {
    private final ArrayList<ViewPagerContainer> totalFragmentsList;
    private final List<ViewPagerContainer> activeFragmentsList;

    public QuestionsFragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<ViewPagerContainer> totalFragmentsList) {
        super(fragmentActivity);
        this.totalFragmentsList = totalFragmentsList;
        activeFragmentsList = new ArrayList<>();
        updatePages();
    }

    public void updatePages() {
        activeFragmentsList.clear();
        for (ViewPagerContainer viewPagerContainer : totalFragmentsList) {
            if (viewPagerContainer.isActive()) {
                activeFragmentsList.add(viewPagerContainer);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return QuestionsPageFragment.newInstance(activeFragmentsList.get(position));
    }

    @Override
    public int getItemCount() {
        return activeFragmentsList.size();
    }

    public static class ViewPagerContainer implements Serializable {
        private final ArrayList<QuestionDataContainer> viewHolderDataList;
        private final OnViewPagerChangedListener onViewPagerChangedListener;
        private boolean isActive;
        private boolean isOnScreen;

        public ViewPagerContainer(ArrayList<QuestionDataContainer> viewHolderDataList, boolean isActive, OnViewPagerChangedListener onViewPagerChangedListener) {
            this.viewHolderDataList = viewHolderDataList;
            this.isActive = isActive;
            this.onViewPagerChangedListener = onViewPagerChangedListener;
            isOnScreen = false;
        }

        public ArrayList<QuestionDataContainer> getViewHolderDataList() {
            return viewHolderDataList;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public OnViewPagerChangedListener getOnViewPagerChangedListener() {
            return onViewPagerChangedListener;
        }

        public boolean isOnScreen() {
            return isOnScreen;
        }

        public void setOnScreen(boolean onScreen) {
            isOnScreen = onScreen;
        }
    }

    public interface OnViewPagerChangedListener {
        void onChanged(int positionChanged);
    }
}
