package com.example.wordgame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wordgame.databinding.FragmentPlayBinding;
import com.example.wordgame.databinding.FragmentStartBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link play#newInstance} factory method to
 * create an instance of this fragment.
 */
public class play extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static play onplay;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private @NonNull FragmentPlayBinding binding;
    private boolean tabs;
    public play() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment play.
     */
    // TODO: Rename and change types and number of parameters
    public static play newInstance(String param1, String param2) {
        play fragment = new play();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * Handle events of back pressed
     * Handle event of the play CardView buttons
     * Inflate  play layout
     * @param inflater of play
     * @param container of play
     * @param savedInstanceState for play
     * @return view of play
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPlayBinding.inflate(inflater, container, false);
        onplay = this;
         if(getTabsStatus()){
             ((MainActivity) requireActivity()).backUpPressed(play.this,R.id.action_SecondFragment_to_FirstFragment);

         }else
            ((MainActivity) requireActivity()).backUpPressed(play.this,R.id.action_play_to_SecondFragment);

        binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(play.this)
                        .navigate(R.id.trueFalseFragment);
            }
        });

        binding.cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(play.this)
                        .navigate(R.id.multipleChoiceFragment);
            }
        });


        binding.cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(play.this)
                        .navigate(R.id.matchingFragment);
            }
        });

        binding.cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(play.this)
                        .navigate(R.id.translationFragment);
            }
        });

        return binding.getRoot();

    }

    /**
     * set binding null
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * set method for play fragment status
     * @param status of play fragment
     */
    public void  tabStatus(boolean status){
        tabs=status;
    }

    /**
     * get status of play fragment
     * status is true if play fragment is viewed through tabs
     * else  false
     * @return true if play is viewed in tabs else false
     */
    private boolean getTabsStatus(){
        return  tabs;
    }
}