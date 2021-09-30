package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Class CreateKeys is the class to create keys that will be used to store our answers
 * And stores the answers of the user
 */
public  abstract class CreateKeys {

    /**
     * @serialField  characters is the final list of letters that equal possible number of
     * maximum button we can have in per question
     * @serialField  views stores all answers of the game
     * @serialField  number of question of the activity
     */
    private final List<String> characters= new ArrayList<>();
    private final HashMap<String ,View>views = new HashMap<>() ;

    private int numberOfQuestions;

    /**
     * add view into views when user PlayFragment game
     * When user click button, clicked view is added
     * if view  was not clicked before, color int will equal to one
     * Else remove view  from views and counter stay  equal to zero
     * Button is identified by keys,
     * key equals to position concatenate with character from ASCII 65 character upwards
     * @param view clicked view
     * @return  color int
     */
    @SuppressLint("ResourceAsColor")
    public int  addView(View view, int pos){
        int color =0;
        if(views.containsValue(view)) {
            String key ="";
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
            color=1;
            views.put(key,view);
        }
        return color;
    }
    public void storeAnswer(List<String > answers) {
        answers.addAll(views.keySet());
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
     * @return key equals position + ASCII 65 character upwards
     */
    private  String makeKey(int pos){
        for(int c = 65; c <= getNumberOfQuestions();c++) {
            char cha = ( char)c;
            characters.add(cha+"");
        }
        return pos+""+characters.get(pos);
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

    /**
     * set number of questions
     * @param number is the number of the question
     */
    void setNumberOfQuestions(int number){
        numberOfQuestions=number;
    }

    /**
     * get  number of the questions
     * @return number of the questions
     */
    private int getNumberOfQuestions(){
        return  numberOfQuestions;
    }
}
