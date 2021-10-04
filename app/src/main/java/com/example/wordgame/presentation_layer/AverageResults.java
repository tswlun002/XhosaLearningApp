package com.example.wordgame.presentation_layer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentAverageResultsBinding;
import com.example.wordgame.databinding.FragmentProggressBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AverageResults#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AverageResults extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentAverageResultsBinding binding;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAverageResultsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}