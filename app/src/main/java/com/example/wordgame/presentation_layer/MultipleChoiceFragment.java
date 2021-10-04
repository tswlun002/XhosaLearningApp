package com.example.wordgame.presentation_layer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentMultipleChoiceBinding;
import com.example.wordgame.model_layer.LevelResults;
import com.example.wordgame.model_layer.MultipleChoice;
import com.example.wordgame.model_layer.MultipleChoiceViewModel;
import com.example.wordgame.model_layer.User;
import com.example.wordgame.model_layer.WordGameViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MultipleChoiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MultipleChoiceFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MultipleChoiceViewModel multipleChoiceViewModel;
    MultipleChoiceAdapter multipleChoiceController;
    HashMap<String,List<String>> data= new HashMap<>();
    private FragmentMultipleChoiceBinding binding ;
    private LayoutInflater inflater;
    private SubmitHandler submit;
    private final User user = MainActivity.user;
    private OnExtractResults onExtractResults;
    private WordGameViewModel wordGameViewModel;
    public MultipleChoiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MultipleChoiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MultipleChoiceFragment newInstance(String param1, String param2) {
        MultipleChoiceFragment fragment = new MultipleChoiceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       multipleChoiceViewModel = new ViewModelProvider(this).get(MultipleChoiceViewModel.class);
       wordGameViewModel = new ViewModelProvider(requireActivity()).get(WordGameViewModel.class);

    }

    /**
     * creates view of the fragment
     * @param inflater of multiple choice fragment
     * @param container  of multiple choice fragment
     * @param savedInstanceState of multiple choice fragment
     * @return  multiple choice fragment view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentMultipleChoiceBinding.inflate(inflater, container, false);
        this.inflater =inflater;
        View view =binding.getRoot();
        setUpRecycleView(binding,view);
        onExtractResults =multipleChoiceController;
        setData(multipleChoiceViewModel);
        setRecycleView();
        return  view;
    }

    /**
     * Get data of the multiple choice game from database
     * Store questions and figures of the game that will be used during play
     * All data is then sent to Recycle view adapter
     * @param multipleChoiceViewModel is the view model instance of this fragment
     */
    private  void setData(MultipleChoiceViewModel multipleChoiceViewModel){
        multipleChoiceViewModel.getGameMaterial().observe(getViewLifecycleOwner(), new Observer<List<MultipleChoice>>() {
            @Override
            public void onChanged(List<MultipleChoice> multipleChoiceData) {
                List<String> choices ;
                int size = multipleChoiceData.size();

                for(int i =0; i< size;i++) {
                    choices = new ArrayList<>();
                    MultipleChoice multipleChoice = multipleChoiceData.get(i);
                    String question =multipleChoice.getQuestion();
                    choices.add(multipleChoice.getChoiceOne());
                    choices.add(multipleChoice.getChoiceTwo());
                    choices.add(multipleChoice.getChoiceThree());
                    choices.add(multipleChoice.getChoiceFour());
                    data.put(question,choices);

                }
                multipleChoiceController.setData(data,multipleChoiceData);

            }
        });

    }

    private void setRecycleView(){
         multipleChoiceController.setRecycleView(binding.mulplechoiceListviewId);
    }
    /**
     * set up List view with data content
     * @param binding  FragmentTranslationBinding
     */
    private  void setUpRecycleView(FragmentMultipleChoiceBinding binding, View view){
        multipleChoiceController = new MultipleChoiceAdapter(requireContext(),   R.layout.multiple_choice_adapter);
        binding.mulplechoiceListviewId.setAdapter(multipleChoiceController);
        binding.mulplechoiceListviewId.setLayoutManager(new LinearLayoutManager(requireContext()));
        RecyclerView recyclerView = view.findViewById(R.id.mulplechoiceListviewId);
    }

    /**
     * created view of this fragment
     * set listview here
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //binding.submit.setOnClickListener(new SubmitHandler(inflater,R.id.action_multipleChoiceFragment_to_play,binding.getRoot()));
        handleSubmit(view);
        ((MainActivity) requireActivity()).backUpPressed(MultipleChoiceFragment.this,R.id.action_multipleChoiceFragment_to_play);
    }


    /**
     * helper method handle submit button events
     * After submit clicked,
     * Extract user answers and also game correct answers with their respective questions
     * Grade the user answers
     * Navigate to show activity results window
     * And insert user score into database
     * @param view view of this fragment
     */
    private void handleSubmit(View view){
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id  = R.id.action_results_CurrentActivity_to_multipleChoiceFragment;
                HashMap<String,String> userAnswer =onExtractResults.getUserAnswers();
                HashMap<String,String>gameAnswer  = onExtractResults.getGameAnswers();
                //Toast.makeText(requireContext(), userAnswer+" | "+gameAnswer,Toast.LENGTH_SHORT).show();
                submit = new SubmitHandler(inflater, id, view, userAnswer, gameAnswer);
                OnSubmit onSubmit =submit;
                onSubmit.onSubmit(view,inflater);
                shareData(onSubmit,onExtractResults, userAnswer );
                Navigation.findNavController(view).
                        navigate(R.id.action_multipleChoiceFragment_to_results_CurrentActivity);

            }
        });
    }

    /**
     * set user results into level results
     * @param submit submits the user score
     * @param onExtractResults  extract user answers and game answers
     */
    private void  shareData(OnSubmit submit,OnExtractResults onExtractResults,HashMap<String,String>gameAnswer ){
        double score = submit.getScore();
        int userId = user.getUserId();
        String[] information  = onExtractResults.getGameInformation().split(",");
        int gameId=0;int level=0; int totalMarks=0;
        try {
            gameId= Integer.parseInt(information[0].trim());
            level= Integer.parseInt(information[1].trim());
            totalMarks= gameAnswer.size() /*Integer.parseInt(information[2].trim())*/;
        }catch (Exception e){
            Toast.makeText(requireContext(),"Error Matching\n "+e.toString(),Toast.LENGTH_SHORT).show();
        }

        LevelResults levelResults = new LevelResults(
                gameId,userId,level,"multiple choice",
                score,totalMarks
        );
        wordGameViewModel.setValue(levelResults);
    }


    /**
     * set binding to nul on destroy
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }









}

