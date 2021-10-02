package com.example.wordgame.presentation_layer;

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

import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentTranslationBinding;
import com.example.wordgame.model_layer.MatchingViewModel;
import com.example.wordgame.model_layer.TranslationGame;
import com.example.wordgame.model_layer.TranslationViewModel;

import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TranslationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TranslationFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnSubmit submit;
    public TranslationFragment() {
        // Required empty public constructor
    }
    TranslationViewModel translationViewModel;
    TranslationAdapter translationController;
    HashMap<String, String> data  = new HashMap<>();
    private  FragmentTranslationBinding binding;
    private  LayoutInflater inflater;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TranslationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TranslationFragment newInstance(String param1, String param2) {
        TranslationFragment fragment = new TranslationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        translationViewModel = new ViewModelProvider(this).get(TranslationViewModel.class);
    }



    /**
     * create view of  Tralation fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding= FragmentTranslationBinding.inflate(inflater, container, false);
        this.inflater =inflater;
        setUpListView(binding);
        setData(translationViewModel);
        return binding.getRoot();
    }

    void setData(TranslationViewModel translationViewModel){
        translationViewModel.getGameMaterial().observe(getViewLifecycleOwner(), new Observer<List<TranslationGame>>() {
            @Override
            public void onChanged(List<TranslationGame> translationGames) {
                for(TranslationGame translationGame: translationGames){
                    data.put(translationGame.getQuestion(),translationGame.getHints());
                }
                Toast.makeText(requireContext(), data.size()+"",Toast.LENGTH_SHORT).show();
                translationController.setData(data);
            }


        });
    }
    /**
     * created view of translation fragment
     * @param view
     * @param savedInstanceState
     */

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.submitButton.setOnClickListener(new SubmitHandler(inflater,R.id.action_translationFragment_to_play,binding.getRoot()));
        ((MainActivity) requireActivity()).backUpPressed(TranslationFragment.this,R.id.action_translationFragment_to_play);
    }

    /**
     * set up List view with data content
     * @param binding  FragmentTranslationBinding
     */
    private  void setUpListView(FragmentTranslationBinding binding){

        translationController = new TranslationAdapter(requireContext(), R.layout.translation_adapter);
        binding.tranlationListviewID.setAdapter(translationController);
        binding.tranlationListviewID.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

}