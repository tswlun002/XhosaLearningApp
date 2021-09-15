package com.example.wordgame.presentation_layer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentStartBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class FragmentStart extends Fragment {

    /**
     * @serialField  binding is a data binder for FragmentStart
     */
    private FragmentStartBinding binding;
    private PlayFragment playObje;
    /**
     * create frament start
     * @param savedInstanceState for FragmentStart
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    /**
     * create frament start
     * @param inflater of  fragment start
     * @param container  of fragment start
     * @param savedInstanceState for start fragment
     * @return view fragmentStart
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentStartBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    /**
     * set up tab layout which help of LearnAdapter class
     * handle events of the tab layout
     * @param view start fragment view
     * @param savedInstanceState for start fragment
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabAdapter tabAdapter = new TabAdapter(requireActivity());

        ViewPager2 viewPager2 = view.findViewById(R.id.viewpager);
        TabLayout tabLayout = view.findViewById(R.id.tabs);

        viewPager2.setAdapter(tabAdapter);


        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                String tabTitle = "";
                if (position == 0) {
                    tabTitle = "Learn";
                } else if (position == 1) {
                    tabTitle = "Play";
                }
                tab.setText(tabTitle);
            }
        }).attach();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager2.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        ((MainActivity) requireActivity()).backUpPressed(FragmentStart.this,R.id.action_SecondFragment_to_FirstFragment);


    }

    /**
     * find help item and set to available if spaces is enough
     * set search view always visible for search purpose
     * Handle search view events
     * @param menu drop down menu in toolbar
     * @param inflater second fragment inflater
     */

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem help = menu.findItem(R.id.help);
        help.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        MenuItem searchItem = menu.findItem(R.id.search);
        searchItem.setVisible(true);
        searchItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        SearchView searchView1 = (SearchView) searchItem.getActionView();
        searchView1.setQueryHint("Search");
        searchView1.requestFocusFromTouch();
        searchView1.setIconified(false);
        searchView1.clearFocus();
        searchItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return true;
            }
        });

    }

    /**
     * set bindind null on Destroy of this fragment
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;


    }


}