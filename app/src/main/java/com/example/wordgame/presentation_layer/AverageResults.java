package com.example.wordgame.presentation_layer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentAverageResultsBinding;
import com.example.wordgame.model_layer.ProgressReport;
import com.example.wordgame.model_layer.ProgressViewModel;
import com.example.wordgame.model_layer.WordGameViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AverageResults#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AverageResults extends Fragment {

    /**
     * build in android fields
     */
    private FragmentAverageResultsBinding binding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    /**
     * @serialField  progressViewModel is the instance of the  progress view model
     */
    private ProgressViewModel progressViewModel;

    /**
     * defaulted constructor of  AverageResults fragment
     */
    public AverageResults() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AverageResults.
     */
    public static AverageResults newInstance(String param1, String param2) {
        AverageResults fragment = new AverageResults();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * creates the fragment average AverageResults
     * Initialise the progressViewModel instance
     * @param savedInstanceState is the state instance of  AverageResults
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        progressViewModel = new ViewModelProvider(this).get(ProgressViewModel.class);
    }

    /**
     * creates all the view components of the AverageResults
     * @param inflater inflate the AverageResults
     * @param container of AverageResults
     * @param savedInstanceState of AverageResults
     * @return view of AverageResults
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAverageResultsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    /**
     * Created view of AverageResults
     * When  view is being created, we get  progress data
     * @param view is the of AverageResults
     * @param savedInstanceState is the of AverageResults
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getProgressData(view);
    }

    /**
     * Used to get all data of progress of the user  using livedata
     * Uses ProgressHandler class to display all the progress data 
     * @param view is the view of  AverageResults
     */
    private void getProgressData(View view){
        progressViewModel.getGameMaterial().observe(getViewLifecycleOwner(), new Observer<List<ProgressReport>>() {
            @Override
            public void onChanged(List<ProgressReport> progressReports) {
                ProgressHandler progressHandler = new ProgressHandler(view);
                progressHandler.setData(progressReports);
            }
        });
    }
}