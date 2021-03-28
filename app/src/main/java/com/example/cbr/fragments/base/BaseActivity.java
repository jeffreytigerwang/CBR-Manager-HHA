package com.example.cbr.fragments.base;

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
     * <p>Uses the built in {@code android.R.anim.fade_in} and
     * {@code android.R.anim.fade_out} animations.
     *
     * @see BaseActivity#addFragment(int, Fragment, String)
     * */
    protected void addFragmentWithAnimation(int containerViewId, Fragment fragment, String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                        android.R.anim.fade_in, android.R.anim.fade_out)
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
