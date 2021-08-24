package com.example.wordgame;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabAdapter  extends FragmentStateAdapter {


    public TabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

      Fragment fragment = null;
        switch (position){

            case 0:
                fragment=  new learn();
                break;
            case 1:
                fragment= new play();
                break;
        }
        assert fragment != null;
        return  fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}


