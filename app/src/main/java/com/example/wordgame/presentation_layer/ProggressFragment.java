package com.example.wordgame.presentation_layer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wordgame.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProggressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProggressFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProggressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProggressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProggressFragment newInstance(String param1, String param2) {
        ProggressFragment fragment = new ProggressFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * creates progress fragment
     * @param savedInstanceState for progress fragment
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * inflate view of progress
     * Handle event for textView click
     * @param inflater for progress fragment
     * @param container of progress fragment
     * @param savedInstanceState for progress fragment
     * @return view of progress fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_proggress, container, false);

        TextView textView =view.findViewById(R.id.learnnextID);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_proggress_to_SecondFragment);
            }
        });

        return view;
    }

    /**
     * onCreated view handle back press events
     * @param view Progress view
     * @param savedInstanceState of Progress fragment
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) requireActivity()).backUpPressed(ProggressFragment.this,R.id.action_proggress_to_FirstFragment);

    }
}