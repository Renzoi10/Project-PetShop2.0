package com.utp.petfriendly.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.utp.petfriendly.R;
import com.utp.petfriendly.view.fragment.HomeFragment;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setFragment(new HomeFragment());
    }

    public void setFragment(Fragment fragment) {
        if (null != fragment) {
            fragmentManager = getSupportFragmentManager();
            boolean existFragment = false;
            List<Fragment> fragments = fragmentManager.getFragments();
            if (fragments != null) {
                for (Fragment mFragment : fragments) {
                    if (mFragment != null && mFragment.isVisible()) {
                        if (mFragment.getClass().getName().equals(fragment.getClass().getSimpleName())) {
                            existFragment = true;
                        }
                    }
                }
            }
            if (!existFragment) {
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .replace(R.id.content_main, fragment)
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack(null).commit();
            } else {
                fragmentManager.beginTransaction().remove(fragment)
                        .replace(R.id.content_main, fragment)
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack(null).commit();
            }
        }
    }
}

