package com.example.wordgame;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wordgame.databinding.FragmentTrueBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrueFalseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrueFalseFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int pos;

    //Ini
    @SuppressLint("StaticFieldLeak")
    static  TextView question ;


    private final String[] questions = {

            "The animal name on the picture is  Inkomo?",
            "The animal name on the picture is Igusha?",
            "The animal name on the picture is Ikati?",
            "The animal name on the picture is Ibhokhwe?"
    };

    private final int[] figure = {
            R.drawable.igusha, R.drawable.igusha,
            R.drawable.igusha, R.drawable.igusha
    };


    private FragmentTrueBinding binding;
    private LayoutInflater inflater;

    public TrueFalseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrueFalseFragment newInstance(String param1, String param2) {
        TrueFalseFragment fragment = new TrueFalseFragment();
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
     * creates TrueFalseFragment
     * @param inflater of TrueFalseFragment
     * @param container of TrueFalseFragment
     * @param savedInstanceState for TrueFalseFragment
     * @return
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTrueBinding.inflate(inflater, container, false);
        this.inflater = inflater;
        setUpRecycleView(binding);
        ((MainActivity)requireActivity()).setID(R.id.action_trueFalseFragment_to_play);
        ((MainActivity)requireActivity()).setView(binding.getRoot());
        return  binding.getRoot();

    }

    /**
     * helper method to set up controller
     * @param binding  FragmentTrueBinding binder
     */
    private  void setUpRecycleView(FragmentTrueBinding binding){
        TrueFalseController trueFalseController = new TrueFalseController(requireContext(),questions,figure,R.layout.true_false_adapter);
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
        handleButton();
        ((MainActivity) requireActivity()).backUpPressed(TrueFalseFragment.this,R.id.action_trueFalseFragment_to_play);

    }
    /**
     * handle button event
     * Show grades for user
     */
    private  void handleButton() {

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit submit = (Submit) new SubmitHandler();
                submit.onSubmit(binding.getRoot(),inflater);

            }
        });
    }


}