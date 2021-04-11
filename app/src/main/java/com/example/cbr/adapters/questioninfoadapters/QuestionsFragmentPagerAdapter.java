package com.example.cbr.adapters.questioninfoadapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;
import com.example.cbr.fragments.QuestionsPageFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionsFragmentPagerAdapter extends FragmentStateAdapter {
    private final ArrayList<ViewPagerContainer> totalFragmentsList;
    private final List<ViewPagerContainer> activeFragmentsList;

    public QuestionsFragmentPagerAdapter(@NonNull Fragment fragment, ArrayList<ViewPagerContainer> totalFragmentsList) {
        super(fragment);
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
        return QuestionsPageFragment.newInstance(activeFragmentsList.get(position).getViewHolderDataList(), position);
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

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataChanged(@NonNull InfoAdapter.DataChangedEvent dataChangedEvent) {
        int position = dataChangedEvent.getPositionChanged();
        QuestionDataContainer changedContainer = dataChangedEvent.getQuestionDataContainer();

        OnViewPagerChangedListener onViewPagerChangedListener = null;
        for (ViewPagerContainer viewPagerContainer : activeFragmentsList) {
            ArrayList<QuestionDataContainer> currentList = viewPagerContainer.getViewHolderDataList();
            if (position < currentList.size() && currentList.get(position) == changedContainer) {
                onViewPagerChangedListener = viewPagerContainer.onViewPagerChangedListener;
            }
        }

        if (onViewPagerChangedListener != null) {
            onViewPagerChangedListener.onChanged(position, changedContainer);
        }
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
