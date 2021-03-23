package com.example.cbr.fragments.base;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class BaseActivity extends AppCompatActivity {

    protected void addFragment(int containerViewId, Fragment fragment, String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .addToBackStack(fragmentTag)
                .commit();
    }

    /**
     * This function uses
     * {@link androidx.fragment.app.FragmentTransaction#setCustomAnimations(int, int, int, int)
     * setCustomAnimations(int, int, int, int)} to set the animation when entering it's view and
     * popping back to the last fragment/activity.
     *
     * @see BaseActivity#addFragment(int, Fragment, String)
     * */
    protected void addFragmentWithAnimation(int containerViewId, Fragment fragment, String fragmentTag,
                                            @AnimatorRes @AnimRes int enter,
                                            @AnimatorRes @AnimRes int exit,
                                            @AnimatorRes @AnimRes int popEnter,
                                            @AnimatorRes @AnimRes int popExit) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(enter, exit, popEnter, popExit)
                .add(containerViewId, fragment, fragmentTag)
                .addToBackStack(fragmentTag)
                .commit();
    }

    protected void replaceFragment(int containerViewId, Fragment fragment, String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(fragmentTag)
                .commit();
    }
}
