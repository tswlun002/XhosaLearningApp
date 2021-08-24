package com.example.wordgame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.wordgame.databinding.WelcomwindowBinding;

public class WelcomeWindow extends Fragment {

    private WelcomwindowBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = WelcomwindowBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*binding.nextBTNID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(WelcomeWindow.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });*/

        binding.level1IDBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(WelcomeWindow.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.level2IDBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(WelcomeWindow.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });


        binding.level3IDBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(WelcomeWindow.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}