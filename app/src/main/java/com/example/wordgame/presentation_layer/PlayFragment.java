package com.example.wordgame.presentation_layer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentPlayBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static PlayFragment onplay;
    private Menu menu;

    private @NonNull FragmentPlayBinding binding;
    private boolean tabs;
    public PlayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayFragment.
     */
    public static PlayFragment newInstance(String param1, String param2) {
        PlayFragment fragment = new PlayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem results = menu.findItem(R.id.resultsId);
        results.setVisible(true);
        this.menu =menu;
    }

    /**
     * Handle events of back pressed
     * Handle event of the PlayFragment CardView buttons
     * Inflate  PlayFragment layout
     * @param inflater of PlayFragment
     * @param container of PlayFragment
     * @param savedInstanceState for PlayFragment
     * @return view of PlayFragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPlayBinding.inflate(inflater, container, false);
        onplay = this;
         if(getTabsStatus()){
             ((MainActivity) requireActivity()).backUpPressed(PlayFragment.this,R.id.action_SecondFragment_to_FirstFragment);

         }else
            ((MainActivity) requireActivity()).backUpPressed(PlayFragment.this,R.id.action_play_to_SecondFragment);

        binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(PlayFragment.this)
                        .navigate(R.id.trueFalseFragment);
            }
        });

        binding.cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(PlayFragment.this)
                        .navigate(R.id.multipleChoiceFragment);
            }
        });


        binding.cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(PlayFragment.this)
                        .navigate(R.id.matchingFragment);
            }
        });

        binding.cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(PlayFragment.this)
                        .navigate(R.id.translationFragment);
            }
        });
        /**
         * handle click for  fab and navigate to score of the user
         */
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDestination navDestination = Navigation.findNavController((MainActivity) requireActivity(),
                        R.id.nav_host_fragment_content_main).getCurrentDestination();
                if(navDestination.getId()==R.id.play)
                NavHostFragment.findNavController(PlayFragment.this).
                        navigate(R.id.action_play_to_proggress);
                else {
                    NavHostFragment.findNavController(PlayFragment.this).
                            navigate(R.id.action_SecondFragment_to_proggress);
                }

            }
        });


        return binding.getRoot();

    }

    @Override
    public void onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu();
        MenuItem menuItem=menu.findItem(R.id.resultsId);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menuItem.setVisible(false);
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
     * set method for PlayFragment fragment status
     * @param status of PlayFragment fragment
     */
    public void  tabStatus(boolean status){
        tabs=status;
    }

    /**
     * get status of PlayFragment fragment
     * status is true if PlayFragment fragment is viewed through tabs
     * else  false
     * @return true if PlayFragment is viewed in tabs else false
     */
    private boolean getTabsStatus(){
        return  tabs;
    }
}