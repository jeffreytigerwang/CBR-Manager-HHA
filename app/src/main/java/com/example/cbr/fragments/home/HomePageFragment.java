package com.example.cbr.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cbr.R;
import com.example.cbr.databinding.FragmentHomePageBinding;
import com.example.cbr.fragments.base.BaseFragment;


public class HomePageFragment extends BaseFragment implements HomePageContract.View{
    private HomePageContract.Presenter homePagePresenter;
    private FragmentHomePageBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new HomePagePresenter(this));

        binding = FragmentHomePageBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        return view;
    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        homePagePresenter = presenter;
    }


    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }

    public static String getFragmentTag() {
        return HomePageFragment.class.getSimpleName();
    }

    public interface HomePageFragmentInterface {
        void swapToHomePageFragment();
    }
}
