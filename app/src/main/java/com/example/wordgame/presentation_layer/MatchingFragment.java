package com.example.wordgame.presentation_layer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentMatchingBinding;
import com.example.wordgame.model_layer.LearnViewModel;
import com.example.wordgame.model_layer.LevelResults;
import com.example.wordgame.model_layer.Matching;
import com.example.wordgame.model_layer.MatchingViewModel;
import com.example.wordgame.model_layer.MultipleChoice;
import com.example.wordgame.model_layer.User;
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
    }

    /**
     * overloaded creates which binds data and inflate view
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentMatchingBinding.inflate(inflater, container, false);
        this.inflater =inflater;
        MatchingViewHandler matchingViewHandler = new MatchingViewHandler(binding.getRoot());
        try {
            onMatchingViewHandler =matchingViewHandler;
            onExtractResults =matchingViewHandler;

        }catch (ClassCastException  e){
            Toast.makeText(requireContext(), "Can't cast matchingViewHandler to  onMatchingViewHandler",
                    Toast.LENGTH_SHORT).show();;
        }
        setData(matchingViewHandler,matchingViewModel);

        return binding.getRoot();

    }

    private void setData(MatchingViewHandler matchingViewHandler,MatchingViewModel matchingViewModel){
        matchingViewModel.getGameMaterial().observe(getViewLifecycleOwner(), new Observer<List<Matching>>() {
            @Override
            public void onChanged(List<Matching> matchings) {
                int numberOfQuestions  =8;
                List<Matching> newList = randomiseData(matchings,numberOfQuestions);
                matchingViewHandler.setData(newList);
            }
        });
    }

    /**
     * Randomise questions for game
     * @param matchingList list of game material of matching game
     * @param numberQuestions is the number of questions for game
     * @return new list of questions of matching game
     */
    private  List<Matching> randomiseData(List<Matching> matchingList, int numberQuestions){
        Random rand = new Random();
        List<Matching> newList = new ArrayList<>();
        for (int i = 0; i < numberQuestions; i++) {

            int randomIndex = rand.nextInt(matchingList.size());
            newList.add(matchingList.get(randomIndex));
            matchingList.remove(randomIndex);
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

    private void handClick(View view){
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id  =R.id.action_results_CurrentActivity_to_matchingFragment;
                HashMap<String ,String> userAnswer = onExtractResults.getUserAnswers();
                HashMap<String ,String> gameAnswer = onExtractResults.getGameAnswers();
                Toast.makeText(requireContext(), userAnswer.size()+"",Toast.LENGTH_SHORT).show();
                OnSubmit onSubmit =new SubmitHandler(inflater,id,binding.getRoot(), userAnswer,gameAnswer);
                onSubmit.onSubmit(v,inflater);
                new LevelResultsHandler(requireContext(),MainActivity.userViewModel,wordGameViewModel,
                        getViewLifecycleOwner()).shareData(onSubmit,onExtractResults,userAnswer,"matching");
                Navigation.findNavController(view).
                        navigate(R.id.action_matchingFragment_to_results_CurrentActivity);


            }
        });
    }
    private void handleFab(){
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MatchingFragment.this).
                        navigate(R.id.action_matchingFragment_to_proggress);

            }
        });
    }

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