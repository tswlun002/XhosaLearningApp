package com.example.wordgame.presentation_layer;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class SubmitHandler extends ActivityResults implements OnSubmit,Comparable{

    /**
     * @serialField  inflater to inflate user score submitted
     */
    private final LayoutInflater inflater;
    private final HashMap<String,String> AllAnswers;
    private final HashMap<String,String> userAnswers;
    private final List<String> answers = new ArrayList<>();
    private  List<String> questions;
    private  double score;

    public List<String> getAnswers() {
        return answers;
    }

    /**
     * initialise   super class and inflater
     * @param inflater to inflate user score submitted
     * @param Id navigation to score board
     * @param view current view or fragment of the score
     */
    public SubmitHandler(LayoutInflater inflater, int Id, View view,
                         HashMap<String,String> userAnswer, HashMap<String,String> gameAnswers) {
        super(inflater,Id,view);
        this.inflater=inflater;
        this.userAnswers= userAnswer;
        this.AllAnswers =gameAnswers;

    }

    /**
     * submit user score
     * @param view current view or fragment of the score
     * @param inflater  of the  the score window
     */
    @Override
    public void onSubmit(View view, LayoutInflater inflater) {

        gradesActity(gradeAnswer(),getUserAnswers().size(), answers);
    }
    /**
     * handle button event
     * Show grades for user
     * @param v is clicked view
     */
    public void onClick(View v) {
       onSubmit(v,inflater);
    }

    /**
     * get game answers
     * @return game answer
     */
    public  HashMap<String,String> getAllAnswers(){
        return AllAnswers;
    }
    /**
     * get game user answers
     * @return game user answers
     */
    public  HashMap<String,String> getUserAnswers(){
        return userAnswers;
    }

    /**
     * Get questions
     * @return questions
     */
    public List<String> getQuestions(){
        return questions;
    }

    /**
     * grade user results
     * Increment percentage if user answer equals actual answer
     * Calculate user percentage relative to total game marks
     * @return user percentage relative to total marks
     */
    @SuppressLint("DefaultLocale")
    double gradeAnswer(){
        double percent =0;
        //Toast.makeText(inflater.getContext(),userAnswers+"\n\nall a",Toast.LENGTH_SHORT).show();
        for (Object string: userAnswers.keySet().toArray()) {
            int value = compareTo(string + ":" + userAnswers.get(string + ""));
            if (value == 0)
                percent++;

        }
       double marks  =Double.parseDouble(String.format("%.1f",percent));
        setScore(marks);
      return  marks;
    }

    /**
     *
     * @return score of the game
     */
    @Override
    public  double getScore(){
        return  score;
    }

    /**
     *
     * @param value final score of the game
     */
    private  void setScore(double value){
        score=value;
    }

    /**
     * compare user answers with actual game answers
     * @param string user answer concatenated with question
     * @return number representing comparison
     */
    @Override
    public int compareTo(Object string) {
        String newString  = string.toString();
        String question= newString.substring(0, newString.indexOf(":")).trim().toLowerCase();
        String answer  = newString.substring(newString.indexOf(":")+1).trim().toLowerCase();
        answers.add(question+" : "+AllAnswers.get(question));
        int value =-10;
        if(!(answer.equalsIgnoreCase("none"))){
            try {
                value=Objects.requireNonNull(AllAnswers.get(question)).
                        trim().toLowerCase()
                        .compareTo(answer);
            }catch (Exception e){
                Toast.makeText(inflater.getContext(), question+"\n\n "+e.toString(), Toast.LENGTH_SHORT).show();
            }

        }

        return value;
    }
}
