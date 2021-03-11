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
    private ArrayList<ViewPagerContainer> totalFragmentsList;
    private List<ViewPagerContainer> activeFragmentsList;

    public QuestionsFragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<ViewPagerContainer> totalFragmentsList) {
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
        return QuestionsPageFragment.newInstance(totalFragmentsList.get(position));
    }

    @Override
    public int getItemCount() {
        return totalFragmentsList.size();
    }

//    @Override
//    public long getItemId(int position) {
//
//    }

    public static class ViewPagerContainer implements Serializable {
        private final ArrayList<QuestionDataContainer> viewHolderDataList;
        private boolean isActive;
        private OnViewPagerChangedListener onViewPagerChangedListener;
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
