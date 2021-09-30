package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentTrueBinding;
import com.example.wordgame.model_layer.TrueFalseGame;
import com.example.wordgame.model_layer.TrueFalseViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrueFalseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrueFalseFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public  TrueFalseViewModel trueFalseViewModel;
    public   TrueFalseAdapter trueFalseController;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int pos;

    //Ini
    @SuppressLint("StaticFieldLeak")

    private FragmentTrueBinding binding;
    private LayoutInflater inflater;



    public TrueFalseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrueFalseFragment newInstance(String param1, String param2) {
        TrueFalseFragment fragment = new TrueFalseFragment();
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
        trueFalseViewModel = new ViewModelProvider(this).get(TrueFalseViewModel.class);
    }

    /**
     * creates TrueFalseFragment
     * set up recycle view by calling helper method setUpRecycleView
     * set data to recycle view using helper method setData
     * @param inflater of TrueFalseFragment
     * @param container of TrueFalseFragment
     * @param savedInstanceState for TrueFalseFragment
     * @return view of TrueFalseFragment
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTrueBinding.inflate(inflater, container, false);
        this.inflater = inflater;
        setUpRecycleView(binding);
        setData(trueFalseViewModel);
        return  binding.getRoot();

    }

    /**
     * Get data of the TrueFalse game from database
     * Store questions and figures of the game that will be used during play
     * All data is then sent to Recycle view adapter which class TrueFalseAdapter
     * @param trueFalseViewModel is the view model instance of this fragment, TrueFalseFragment
     */
    private  void setData(TrueFalseViewModel trueFalseViewModel){
        trueFalseViewModel.getGameMaterial().observe(getViewLifecycleOwner(), new Observer<List<TrueFalseGame>>() {
            @Override
            public void onChanged(List<TrueFalseGame> trueFalseGames) {
                 List<String> questions = new ArrayList<>();
                 List<String> pictures =new ArrayList<>();

                for (TrueFalseGame material: trueFalseGames){
                    questions.add(material.getQuestion());
                    pictures.add(material.getFigures());
                }

                try {
                    trueFalseController.setData(questions,pictures);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    /**
     * helper method to set up controller
     * @param binding  FragmentTrueBinding binder
     */
    private  void setUpRecycleView(FragmentTrueBinding binding){
        trueFalseController = new TrueFalseAdapter(requireContext(),R.layout.true_false_adapter);
        binding.listviewIDtrueAdapter.setAdapter(trueFalseController);
        binding.listviewIDtrueAdapter.setLayoutManager(new LinearLayoutManager(requireContext()));


    }


    /**
     * created view for TrueFalseFragment
     * @param view of TrueFalseFragment
     * @param savedInstanceState for  TrueFalseFragment
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.submit.setOnClickListener(new SubmitHandler(inflater,R.id.action_trueFalseFragment_to_play,view));
        ((MainActivity) requireActivity()).backUpPressed(TrueFalseFragment.this,R.id.action_trueFalseFragment_to_play);

    }
    /**
     * handle button event
     * Show grades for user
     */



}