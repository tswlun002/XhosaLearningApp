package com.example.wordgame.presentation_layer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentMatchingBinding;
import com.example.wordgame.model_layer.Matching;
import com.example.wordgame.model_layer.MatchingViewModel;
import com.example.wordgame.model_layer.WordGameViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MatchingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchingFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private  OnMatchingViewHandler onMatchingViewHandler;
    private OnExtractResults onExtractResults;
    private FragmentMatchingBinding binding;
    private  LayoutInflater inflater;
    private MatchingViewModel matchingViewModel;
    private WordGameViewModel wordGameViewModel;
    private  MatchingViewHandler matchingViewHandler;
    //private final User user = MainActivity.user;


    public MatchingFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MatchingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchingFragment newInstance(String param1, String param2) {
        MatchingFragment fragment = new MatchingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        matchingViewModel = new ViewModelProvider(this).get(MatchingViewModel.class);
        wordGameViewModel = new ViewModelProvider(requireActivity()).get(WordGameViewModel.class);
    }

    /**
     * overloaded creates which binds data and inflate view
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentMatchingBinding.inflate(inflater, container, false);
        this.inflater =inflater;
        matchingViewHandler = new MatchingViewHandler(binding.getRoot());
        try {
            onMatchingViewHandler =matchingViewHandler;
            onExtractResults =matchingViewHandler;

        }catch (ClassCastException  e){
            Toast.makeText(requireContext(), "Can't cast matchingViewHandler to  onMatchingViewHandler",
                    Toast.LENGTH_SHORT).show();;
        }
        //setData(matchingViewHandler,matchingViewModel,level);
        getUserInformation();

        return binding.getRoot();

    }

    /**
     * get user id for matching3.txt and setting it to access each level.
     * user that id to store the average grades for the current level
     */
    private void getUserInformation(){
        wordGameViewModel.getUserLevel().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                setData(matchingViewModel,matchingViewHandler,integer);
            }
        });
    }
    private void setData(MatchingViewModel matchingViewModel,MatchingViewHandler matchingViewHandler,int level){
        if(level==1) {
            matchingViewModel.getQuestionsLevelOne().observe(getViewLifecycleOwner(), new Observer<List<Matching>>() {
                @Override
                public void onChanged(List<Matching> matching) {
                        setData(matching,matchingViewHandler);
                }
            });
        }else if(level ==2) {
            matchingViewModel.getQuestionsLevelTwo().observe(getViewLifecycleOwner(), new Observer<List<Matching>>() {
                @Override
                public void onChanged(List<Matching> matching) {
                    setData(matching,matchingViewHandler);
                }
            });
        }else if(level ==3){
            matchingViewModel.getQuestionsLevelThree().observe(getViewLifecycleOwner(), new Observer<List<Matching>>() {
                @Override
                public void onChanged(List<Matching> matching) {
                        setData(matching,matchingViewHandler);
                }
            });
        }
    }

    /**
     * sets the data then randomise it before access for use
     * @param matching gets data from the database
     */
    private void setData(List<Matching> matching,MatchingViewHandler matchingViewHandler){
       if(matching.size()>0) {
            int numberOfQuestions = 8;
            List<Matching> newList = randomiseData(matching, numberOfQuestions);
            matchingViewHandler.setData(newList);
        }
    }
    /**
     * Randomise questions for game
     * @param matchingList list of game material of matching3.txt game
     * @param numberQuestions is the number of questions for game
     * @return new list of questions of matching3.txt game
     */
    private  List<Matching> randomiseData(List<Matching> matchingList, int numberQuestions){
        Random rand = new Random();
        List<Matching> newList = new ArrayList<>();
        for (int i = 0; i < numberQuestions; i++) {
            if(matchingList.size()>0) {
                int randomIndex = rand.nextInt(matchingList.size());
                newList.add(matchingList.get(randomIndex));
                matchingList.remove(randomIndex);
            }
        }

        return newList;
    }


    /**
     * created fragment
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        wordGameViewModel = new ViewModelProvider(requireActivity()).get(WordGameViewModel.class);
        handClick(view);
        handleFab();
        ((MainActivity) requireActivity()).backUpPressed(MatchingFragment.this,R.id.action_matchingFragment_to_play);
        handleDragDrop();
    }

    /**
     *  handles submit button click
     *  Then submit user results  using class SubmitHandler
     *  Navigate to level results fragment to show user results
     * @param view of this fragment
     */
    private void handClick(View view){
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id  =R.id.action_results_CurrentActivity_to_matchingFragment;
                HashMap<String ,String> userAnswer = onExtractResults.getUserAnswers();
                HashMap<String ,String> gameAnswer = onExtractResults.getGameAnswers();
                OnSubmit onSubmit =new SubmitHandler(inflater,id,binding.getRoot(), userAnswer,gameAnswer);
                onSubmit.onSubmit(v,inflater);
                new LevelResultsHandler(requireContext(),MainActivity.userViewModel,wordGameViewModel,
                        getViewLifecycleOwner()).shareData(onSubmit,onExtractResults,userAnswer, "matching");
                Navigation.findNavController(view).
                        navigate(R.id.action_matchingFragment_to_results_CurrentActivity);


            }
        });
    }

    /**
     * handle fab button click
     * Navigate to progress window to display user results
     */
    private void handleFab(){
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MatchingFragment.this).
                        navigate(R.id.action_matchingFragment_to_proggress);

            }
        });
    }

    /**
     * handle drag and drop of the views
     */
    void handleDragDrop(){
        onMatchingViewHandler.viewClicked(binding.xhosaMatchTextView1);
        onMatchingViewHandler.viewClicked(binding.xhosaMatchTextView2);
        onMatchingViewHandler.viewClicked(binding.xhosaMatchTextView3);
        onMatchingViewHandler.viewClicked(binding.xhosaMatchTextView4);
        onMatchingViewHandler.viewClicked(binding.xhosaMatchTextView5);
        onMatchingViewHandler.viewClicked(binding.xhosaMatchTextView6);
         onMatchingViewHandler.viewClicked(binding.xhosaMatchTextView7);
         onMatchingViewHandler.viewClicked(binding.xhosaMatchTextView8);
         onMatchingViewHandler.viewClicked(binding.xhosaEditText3);
         onMatchingViewHandler.viewClicked(binding.xhosaEditText1);
         onMatchingViewHandler.viewClicked(binding.xhosaEditText2);
         onMatchingViewHandler.viewClicked(binding.xhosaEditText4);
    }
}