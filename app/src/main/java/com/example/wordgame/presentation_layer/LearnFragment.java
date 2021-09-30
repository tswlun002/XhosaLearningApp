package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentLearnBinding;
import com.example.wordgame.model_layer.Learn;
import com.example.wordgame.model_layer.LearnViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearnFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearnFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private LearnViewModel learnViewModel;
    private  LearnAdapter learn;
    private final HashMap<String, List<String> >  data = new HashMap<>();
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
     *  creates Fragment LearnFragment
     * @param savedInstanceState Fragment LearnFragment
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        learnViewModel =  new ViewModelProvider(this).get(LearnViewModel.class);


    }

    /**
     * Get search menu item
     * Set up search view for item search
     * Handle event for search view
     * Allow to search through content any word
     * Filtering occur on query text change
     * @param menu  of learn fragment
     * @param inflater of the menu
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView1 = (SearchView) item.getActionView();
        searchView1.setQueryHint("Search");
        searchView1.requestFocusFromTouch();
        searchView1.setIconified(false);
        searchView1.clearFocus();
        searchView1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query == null || query.length() == 0){
                    filter("");
                }
                else
                    filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText == null || newText.length() == 0){
                    filter("");
                 }
                else
                    filter(newText);
                return true;
            }


        });
        menu.findItem(R.id.search).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });
    }

    void filter (String query){
        learn.getFilter().filter(query);
    }

    /**
     * creates view of the fragment LearnFragment
     * @param inflater inflates Fragment LearnFragment
     * @param container container for   Fragment LearnFragment
     * @param savedInstanceState state instance for Fragment LearnFragment
     * @return view of Fragemnt LearnFragment
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLearnBinding.inflate(inflater, container, false);
        setUpListView(binding);
        getAllLearningMaterial(learnViewModel);
        return binding.getRoot();


    }

    /**
     * get all learning material
     * @param learnViewModel holder of the learning
     */
    private  void getAllLearningMaterial(LearnViewModel learnViewModel){
        learnViewModel.getAllMaterial().observe(getViewLifecycleOwner(), new Observer<List<Learn>>() {
            @Override
            public void onChanged(List<Learn> learningMaterial) {

                String key="";
                for(Learn material:learningMaterial) {
                    List<String> content = new ArrayList<>();
                    key = material.getSection();
                    content.add(material.getContent());

                    if(data.containsKey(key)){
                        content.addAll(Objects.requireNonNull(data.get(key)));
                        content=content.stream().distinct().collect(Collectors.toList());
                        data.put(key, content);
                    }
                    else {
                        content=content.stream().distinct().collect(Collectors.toList());
                        data.put(key, content);
                    }

                }
                for( String key1 : data.keySet()) {
                    learn.setData(data);
                 }


            }

        });


    }
    /**
     * set up List view with data content
     * @param binding  FragmentLearnBinding
     */
    @SuppressLint({"UseCompatLoadingForDrawables", "NotifyDataSetChanged"})
    private  void setUpListView(FragmentLearnBinding binding){


        learn = new LearnAdapter(requireContext(), R.layout.learn_adapter);
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