package com.example.wordgame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wordgame.databinding.FragmentMatchingBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MatchingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentMatchingBinding binding;
    private  LayoutInflater inflater;
    public MatchingFragment() {
        // Required empty public constructor
    }

    //words to generate questions
    private String [] myEngWordsArr = {"dog", "sheep", "goat", "horse"};
    private String [] xhosaTransWordArr =  {"inja", "igusha", "ibhokhwe", "ihashe"};

    //ids of textViews
    private int [] idsArrayEngTVs = {R.id.engTextView1, R.id.engTextView2,R.id.engTextView3, R.id.engTextView4};
    private int [] idsArrayXhosaTVs = {R.id.xhosaMatchTextView1, R.id.xhosaMatchTextView2,R.id.xhosaMatchTextView3, R.id.xhosaMatchTextView4};
    private int [] idsArrayEditText = {R.id.xhosaEditText1, R.id.xhosaEditText2,R.id.xhosaEditText3, R.id.xhosaEditText4};

    //populating text views with content
    public void PopulateTextViews(int idEng[], int idXhosa[],String[] eWords,String[] xWords, View view){

        for(int i = 0; i < idEng.length; i++){
            //populating eng TextViews
            TextView textViewEng = (TextView) view.findViewById(idEng[i]);
            textViewEng.setText(eWords[i]);

            //populating xhosa TextViews
            TextView textViewXho = (TextView) view.findViewById(idXhosa[i]);
            textViewXho.setText(xWords[i]);
        }
    }

    public int numMatchingAns(View view){
        //boolean isMatching = false;
        ArrayList<String> userAnswers = new ArrayList<>();
        int correctAnswers = 0;
        EditText myEd;

        for(int i = 0; i< idsArrayEditText.length; i++){
            myEd = (EditText) view.findViewById(idsArrayEditText[i]);
            String text = (String) myEd.getText().toString();
            userAnswers.add(text);
        }

        for (int k = 0; k< idsArrayEditText.length; k++){

            if(userAnswers.get(k).equalsIgnoreCase( xhosaTransWordArr[k])== true){
                correctAnswers++;
            }
        }

        return correctAnswers;
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * overloaded creates which binds data and inflate view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMatchingBinding.inflate(inflater, container, false);
        this.inflater =inflater;
        return binding.getRoot();
    }

    /**
     * created fragment
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PopulateTextViews(idsArrayEngTVs,idsArrayXhosaTVs,myEngWordsArr,xhosaTransWordArr,view);
        int correctAns = numMatchingAns(view);
        handleButton(correctAns);
        ((MainActivity) requireActivity()).backUpPressed(MatchingFragment.this,R.id.action_matchingFragment_to_play);
    }

    /**
     * handle button event
     * Show grades for user
     */
    private  void handleButton(int correctAns) {

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int correctAns = numMatchingAns(v);
                ActiviyResults activiyResults = new ActiviyResults(inflater, requireContext());
                activiyResults.gradesActity(correctAns,idsArrayEditText.length);
            }
        });
    }
}