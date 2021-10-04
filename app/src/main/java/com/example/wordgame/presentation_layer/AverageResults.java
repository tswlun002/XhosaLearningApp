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

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentAverageResultsBinding binding;
    private WordGameViewModel wordGameViewModel;
    private ProgressViewModel progressViewModel;
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
    // TODO: Rename and change types and number of parameters
    public static AverageResults newInstance(String param1, String param2) {
        AverageResults fragment = new AverageResults();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //wordGameViewModel = new ViewModelProvider(requireActivity()).get(WordGameViewModel.class);
        progressViewModel = new ViewModelProvider(this).get(ProgressViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAverageResultsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getProgressData(view);
    }

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