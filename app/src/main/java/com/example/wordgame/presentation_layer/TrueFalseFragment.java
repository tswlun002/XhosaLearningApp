package com.example.wordgame.presentation_layer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentTrueBinding;
import com.example.wordgame.model_layer.LevelResults;
import com.example.wordgame.model_layer.TrueFalseGame;
import com.example.wordgame.model_layer.TrueFalseViewModel;
import com.example.wordgame.model_layer.User;
import com.example.wordgame.model_layer.UserViewModel;
import com.example.wordgame.model_layer.WordGameViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrueFalseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrueFalseFragment extends Fragment  {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public  TrueFalseViewModel trueFalseViewModel;
    public   TrueFalseAdapter trueFalseController;
    private FragmentTrueBinding binding;
    private LayoutInflater inflater;
    private WordGameViewModel wordGameViewModel;
    private  OnExtractResults onExtractResults;
    private final UserViewModel userViewModel = MainActivity.userViewModel;
    private  OnSubmit onSubmit;



    public TrueFalseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    public static TrueFalseFragment newInstance(String param1, String param2) {
        TrueFalseFragment fragment = new TrueFalseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * create fragment from given  savedInstanceState
     * @param savedInstanceState bundle to save state of the fragment
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trueFalseViewModel = new ViewModelProvider(this).get(TrueFalseViewModel.class);
        wordGameViewModel = new ViewModelProvider(requireActivity()).get(WordGameViewModel.class);
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTrueBinding.inflate(inflater, container, false);
        this.inflater = inflater;
        setUpRecycleView(binding);
        initExtract();
       getUserInformation();

        return  binding.getRoot();

    }

    /**
     * initial  the extract interface
     */
    private void initExtract(){
        try{
         onExtractResults = trueFalseController;
        }catch (Exception e){
            Toast.makeText(requireContext(), "cast err on trueFalse", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Get user information such as level
     * Use the user level to set data for that level
     */
    private void getUserInformation(){
        wordGameViewModel.getUserLevel().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                setData(trueFalseViewModel,integer);
            }
        });
    }

    /**
     * Get data of the TrueFalse game from database
     * Store questions and figures of the game that will be used during play
     * All data is then sent to Recycle view adapter which class TrueFalseAdapter
     * @param trueFalseViewModel is the view model instance of this fragment, TrueFalseFragment
     */
    private  void setData(TrueFalseViewModel trueFalseViewModel, int level){
        if(level ==1) {
            trueFalseViewModel.getQuestionsLevelOne().observe(getViewLifecycleOwner(), new Observer<List<TrueFalseGame>>() {
                @Override
                public void onChanged(List<TrueFalseGame> trueFalseGames) {
                    setData(trueFalseGames);
                }
            });
        }else if(level==2){
            trueFalseViewModel.getQuestionsLevelTwo().observe(getViewLifecycleOwner(), new Observer<List<TrueFalseGame>>() {
                @Override
                public void onChanged(List<TrueFalseGame> trueFalseGames) {
                    setData(trueFalseGames);
                }
            });
        }
        else if(level ==3){
            trueFalseViewModel.getQuestionsLevelThree().observe(getViewLifecycleOwner(), new Observer<List<TrueFalseGame>>() {
                @Override
                public void onChanged(List<TrueFalseGame> trueFalseGames) {
                    setData(trueFalseGames);
                }
            });
        }


    }

    private void  setData(List<TrueFalseGame> trueFalseGames){
        List<String> questions = new ArrayList<>();
        List<String> pictures =new ArrayList<>();
        int numberOfQuestions =5;
        List<TrueFalseGame> trueFalseGames1 = randomiseData(trueFalseGames, numberOfQuestions);
        for (TrueFalseGame material: trueFalseGames1){
            questions.add(material.getQuestion());
            pictures.add(material.getFigures());
        }

        try {
            trueFalseController.setData(questions,pictures,trueFalseGames1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Randomise questions for game
     * @param trueFalseGames list of game material of true false game
     * @param numberQuestions is the number of questions for game
     * @return new list of questions of true false game
     */
    private  List<TrueFalseGame> randomiseData(List<TrueFalseGame> trueFalseGames,int numberQuestions){
        Random rand = new Random();
        List<TrueFalseGame> newList = new ArrayList<>();
        for (int i = 0; i < numberQuestions; i++) {

            int randomIndex = rand.nextInt(trueFalseGames.size());
            newList.add(trueFalseGames.get(randomIndex));
            trueFalseGames.remove(randomIndex);
        }

        return newList;
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
        handleSubmit(view);
        handleFab();
        ((MainActivity) requireActivity()).backUpPressed(TrueFalseFragment.this,R.id.action_trueFalseFragment_to_play);

    }

    private void handleSubmit(View view){
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id  = R.id.action_results_CurrentActivity_to_trueFalseFragment;
                HashMap<String,String> userAnswer =onExtractResults.getUserAnswers();
                HashMap<String,String>gameAnswer  = onExtractResults.getGameAnswers();
                onSubmit = new SubmitHandler(inflater,id,view,userAnswer,gameAnswer);
                onSubmit.onSubmit(view,inflater);
                Navigation.findNavController(view).
                        navigate(R.id.action_trueFalseFragment_to_results_CurrentActivity);
                new LevelResultsHandler(requireContext(),userViewModel,wordGameViewModel,
                        getViewLifecycleOwner()).shareData(onSubmit,onExtractResults,userAnswer,"true false");

            }
        });
    }

    private void handleFab(){
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(TrueFalseFragment.this).
                        navigate(R.id.action_trueFalseFragment_to_proggress);
                
            }
        });
    }





}