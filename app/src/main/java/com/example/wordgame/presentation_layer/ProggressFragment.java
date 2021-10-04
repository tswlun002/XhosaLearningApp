package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wordgame.R;
import com.example.wordgame.databinding.FragmentProggressBinding;
import com.example.wordgame.databinding.FragmentTranslationBinding;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProggressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProggressFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentProggressBinding binding;
    HashMap<Integer,Integer> actions = new HashMap<>();

    public ProggressFragment() {
        // Required empty public constructor
    }

    void intitActions(){
        //actions.put(0+";"+1,)
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
        binding = FragmentProggressBinding.inflate(inflater, container, false);

        //TextView textView =view.findViewById(R.id.learnnextID);

       /* textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_proggress_to_SecondFragment);
            }
        });*/
        Toolbar toolbar = requireActivity().findViewById(R.id.toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),
                binding.drawer,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer
        );
        binding.drawer.addDrawerListener(actionBarDrawerToggle);

        return binding.getRoot();
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

    @Override
    public void onResume() {
        super.onResume();
        final boolean[] open = {false};
        final boolean[] close = {true};
        final boolean[] averageGrade ={true};
        setDrawer(open,close);
        handleDrawer(averageGrade,open,close);
        handleNavigationDrawerItems(binding.drawer, averageGrade);
    }
    /**
     * helper method to set drawer in appbar
     * Enable DisplayHomeAsUpEnabled so that drawer icon can be visible
     *@param open  true if drawer open else false
     *@param close true if drawer is closed else false
     */
    private  void setDrawer(boolean [] open, boolean[] close){

        final Toolbar toolbar =  requireActivity().findViewById(R.id.toolbar);

        Objects.requireNonNull(((AppCompatActivity) requireActivity())
                .getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!open[0] & close[0]) {
                    binding.drawer.openDrawer(GravityCompat.START);
                }else{
                    binding.drawer.closeDrawer(GravityCompat.START);
                }

            }
        });
    }
    /**
     * Helper method , has inner method to handle drawer
     * @param average  true fragment searchLost_person is open else false
     * @param open  true if drawer open else false
     * @param close true if drawer is closed else false
     */
    private  void  handleDrawer(boolean[] average, boolean []open, boolean[] close){
        binding.drawer.addDrawerListener(new DrawerLayout.DrawerListener() {

            /**
             * Called when a drawer's position changes.
             * @param drawerView is a drawer views
             * @param slideOffset amount or position where has slide up to
             */
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            }


            /**
             * drawerLayout.closeDrawer(GravityCompat.START);
             * Called when a drawer has settled in a completely open state.
             * The drawer is interactive at this point.
             * If you have 2 drawers (left and right) you can distinguish
             * them by using id of the drawerView. int id = drawerView.getId();
             *  id will be your layout's id: for example R.id.left_drawer
             * @param drawerView is a drawer views
             */

            @SuppressLint({"RestrictedApi", "ResourceAsColor"})
            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                open[0] =true;
                close[0] =false;
                NavigationMenuItemView levelone = drawerView.findViewById(R.id.levelOneResults);
                NavigationMenuItemView leveltwo = drawerView.findViewById(R.id.levelTwoResults);
                NavigationMenuItemView levelthree = drawerView.findViewById(R.id.levelThreeResults);
                if(average[0]) {
                    levelone.setChecked(true);
                    levelone.setTextColor(ColorStateList.valueOf(R.color.purple_700));
                }
                else {
                    leveltwo.setChecked(true);
                    leveltwo.setTextColor(ColorStateList.valueOf(R.color.purple_700));
                }
            }

            /**
             * Called when a drawer has settled in a completely closed state.
             * when drawer closes , average item are unchecked
             * set close true and set open false
             * @param drawerView  is a drawer views
             */

            @SuppressLint("RestrictedApi")
            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                NavigationMenuItemView levelone = drawerView.findViewById(R.id.action_averageResults2_to_levelOneResults3);
                NavigationMenuItemView leveltwo = drawerView.findViewById(R.id.action_averageResults2_to_levelTwoResults3);
                NavigationMenuItemView levelthree = drawerView.findViewById(R.id.action_averageResults2_to_levelThreeResults4);

               /* levelone.setChecked(false);
                levelone.setTextColor(ColorStateList.valueOf(Color.BLACK));
                leveltwo.setChecked(false);
                leveltwo.setTextColor(ColorStateList.valueOf(Color.BLACK));
                levelthree.setChecked(false);
                levelthree.setTextColor(ColorStateList.valueOf(Color.BLACK));*/

                close[0] =true;
                open[0] =false;
                //
            }

            /**
             * Called when the drawer motion state changes. The new state will be one of STATE_IDLE, STATE_DRAGGING or STATE_SETTLING.
             * @param newState of the drawer
             */
            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
    }

    /**
     * Handle drawer item selection or clicks
     * When item is selected , navigate to its destination fragment
     * @param drawerLayout that contain items
     * @param average  true fragment searchLost_person is open else false
     */
    private  void handleNavigationDrawerItems(DrawerLayout drawerLayout,boolean[] average){

                binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            /**
             * Helper method to handle selected item in the drawer
             * selected item is setChecked(true)
             * When item add lost Person, navigate to addLostPeople frament
             * if item search Lost person is selected , navigate to searchLost_person fragment
             * @param item to be selected in the drawer
             * @return true ,to indicate item is selected
             */
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);

                average[0]=true;
                return true;
            }
        });
    }
}