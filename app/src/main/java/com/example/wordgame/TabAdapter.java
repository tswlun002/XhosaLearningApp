package com.example.wordgame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * subclass of  FragmentStateAdapter
 * set up tabs in a viewPager2
 */
public class TabAdapter  extends FragmentStateAdapter {

    private play status;

    /**
     * constructor initialise parent class FragmentStateAdapter
     * @param fragmentActivity of second fragment
     */
    public TabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    /**
     * creates frament when position of the tab is clicked
     * When position zero , learn fragment is created
     * when position 1 , play fragment is created
     * @param position of each fragment
     * @return fragment at clicked position
     */
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
                ((play) fragment).tabStatus(true);
                break;
        }
        assert fragment != null;
        return fragment;
    }

    /**
     * get number of tabs
     * @return number of tabs
     */
    @Override
    public int getItemCount() {
        return 2;
    }

    public  void playstatus(play s){
        status= s;
    }

    public  play getStatus(){
        return  status;
    }
}


