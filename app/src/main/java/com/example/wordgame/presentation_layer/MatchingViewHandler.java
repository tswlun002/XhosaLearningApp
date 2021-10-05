package com.example.wordgame.presentation_layer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wordgame.R;
import com.example.wordgame.model_layer.Matching;

import java.util.HashMap;
import java.util.List;

/**
 * @Class MatchingViewHandler handle click view( buttons) and
 * Call parent class  Drag andDrop to handle drag and drop events
 */
public class MatchingViewHandler extends DragandDrop implements OnMatchingViewHandler,OnExtractResults {

    /**
     * @serial view is the view of MatchingFragment
     */
    private  final  View view;
    private TextView firstQ,secondQ,thirdQ,fourthQ;
    private final HashMap<String,String> userAnswers = new HashMap<>();
    private final HashMap<String,String> matchingAnswers = new HashMap<>();
    private  Matching matching;
    private HashMap<Integer, String> questions= new HashMap<>();
    HashMap<Integer, String>  answers=new HashMap<>();
    private int level = 0;
    private String heading = "";



    /**
     * Get user results
     * @return user results
     */
    @Override
    public HashMap<String, String> getUserAnswers() {
        extractUserAnswers();
        return userAnswers;
    }

    /**
     * Get game results
     * @return game results
     */
    @Override
    public HashMap<String, String> getGameAnswers() {
        return matchingAnswers;
    }

    @Override
    public String getGameInformation() {
        return matching.toString();
    }

    /**
     * extract user results from the with their corresponding questions
     * Edittext have user answers
     * And textview have questions
     */
    public void extractUserAnswers(){
        EditText userAnswer1 = view.findViewById(R.id.xhosaEditText1);
        EditText userAnswer2 = view.findViewById(R.id.xhosaEditText2);
        EditText userAnswer3 = view.findViewById(R.id.xhosaEditText3);
        EditText userAnswer4 = view.findViewById(R.id.xhosaEditText4);
        userAnswers.put(firstQ.getText().toString(),userAnswer1.getText().toString());
        userAnswers.put(secondQ.getText().toString(),userAnswer2.getText().toString());
        userAnswers.put(thirdQ.getText().toString(),userAnswer3.getText().toString());
        userAnswers.put(fourthQ.getText().toString(),userAnswer4.getText().toString());
    }

    /**
     * extract matching game answers with their question
     * @param material is the object of matching material
     */
    void extractMatchingAnswers(List<Matching> material){
        for(Matching answers: material){
            String question,answer;
            question=answers.getQuestions().trim().toLowerCase();
            answer =answers.getAnswers().trim().toLowerCase();
            matchingAnswers.put(question,answer);
        }

    }

    private void initQuestionAnswers(List<Matching> material){
        int count  = 1;
        for (Matching materialActivity : material) {

            level = materialActivity.getLevel();
            heading = materialActivity.getTittle();
            String question,answer;
            question =materialActivity.getQuestions().trim().toLowerCase();
            answer =materialActivity.getAnswers().trim().toLowerCase();
            questions.put(count,question);
            answers.put(count,answer);
            count++;
        }
    }

    public  MatchingViewHandler (View view){
        this.view=view ;
    }


    public void setData(List<Matching> material){
        if(material != null) {
            initQuestionAnswers(material);
            matching = material.get(0);
            extractMatchingAnswers(material);
            if(questions !=null & answers !=null) {
                TextView tittle = view.findViewById(R.id.instructionTextView);
                tittle.setText(heading);
                firstQ = view.findViewById(R.id.engTextView1);
                firstQ.setText(questions.get(1));
                secondQ = view.findViewById(R.id.engTextView2);
                secondQ.setText(questions.get(2));
                thirdQ = view.findViewById(R.id.engTextView3);
                thirdQ.setText(questions.get(3));
                fourthQ = view.findViewById(R.id.engTextView4);
                fourthQ.setText(questions.get(4));
                TextView answer1 = view.findViewById(R.id.xhosaMatchTextView1);
                answer1.setText(answers.get(1));
                TextView answer2 = view.findViewById(R.id.xhosaMatchTextView2);
                answer2.setText(answers.get(2));
                TextView answer3 = view.findViewById(R.id.xhosaMatchTextView3);
                answer3.setText(answers.get(3));
                TextView answer4 = view.findViewById(R.id.xhosaMatchTextView4);
                answer4.setText(answers.get(4));
                TextView answer5 = view.findViewById(R.id.xhosaMatchTextView5);
                answer5.setText(answers.get(5));
                TextView answer6 = view.findViewById(R.id.xhosaMatchTextView6);
                answer6.setText(answers.get(6));
                TextView answer7 = view.findViewById(R.id.xhosaMatchTextView7);
                answer7.setText(answers.get(7));
                TextView answer8 = view.findViewById(R.id.xhosaMatchTextView8);
                answer8.setText(answers.get(8));
                if (level == 1) {
                    setVisible(answer5);
                    setVisible(answer6);
                    setVisible(answer7);
                    setVisible(answer8);
                }
                if(level ==2){
                    setVisible(answer7);
                    setVisible(answer8);
                }
            }
        }
    }
    private void setVisible(View view){
        view.setVisibility(View.GONE);

    }
    /**
     * handle view clicks events
     * @param view being clicked
     */
    @Override
    public void viewClicked(View view) {
        handleViewDrag(view);
    }

    @Override
    public void numberOfQuestions(int number) {

    }

    /**
     * Call drag to in a super class handles drag events
     * @param view being dragged
     */
    void handleViewDrag(View view){
        super.handleDragDrop(view);
    }
}
