package com.example.wordgame;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.wordgame.databinding.FragmentTrueBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrueFalseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrueFalseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private final String[] headings = {
            "The animal name on the picture is  Inkomo?",
            "The animal name on the picture is Igusha?",
            "The animal name on the picture is Ikati?",
            "The animal name on the picture is Ibhokhwe?"
    };

    private final int[] lesson = {
            R.drawable.igusha, R.drawable.igusha,
            R.drawable.igusha, R.drawable.igusha
    };

    private FragmentTrueBinding binding;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTrueBinding.inflate(inflater, container, false);



        binding.listviewIDtrueAdapter.setStackFromBottom(false);
        binding.listviewIDtrueAdapter.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        PlaController plaController = new PlaController(requireContext(),headings,lesson,R.layout.true_false_adapter);
        binding.listviewIDtrueAdapter.setAdapter(plaController);



        return  binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = view.findViewById(R.id.listviewIDtrueAdapter);

    }
}