package com.example.wordgame.presentation_layer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDestination;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.wordgame.R;
import com.example.wordgame.databinding.WelcomwindowBinding;
import com.example.wordgame.model_layer.User;
import com.example.wordgame.model_layer.UserViewModel;
import com.example.wordgame.model_layer.WordGameViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

/**
 * Class to initiate word game.
 * Allow user choose level of PlayFragment and also navigate to see progress of learning
 */

public class WelcomeWindow extends Fragment {

    /**
     * @serialField binding data binding for Welcome fragment
     */
    private WelcomwindowBinding binding;
    private final  UserViewModel userViewModel = MainActivity.userViewModel;
    private List<User> userList;
    private WordGameViewModel wordGameViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        wordGameViewModel = new ViewModelProvider(requireActivity()).get(WordGameViewModel.class);

    }
    /**
     * creates view of Welcome fragment
     * @param inflater to inflate Welcome fragment
     * @param container for Welcome fragment
     * @param savedInstanceState of Welcome fragment
     * @return view of Welcome fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = WelcomwindowBinding.inflate(inflater, container, false);
        getUserDetails();
        return binding.getRoot();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem=menu.findItem(R.id.resultsId);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menuItem.setVisible(false);
    }

    private  void getUserDetails(){
        userViewModel.getGameMaterial().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if(users!=null)
                    userList=users;
            }
        });
    }

    /**
     * @return user current level
     */
    private  int getUserLevel(){
        User user = userList.get(0);
        return user.getCurrentLevel();
    }
    /***
     * share user level with other fragments
     */
    private  void shareUserLevel(){
        wordGameViewModel.setUserLevel(getUserLevel());
    }

    /**
     * handles button events of the created view
     * @param view welcome fragment created view
     * @param savedInstanceState of welcome fragment
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       levelOne();
       levelTwo();
       levelThree();

    }

    /**
     * handle button level one event
     */
    private void levelOne(){

        binding.level1IDBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareUserLevel();
                NavDestination  navDestination =Navigation.findNavController((MainActivity) requireActivity(),
                        R.id.nav_host_fragment_content_main).getCurrentDestination();
                if(navDestination.getId()==R.id.FirstFragment)
                    Navigation.findNavController((MainActivity) requireActivity(),
                        R.id.nav_host_fragment_content_main).
                        navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
    /**
     * handle button level two event
     */
    private void levelTwo(){
        binding.level2IDBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getUserLevel()>=2) {
                    shareUserLevel();
                    NavDestination  navDestination =Navigation.findNavController((MainActivity) requireActivity(),
                            R.id.nav_host_fragment_content_main).getCurrentDestination();
                    if(navDestination.getId()==R.id.FirstFragment)
                    Navigation.findNavController((MainActivity) requireActivity(),
                            R.id.nav_host_fragment_content_main).
                            navigate(R.id.action_FirstFragment_to_SecondFragment);
                }
                else
                    popUpDialog();
            }
        });
    }
    /**
     * handle button level three event
     */
    private void levelThree(){
        binding.level3IDBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getUserLevel()>=3) {
                    shareUserLevel();
                    NavDestination  navDestination =Navigation.findNavController((MainActivity) requireActivity(),
                            R.id.nav_host_fragment_content_main).getCurrentDestination();
                    if(navDestination.getId()==R.id.FirstFragment)
                    Navigation.findNavController((MainActivity) requireActivity(),
                            R.id.nav_host_fragment_content_main).
                            navigate(R.id.action_FirstFragment_to_SecondFragment);
                }
                else
                    popUpDialog();
            }
        });
    }

    private void popUpDialog() {

        AlertDialog alertDialog = new AlertDialog.Builder(requireContext()).create();
        alertDialog.setTitle("Note");
        alertDialog.setMessage(
                "Your currently qualify for level " + getUserLevel() + ".\n" +
                        "Please complete level " +getUserLevel()+" first."
        );
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    /**
     * set bottom navigation Visible on resume of welcome window
     */
    @Override
    public void onResume() {
        super.onResume();

        BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigationID);
        bottomNavigationView.setVisibility(View.VISIBLE);
        bottomNavigationView.getMenu().findItem(R.id.homeIdItem).setChecked(true);
    }

    /**
     * set data binding for this fragment null
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}