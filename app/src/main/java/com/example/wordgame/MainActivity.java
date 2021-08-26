package com.example.wordgame;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.wordgame.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    /**
     * @serialField  appBarConfiguration for configuration of appbar
     * @serialField  binding is data binder for main activity
     */
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    /**
     * creates main activity  ,set up navigation controller
     * @param savedInstanceState of main activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // upon click allow the fragment to get another fragment

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    }



    /**
     * inflate created menu in a tool bar (drop down menu)
     * @param menu is drop down menu in a tool bar
     * @return true when menu is inflated
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome_menu, menu);
        return true;
    }
    /** Handle action bar item clicks here. The action bar will
     *  automatically handle clicks on the Home/Up button, so long
     *  Then Navigate to home upon the second case click
     *  as you specify a parent activity in AndroidManifest.xml.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            if( item.getItemId() == R.id.progressIdItem)
                Navigation.findNavController(this,R.id.nav_host_fragment_content_main).navigate(R.id.action_FirstFragment_to_proggress);
            else if(item.getItemId()==R.id.homeIdItem)
                Navigation.findNavController(this,R.id.nav_host_fragment_content_main).navigate(R.id.action_proggress_to_FirstFragment);



        return super.onOptionsItemSelected(item);
    }

    /**
     * set support for navigation up
     * @return true if navigation is supported else false
     */
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


}