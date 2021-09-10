package com.example.wordgame;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @Class CreateKeys is the class to create keys that will be used to store our answers
 * And stores the answers of the user
 */
public  abstract class CreateKeys {

    /**
     * @serialField  letter is the final list of letters that equal possible number of
     * maximum button we can have in per question
     * @serialField  views stores all answers of the game
     */
    private final String[] letter ={"a", "b", "c", "d", "e", "f"};
    private final HashMap<String ,View>views = new HashMap<>() ;

    /**
     * add view into views when user play game
     * When user click button, clicked view is added
     * if view  was not clicked before, change background color to green
     * Else remove view  from views and set view background color to blue
     * Button is identified by keys,
     * key = position concatenate with a to d
     * @param view clicked view
     */
    @SuppressLint("ResourceAsColor")
    public void  addView(View view, int pos){
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
            List<String> blueKeys = new ArrayList<>();
            getPreviousAnswers(views,blueKeys,pos);
            remove(views, pos, blueKeys);
            view.setBackgroundColor(Color.GREEN);
            views.put(key,view);
            Toast.makeText(view.getContext(), key+""+views.size(),key.length()).show();
        }
    }

    /**
     * Store keys of the previous answers in region of the current position
     * @param views list of answers mapped to keys
     * @param blueKeys is the keys of the previous answers
     * @param pos is the current clicked position
     */
    private  void getPreviousAnswers(HashMap<String ,View> views,List<String> blueKeys,int pos){
        for (String key1 : views.keySet()){
            try {
                if(Integer.parseInt(key1.substring(0,1).trim()) ==pos ) {
                    blueKeys.add(key1);
                    Objects.requireNonNull(views.get(key1)).setBackgroundColor(Color.BLUE);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    /**
     * create keys for buttons
     * @param pos  is the position of the view
     * @return key = position + letters from a-f
     */
    private  String makeKey(int pos){
        String key =pos+letter[0];
        for (String key1 : views.keySet()) {
            if (key1.equalsIgnoreCase(pos + "a"))
                key = pos + letter[1];
            else if (key1.equalsIgnoreCase(pos + "b"))
                key = pos + letter[2];
            else if (key1.equalsIgnoreCase(pos + "c"))
                key = pos + letter[3];
            else if (key1.equalsIgnoreCase(pos + "d"))
                key = pos + letter[4];
            else if (key1.equalsIgnoreCase(pos + "e"))
                key = pos + letter[5];
        }
        return  key;
    }

    /**
     * Removes previous answers in the list of the answers(views)
     * All previous answer's keys are contained in list called blueKeys
     * Loop through the list and remove keys that are in same regional layout
     * with the current position
     * @param views is the collections of View mapped their keys
     * @param position is the current clicked position in the interface
     * @param blueKeys list of previous answer's keys
     * @exception  ClassCastException   catch if the letter at index one is not Integer type
     */
    private void remove(HashMap<String ,View> views , int position, List<String> blueKeys){
        for (int i=0; i<blueKeys.size(); i++){
            String key1 = blueKeys.get(i);
            try {
                if(Integer.parseInt(key1.substring(0,1).trim()) ==position) {
                    views.remove(key1);
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

    }
}
