package com.example.wordgame.presentation_layer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentTranslationBinding;
import com.example.wordgame.model_layer.LevelResults;
import com.example.wordgame.model_layer.MatchingViewModel;
import com.example.wordgame.model_layer.TranslationGame;
import com.example.wordgame.model_layer.TranslationViewModel;
import com.example.wordgame.model_layer.User;
import com.example.wordgame.model_layer.WordGameViewModel;

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
    private WordGameViewModel wordGameViewModel;
    private final  User user = MainActivity.user;


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
                translationController.setData(data,translationGames);
            }
        });
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
                //Toast.makeText(requireContext(), userAnswer+"\n\n"+gameAnswer.size(),
                 //       Toast.LENGTH_LONG).show();
               OnSubmit onSubmit = new SubmitHandler(inflater,id,view,userAnswer,gameAnswer);
                onSubmit.onSubmit(view,inflater);
                shareData(onSubmit,onExtractResults);
                Navigation.findNavController(view).
                        navigate(R.id.action_translationFragment_to_results_CurrentActivity);

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
            Toast.makeText(requireContext(),"Error Translation\n "+e.toString(),Toast.LENGTH_SHORT).show();
        }

        LevelResults levelResults = new LevelResults(
                gameId,userId,level,"translation",
                score,totalMarks
        );
        wordGameViewModel.setValue(levelResults);
    }


}