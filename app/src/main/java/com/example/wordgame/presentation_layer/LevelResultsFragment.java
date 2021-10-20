package com.example.wordgame.presentation_layer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;

import com.example.wordgame.R;
import com.example.wordgame.model_layer.LevelResults;
import com.example.wordgame.model_layer.LevelResultsViewModel;
import com.example.wordgame.model_layer.WordGameViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LevelResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LevelResultsFragment extends Fragment {

    private  WordGameViewModel wordGameViewModel;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LevelResultsViewModel levelResultsViewModel;
    int count  =0;
    public LevelResultsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LevelResultsFragment.
     */
    public static LevelResultsFragment newInstance(String param1, String param2) {
        LevelResultsFragment fragment = new LevelResultsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * creates the fragment level results
     * Initialise levelResultsViewModel  and wordGameViewModel;
     * levelResultsViewModel is given ownership of the fragment lifecycle
     * @param savedInstanceState of level results fragment
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        levelResultsViewModel = new ViewModelProvider(this).get(LevelResultsViewModel.class);
        wordGameViewModel = new ViewModelProvider(requireActivity()).get(WordGameViewModel.class);
    }

    /**
     * Hides all its view but open them as a dialog
     * @param inflater of LevelResultsFragment
     * @param container of LevelResultsFragment
     * @param savedInstanceState of LevelResultsFragment
     * @return view of LevelResultsFragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_results__current_activity, container, false);
        view.findViewById(R.id.NextButtonId).setVisibility(View.GONE);
        view.findViewById(R.id.ExitBitton).setVisibility(View.GONE);
        view.findViewById(R.id.gradesActivity).setVisibility(View.GONE);
        view.findViewById(R.id.CorrectionsID).setVisibility(View.GONE);
        insertToLevelResultsDB(levelResultsViewModel);


        return view;
    }

    /**
     * Hide the toolbar of the application
     */
    @Override
    public void onResume() {
        super.onResume();
        Toolbar toolbar  = requireActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);


    }

    /**
     * Insert data of the level results  into database using help of view model wordGameViewModel
     * which is shared by all fragments
     * After insert each results , we get maximum  results of each game and update
     * Progress of the user
     * @param levelResultsViewModel is the view model of  level results instance
     */
    private void insertToLevelResultsDB(LevelResultsViewModel levelResultsViewModel){
        wordGameViewModel.getResults().observe(getViewLifecycleOwner(), new Observer<LevelResults>() {
            @Override
            public void onChanged(LevelResults levelResults) {
                levelResultsViewModel.insert(levelResults);
                getHighestScore();

            }
        });



    }

    /**
     * Get  maximum score of current level of each game
     * Use class  ComputeProgressReport  to compute scores
     * And update progress of the user
     */
    void getHighestScore(){
        levelResultsViewModel.getallgradeslevelone().observe(getViewLifecycleOwner(), new Observer<List<LevelResults>>() {
            @Override
            public void onChanged(List<LevelResults> levelResults) {
                ComputeProgressReport computeProgressReport =
                        new ComputeProgressReport(requireActivity().getApplication());
                computeProgressReport.setLevelResultsList(levelResults);
                computeProgressReport.computeScores();
                computeProgressReport.insert(getViewLifecycleOwner());
            }
        });
    }


    /**
     * On view destroy Toolbar of the application is set visible again
     */
    @Override
    public void onDestroyView() {
        Toolbar toolbar  = requireActivity().findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);

        super.onDestroyView();
    }

}