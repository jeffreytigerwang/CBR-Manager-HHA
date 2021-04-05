package com.example.cbr.adapters.questioninfoadapters;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

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

    public void setPageActive(int page, boolean isActive) {
        if (totalFragmentsList.get(page).isActive() != isActive) {
            totalFragmentsList.get(page).setActive(isActive);
            updatePages();
        }
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

    @Override
    public boolean containsItem(long itemId) {
        for (ViewPagerContainer viewPagerContainer : activeFragmentsList) {
            if (viewPagerContainer.hashCode() == itemId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public long getItemId(int position) {
        return activeFragmentsList.get(position).hashCode();
    }

    public static class ViewPagerContainer implements Serializable {
        private final ArrayList<QuestionDataContainer> viewHolderDataList;
        private final OnViewPagerChangedListener onViewPagerChangedListener;
        private boolean isActive;

        public ViewPagerContainer(ArrayList<QuestionDataContainer> viewHolderDataList, boolean isActive, OnViewPagerChangedListener onViewPagerChangedListener) {
            this.viewHolderDataList = viewHolderDataList;
            this.isActive = isActive;
            this.onViewPagerChangedListener = onViewPagerChangedListener;
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
    }

    public interface OnViewPagerChangedListener {
        void onChanged(int positionChanged, QuestionDataContainer questionDataContainer);
    }
}
