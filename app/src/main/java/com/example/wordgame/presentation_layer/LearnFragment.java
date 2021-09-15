package com.example.wordgame.presentation_layer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentLearnBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearnFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearnFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private final String[] headings = {
            "Numbers/Izibalo",
            "Vowels/Izikhamiso",
            "Consonants/Amaqabane",
            "Clicks/Izandi"
    };

    private final int[] lesson = {
            R.drawable.numbers, R.drawable.vowels,
            R.drawable.consonants, R.drawable.clicks
    };


     private FragmentLearnBinding  binding;

    /**
     * Empty constructor
     */
    public LearnFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LearnFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LearnFragment newInstance(String param1, String param2) {
        LearnFragment fragment = new LearnFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     *  creates Fragemnt LearnFragment
     * @param savedInstanceState Fragemnt LearnFragment
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
     * creates view of the frament LearnFragment
     * @param inflater inflates Fragemnt LearnFragment
     * @param container container for   Fragemnt LearnFragment
     * @param savedInstanceState state instance for Fragemnt LearnFragment
     * @return view of Fragemnt LearnFragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLearnBinding.inflate(inflater, container, false);
        setUpListView(binding);
        return binding.getRoot();


    }

    /**
     * set up List view with data content
     * @param binding  FragmentLearnBinding
     */
    private  void setUpListView(FragmentLearnBinding binding){

        LearnAdapter learn = new LearnAdapter(requireContext(), headings, lesson, R.layout.learn_adapter);
        binding.learnIDListview.setAdapter(learn);
        binding.learnIDListview.setLayoutManager(new LinearLayoutManager(requireContext()));
    }


    /**
     * fragment LearnFragment resume
     * set bottom navigation Invisible/gone when fragment resumes
     */
    @Override
    public void onResume() {
        super.onResume();
        setVisibilityBottomNavigation(View.GONE);

    }

    /**
     * set visibility of bottom navigation
     * @param visible visibility value
     */
    private  void setVisibilityBottomNavigation(int visible){
        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigationID);
        bottomNavigationView.setVisibility(visible);
    }

}