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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentTrueBinding;
import com.example.wordgame.model_layer.LevelResults;
import com.example.wordgame.model_layer.TrueFalseGame;
import com.example.wordgame.model_layer.TrueFalseViewModel;
import com.example.wordgame.model_layer.User;
import com.example.wordgame.model_layer.WordGameViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private User user = MainActivity.user;
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
        setData(trueFalseViewModel);

        return  binding.getRoot();

    }

    private void initExtract(){
        try{
         onExtractResults = trueFalseController;
        }catch (Exception e){
            Toast.makeText(requireContext(), "cast err on trueFalse", Toast.LENGTH_SHORT).show();
        }

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
                    trueFalseController.setData(questions,pictures,trueFalseGames);
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
        handleSubmit(view);
        ((MainActivity) requireActivity()).backUpPressed(TrueFalseFragment.this,R.id.action_trueFalseFragment_to_play);

    }

    private void handleSubmit(View view){
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id  = R.id.action_results_CurrentActivity_to_trueFalseFragment;
                HashMap<String,String> userAnswer =onExtractResults.getUserAnswers();
                HashMap<String,String>gameAnswer  = onExtractResults.getGameAnswers();
                Toast.makeText(requireContext(), userAnswer.size()+""+gameAnswer.size(),
                        Toast.LENGTH_SHORT).show();
                onSubmit = new SubmitHandler(inflater,id,view,userAnswer,gameAnswer);
                onSubmit.onSubmit(view,inflater);
                shareData(onSubmit,onExtractResults);
                Navigation.findNavController(view).
                        navigate(R.id.action_trueFalseFragment_to_results_CurrentActivity);

            }
        });
    }

    private void  shareData(OnSubmit submit,OnExtractResults onExtractResults){
        double score = submit.getScore();
        int userId = user.getUserId();
        String[] information  = onExtractResults.getGameInformation().split(",");
        int gameId=0;int level=0; int totalMarks=0;
        try {
            gameId= Integer.parseInt(information[0].trim());
            level= Integer.parseInt(information[1].trim());
            totalMarks= Integer.parseInt(information[2].trim());
        }catch (Exception e){
            Toast.makeText(requireContext(),"Error Matching\n "+e.toString(),Toast.LENGTH_SHORT).show();
        }

        LevelResults levelResults = new LevelResults(
                gameId,userId,level,"true false",
                score,totalMarks
        );
        wordGameViewModel.setValue(levelResults);
    }



}