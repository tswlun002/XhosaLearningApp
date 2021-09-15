package com.example.wordgame.presentation_layer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordgame.R;
import com.example.wordgame.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    /**
     * @serialField  appBarConfiguration for configuration of appbar
     * @serialField  binding is data binder for main activity
     * @serialField  views list of clicked view( multiple choice buttons)
     */
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private MultipleChoiceAdapter multipleChoiceController;
    private RecyclerView recycleView;
    private View view;
    private int Id;

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
        MenuItem menuItem = menu.findItem(R.id.resultsId);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        menuItem.setVisible(false);
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
            else if(item.getItemId()==R.id.instructions){
                Instructions instructions = new Instructions(this , getLayoutInflater());
                instructions.instructions();
            }
            else if(item.getItemId()==R.id.aboutApp){
                String message ="This application about learning basic xhosa:\n" +
                        "terms\n" +
                        "communication\n" +
                        "basic sentence building\n";
               popUp("About App",message);
            }

           /* else if(item.getItemId()==R.id.resultsId){
                ActivityResults activiyResults = new ActivityResults(getLayoutInflater(),this,getID(), getView());
                 activiyResults.gradesActity(0,0);
            }*/
            else if (item.getItemId() == R.id.help){
                String message  = "Drag and drop text to edit text\n" +
                        "Or type the answer\n" +
                        "Selected option answer are highlighted light blue (CYAN)" ;
                popUp("This Activity Instructions",message);
            }

        return super.onOptionsItemSelected(item);
    }


    /**
     * set up pop up when click
     * gives user instruction how to PlayFragment current game
     * When user clicks Okay button, poop is dismissed
     * @param tittle tittle of the popup
     * @param message instruction of the current game
     */
    void popUp(String tittle ,String message){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(tittle);
        alertDialog.setMessage(message);
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    /**
     * @return view
     */
    View getView(){
        return  this.view;
    }

    /**
     * @param v  set view
     */
    void setView(View v){
        this.view=v;
    }

    /**
     *
     * @return action id for navigation between fragments
     */
    int getID(){
        return  this.Id;
    }

    /**
     * @param v set id for navigation
     */
    void setID(int v){
        this.Id=v;
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

    /**
     * handle all back pressed in fragments
     * @param fragment current fragment where back pressed is pressed
     * @param id  action id from current to back
     */
    public void backUpPressed(Fragment fragment, int id){
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(fragment).navigate(id);

            }
        });
    }

    /**
     * sets multipleChoiceController and recycle view
     * @param multipleChoiceController multipleChoiceController
     * @param view recycle view
     */
    public void setMultipleChoiceController(MultipleChoiceAdapter multipleChoiceController, RecyclerView view) {
        this.multipleChoiceController = multipleChoiceController;
        this.recycleView =view;
    }

    //192.168.137.136

}