package com.example.wordgame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.wordgame.databinding.WelcomwindowBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Class to initiate word game.
 * Allow user choose level of play and also navigate to see progress of learning
 */

public class WelcomeWindow extends Fragment {

    /**
     * @serialField binding data binding for Welcome fragment
     */
    private WelcomwindowBinding binding;

    /**
     * creates view of Welcome fragment
     * @param inflater to inflate Welcome fragment
     * @param container for Welcome fragment
     * @param savedInstanceState of Welcome fragment
     * @return view of Welcome fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = WelcomwindowBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    /**
     * handles button events of the created view
     * @param view welcome fragment created view
     * @param savedInstanceState of welcome frament
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       levelOne();
       levelTwo();
       levelThree();

    }

    /**
     * handle button level one event
     */
    private void levelOne(){
        binding.level1IDBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(WelcomeWindow.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
    /**
     * handle button level two event
     */
    private void levelTwo(){
        binding.level2IDBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(WelcomeWindow.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
    /**
     * handle button level three event
     */
    private void levelThree(){
        binding.level3IDBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(WelcomeWindow.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    /**
     * set bottom navigation Visible on resume of welcome window
     */
    @Override
    public void onResume() {
        super.onResume();

        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigationID);
        bottomNavigationView.setVisibility(View.VISIBLE);
        bottomNavigationView.getMenu().findItem(R.id.homeIdItem).setChecked(true);
    }

    /**
     * set data binding for this fragment null
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}