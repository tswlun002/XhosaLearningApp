package com.example.wordgame.presentation_layer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentMatchingBinding;

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
    OnMatchingViewHandler onMatchingViewHandler;
    private FragmentMatchingBinding binding;
    private  LayoutInflater inflater;
    //words to generate questions
    private String [] myEngWordsArr = {"dog", "sheep", "goat", "horse"};
    private String [] xhosaTransWordArr =  {
            "inja", "igusha", "ibhokhwe",
            "ihashe","icephe","isitya",
            "injana","amanzi"
    };

    //ids of textViews and edit texts
    private final int [] idsArrayEngTVs = {R.id.engTextView1, R.id.engTextView2,R.id.engTextView3, R.id.engTextView4};
    private int [] idsArrayXhosaTVs = {R.id.xhosaMatchTextView1, R.id.xhosaMatchTextView2,R.id.xhosaMatchTextView3, R.id.xhosaMatchTextView4};
    private final int [] idsArrayEditText = {R.id.xhosaEditText1, R.id.xhosaEditText2,R.id.xhosaEditText3, R.id.xhosaEditText4};


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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * overloaded creates which binds data and inflate view
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMatchingBinding.inflate(inflater, container, false);
        this.inflater =inflater;
        ((MainActivity)requireActivity()).setID(R.id.action_matchingFragment_to_play);
        ((MainActivity)requireActivity()).setView(binding.getRoot());
        return binding.getRoot();
    }

    /**
     * created fragment
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.button.setOnClickListener(new SubmitHandler(inflater,R.id.action_matchingFragment_to_play,binding.getRoot()));
        ((MainActivity) requireActivity()).backUpPressed(MatchingFragment.this,R.id.action_matchingFragment_to_play);
        handleDragDrop();
    }

    void handleDragDrop(){
        onMatchingViewHandler = new MatchingViewHandler();
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