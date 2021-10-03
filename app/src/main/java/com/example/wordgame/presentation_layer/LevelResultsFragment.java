package com.example.wordgame.presentation_layer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wordgame.R;
import com.example.wordgame.model_layer.LevelResults;
import com.example.wordgame.model_layer.LevelResultsViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LevelResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LevelResultsFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private LevelResultsViewModel levelResultsViewModel;
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
    // TODO: Rename and change types and number of parameters
    public static LevelResultsFragment newInstance(String param1, String param2) {
        LevelResultsFragment fragment = new LevelResultsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        levelResultsViewModel = new ViewModelProvider(this).get(LevelResultsViewModel.class);
        insertToResultsDB(levelResultsViewModel);
    }

    private void insertToResultsDB(LevelResultsViewModel levelResultsViewModel){

        levelResultsViewModel.insert(new LevelResults());
    }
   /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(requireContext(),"Here On results",Toast.LENGTH_LONG).show();
        return inflater.inflate(R.layout.fragment_results__current_activity, container, false);
    }*/
}