package com.example.wordgame;

import android.annotation.SuppressLint;
import android.graphics.Color;
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

import com.example.wordgame.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity  implements OnMultipleChoice,OnTrueFalseQuestion {
    /**
     * @serialField  appBarConfiguration for configuration of appbar
     * @serialField  binding is data binder for main activity
     * @serialField  views list of clicked view( multiple choice buttons)
     */
   private HashMap<String ,View> views = new HashMap<>();
    private Button btn1,btn2,btn3,btn4;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private  MultipleChoiceController multipleChoiceController;
    private final  String []letter = {"a","b","c","d"};
    private RecyclerView recycleView;


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
     * add the view to list
     * @param view is the clicked view
     */
    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void choice1(View view,int pos) {
        addView(view,pos);
    }
    /**
     * add the view to list
     * @param view is the clicked view
     */
    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void choice2(View view,int pos) {
        addView(view,pos);;
    }
    /**
     * add the view to list
     * @param view is the clicked view
     */
    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged", "ResourceAsColor"})
    @Override
    public void choice3(View view,int pos) {
        addView(view,pos);
    }
    /**
     * add the view to list
     * @param view is the clicked view
     */
    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void choice4(View view, int pos) {
        addView(view,pos);

    }

    /**
     *
     * @param view
     */
    @SuppressLint("ResourceAsColor")
    private  void addView(View view,int pos){
        if(views.containsValue(view)) {
            String key ="";
            view.setBackgroundColor(Color.BLUE);
            for (String key1 : views.keySet()){
                if(views.get(key1)==view) {
                    key = key1;
                    break;
                }
            }
            views.remove(key);
        }
        else {

            String key = makeKey(pos);
            view.setBackgroundColor(Color.GREEN);
            for (String key1 : views.keySet()){
                if(Integer.parseInt(key1.substring(0,1)) ==pos)
                    Objects.requireNonNull(views.get(key1)).setBackgroundColor(Color.BLUE);
            }
            views.put(key,view);

            Toast.makeText(this, ""+key,  Toast.LENGTH_SHORT).show();

        }
    }

    private  String makeKey(int pos){
        String key =pos+letter[0];
        for (String key1 : views.keySet()) {
            if (key1.equalsIgnoreCase(pos + "a"))
                key = pos + letter[1];
            else if (key1.equalsIgnoreCase(pos + "b"))
                key = pos + letter[2];
            else if (key1.equalsIgnoreCase(pos + "c"))
                key = pos + letter[3];
        }
        return  key;
    }



    @Override
    public void onMultipleChoice(Button button1, Button button2, Button button3, Button button4) {
      this.btn1=button1;
      this.btn2=button2;
      this.btn3=button3;
      this.btn4=button4;
    }

    @Override
    public void scrollDown(int position) {
        recycleView.scrollToPosition(position+1);
    }

    public void setMultipleChoiceController(MultipleChoiceController multipleChoiceController, RecyclerView view) {
        this.multipleChoiceController = multipleChoiceController;

        this.recycleView =view;
    }

    @Override
    public void trueButton(View view, int position) { addView(view,position);
    }

    @Override
    public void falseButton(View view, int position) {addView(view,position);

    }
}