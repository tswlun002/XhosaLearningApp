package com.example.wordgame.presentation_layer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentTranslationBinding;

import com.example.wordgame.model_layer.TranslationGame;
import com.example.wordgame.model_layer.TranslationViewModel;

import com.example.wordgame.model_layer.WordGameViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
    private WordGameViewModel wordGameViewModel;
    //private final  User user = MainActivity.user;


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
        wordGameViewModel = new ViewModelProvider(requireActivity()).get(WordGameViewModel.class);
    }



    /**
     * create view of  Translation fragment
     * @param inflater of Translation fragment
     * @param container of Translation fragment
     * @param savedInstanceState of Translation fragment
     * @return  view of Translation fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding= FragmentTranslationBinding.inflate(inflater, container, false);
        this.inflater =inflater;
        setUpListView(binding);
        getUserInformation();


        return binding.getRoot();
    }
    /**
     * Get user information such as level
     * Use the user level to set data for that level
     */
    private void getUserInformation(){
        wordGameViewModel.getUserLevel().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                setData(translationViewModel,integer);
            }
        });
    }
    void setData(TranslationViewModel translationViewModel,int level ){
        if(level ==1) {
            translationViewModel.getQuestionsLevelOne().observe(getViewLifecycleOwner(), new Observer<List<TranslationGame>>() {
                @Override
                public void onChanged(List<TranslationGame> translationGames) {
                   setData(translationGames);
                }
            });
        }else if(level ==2){
            translationViewModel.getQuestionsLevelTwo().observe(getViewLifecycleOwner(), new Observer<List<TranslationGame>>() {
                @Override
                public void onChanged(List<TranslationGame> translationGames) {
                    setData(translationGames);
                }
            });
        }
        else  if(level ==3){
            translationViewModel.getQuestionsLevelThree().observe(getViewLifecycleOwner(), new Observer<List<TranslationGame>>() {
                @Override
                public void onChanged(List<TranslationGame> translationGames) {
                    setData(translationGames);
                }
            });
        }
    }

    private  void setData(List<TranslationGame> translationGames){
        int numberOfQuestions = 5;
        List<TranslationGame> translationGameList = randomiseData(translationGames, numberOfQuestions);
        for (TranslationGame translationGame : translationGameList) {
            data.put(translationGame.getQuestion(), translationGame.getHints());
        }
        translationController.setData(data, translationGameList);
    }
    /**
     * Randomise questions for game
     * @param translationGameList list of game material of translation  game
     * @param numberQuestions is the number of questions for game
     * @return new list of questions of translation game
     */
    private  List<TranslationGame> randomiseData(List<TranslationGame> translationGameList, int numberQuestions){
        Random rand = new Random();
        List<TranslationGame> newList = new ArrayList<>();
        for (int i = 0; i < numberQuestions; i++) {
             if(translationGameList.size()>0) {
                 int randomIndex = rand.nextInt(translationGameList.size());
                 newList.add(translationGameList.get(randomIndex));
                 translationGameList.remove(randomIndex);
             }
        }

        return newList;
    }
    /**
     * created view of translation fragment
     * @param view of Translation fragment
     * @param savedInstanceState of Translation fragment
     */

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handleSubmit(view);
        handleFab();
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


    private void handleSubmit(View view){
        OnExtractResults onExtractResults = translationController;
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id  = R.id.action_results_CurrentActivity_to_translationFragment;
                HashMap<String,String> userAnswer =onExtractResults.getUserAnswers();
                HashMap<String,String>gameAnswer  = onExtractResults.getGameAnswers();
                OnSubmit onSubmit = new SubmitHandler(inflater,id,view,userAnswer,gameAnswer);
                onSubmit.onSubmit(view,inflater);
                new LevelResultsHandler(requireContext(),MainActivity.userViewModel,wordGameViewModel,
                        getViewLifecycleOwner()).shareData(onSubmit,onExtractResults,userAnswer,"translation");
                Navigation.findNavController(view).
                        navigate(R.id.action_translationFragment_to_results_CurrentActivity);

            }
        });
    }

    /**
     * sets a popup to progress view
     */
    private void handleFab(){
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(TranslationFragment.this).
                        navigate(R.id.action_translationFragment_to_proggress);

            }
        });
    }

}