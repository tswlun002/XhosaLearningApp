package com.example.wordgame;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wordgame.databinding.FragmentMultipleChoiceBinding;
import com.example.wordgame.databinding.FragmentTranslationBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MultipleChoiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MultipleChoiceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private  RecyclerView recyclerView;
    private final String [] questions={
            "home is called ?",
            "chair is called?",
            "food is called?",
            "walking is called?"
    };
    private FragmentMultipleChoiceBinding binding ;
    private LayoutInflater inflater;
    private  int position;
    private  TextView textView;
    private  MultipleChoiceController multipleChoiceController;
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * creates view of the fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding= FragmentMultipleChoiceBinding.inflate(inflater, container, false);
        this.inflater =inflater;
        View view =binding.getRoot();
        setUpListView(binding,view);
        return  view;
    }

    /**
     * set up List view with data content
     * @param binding  FragmentTranslationBinding
     */
    private  void setUpListView(FragmentMultipleChoiceBinding binding,View view){
        MultipleChoiceController multipleChoiceController = new MultipleChoiceController(requireContext(), questions,  R.layout.multiple_choice_adapter);
        binding.mulplechoiceListviewId.setAdapter(multipleChoiceController);
        binding.mulplechoiceListviewId.setLayoutManager(new LinearLayoutManager(requireContext()));
        this.multipleChoiceController=multipleChoiceController;
        RecyclerView recyclerView = view.findViewById(R.id.mulplechoiceListviewId);
        ((MainActivity) requireActivity()).setMultipleChoiceController(multipleChoiceController, recyclerView);


    }

    /**
     * created view of this fragment
     * set listview here
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handleButton();
        ((MainActivity) requireActivity()).backUpPressed(MultipleChoiceFragment.this,R.id.action_multipleChoiceFragment_to_play);
    }


    /**
     * set binding to nul on destroy
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }



    /**
     * handle button event
     * Show grades for user
     */
    private  void handleButton() {

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActiviyResults activiyResults = new ActiviyResults(inflater, requireContext(),R.id.action_multipleChoiceFragment_to_play,binding.getRoot());
                activiyResults.gradesActity(18, 20);
            }
        });
    }




}

