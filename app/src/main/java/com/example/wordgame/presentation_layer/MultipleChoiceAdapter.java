package com.example.wordgame.presentation_layer;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordgame.R;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceAdapter extends RecyclerView.Adapter<MultipleChoiceAdapter.Holder>  {

    private final String[] questions;
    @SuppressLint("StaticFieldLeak")
    private static Context context = null;
    private final int layout;
    private static OnMultipleChoice onMCQ ;
    private  final  int [] buttonColors = {Color.BLUE,Color.GREEN};
    private final List<Integer> colors = new ArrayList<>();
    private final List<String> answers = new ArrayList<>();
    private   Holder holder;
    @SuppressLint("StaticFieldLeak")
    static  TextView question ;
    @SuppressLint("StaticFieldLeak")
    private final String[] choices ={

            "Isitulo",
            "Ukuhamba",
            "ukutya",
            "Ekhaya"
    };

    /**
     * construct to initialise the serial fields
     * @param context is the context that inflate recycleview
     * @param question list of questions
     * @param resource id of the adapter to be inflate on listview
     */
    public MultipleChoiceAdapter(@NonNull Context context, String[] question, int resource) {
        MultipleChoiceAdapter.context = context;
        this.layout = resource;
        this.questions = question;
        onMCQ = new HandleMQCButtons();
        generateColors(question.length);
    }
    /**
     * get view of the listview
     * @param parent      is the view of fragment Multiple choice
     * @return view of listview
     */
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout,parent,false);

        holder=  new MultipleChoiceAdapter.Holder( view);
        return  holder;
    }

    /**
     * set questions , choices(answers) and question number
     * @param viewHolder holds the view ( button, texts)
     * @param position position of the item
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holder viewHolder, @SuppressLint("RecyclerView") int position) {
        question.setText(questions[position]);
        viewHolder.choice1.setText(choices[0]);
        viewHolder.choice2.setText(choices[1]);
        viewHolder.choice3.setText(choices[2]);
        viewHolder.choice4.setText(choices[3]);
        viewHolder.choice1.setBackgroundColor(getColors().get(position*4));
        viewHolder.choice2.setBackgroundColor(getColors().get(position*4+1));
        viewHolder.choice3.setBackgroundColor(getColors().get(position*4+2));
        viewHolder.choice4.setBackgroundColor(getColors().get(position*4+3));
        viewHolder.page.setText((position+1)+"/"+questions.length);
        holder.setNumberOfQuestions(questions.length+65);
    }


    /**
     * number of elements to inflated into listview
     *
     * @return number of the element inflated
     */
    @Override
    public int getItemCount() {
        return questions.length;
    }

    /**viewHolder
     * generate initial list of colors of buttons for all questions
     * List button is 4 times activity questions because each question contains four buttons
     * All colors  initial are set to zero
     * Even index store colors for true  buttons
     * And odd index store  colors for false buttons
     * @param setSize  number of questions set for activity
     */
    private  void generateColors(int setSize){
        int size  = setSize*4;
        for(int i =0 ; i<size; i++)
            colors.add(Color.BLUE);
    }

    /**
     * set new color into colors list at at index
     * @param index where we set the new color
     * @param color new color being set
     */
    private void setColor(int index, int color){
        colors.set(index,color);
        //Toast.makeText(context,"color is changes  at "+index+ "color is "+colors.get(index),Toast.LENGTH_SHORT).show();
    }

    /**
     * @return   list of  of all colors  for all questions
     */
    private List<Integer> getColors(){
        return  colors;
    }
    /**
     * Inner class that contain features of the element to be inflated into listview
     */
    class  Holder extends RecyclerView.ViewHolder{
        TextView page;
        Button choice3,choice1,choice2,choice4, nextQ;
        public Holder(@NonNull View convertView) {
            super(convertView);
            question = convertView.findViewById(R.id.transQuestionTextView);
            choice1 = convertView.findViewById(R.id.answer1textView);
            choice2 = convertView.findViewById(R.id.answer2TextView);
            choice3 = convertView.findViewById(R.id.answer3textView);
            choice4 = convertView.findViewById(R.id.answer4TextView);
            nextQ   = convertView.findViewById(R.id.moreQuestionID);
            page = convertView.findViewById(R.id.page);
            //onMCQ.onMultipleChoice(choice1,choice2,choice3,choice4);

            handleRecycleView();


        }

        /**
         * Helper method to handle click listeners
         */
        private void handleRecycleView (){
            HandleClickButtons handleClickButtons = new HandleClickButtons(questions.length);
            choice1.setOnClickListener(handleClickButtons);
            choice2.setOnClickListener(handleClickButtons);
            choice3.setOnClickListener(handleClickButtons);
            choice4.setOnClickListener(handleClickButtons);
        }
        /**
         * set number of questions for this game
         */
        void setNumberOfQuestions(int value){
            onMCQ.numberOfQuestions(value);
        }

        /**
         * @Class  HandleClickButtons handle MCQ button clicks by implement click listeners
         */
        private   class HandleClickButtons implements View.OnClickListener{
            private final int numberOfQuestions;

            /**
             * constructor to initialise  serial fileds
             * @param numberQ number of the question
             */
            HandleClickButtons(int numberQ){
                numberOfQuestions =numberQ;
            }

            /**
             * notify changes
             * handles the button clicks of multiple choice
             * @param v view clicked by MCQ buttons
             */
            @Override
            public void onClick(View v) {
                notifyChanges();
                final  int [] buttonColors = {Color.BLUE,Color.GREEN};
                int pos = getLayoutPosition();
                if (v.getId() == R.id.moreQuestionID) {

                    if (pos + 1 < numberOfQuestions) {
                        nextQ.setVisibility(View.VISIBLE);
                        onMCQ.scrollDown(pos);
                    } else {
                        nextQ.setVisibility(View.GONE);
                    }
                }
                else {
                    if(v.getId()==R.id.answer1textView ){
                        onMCQ.choices(v, pos);
                        onMCQ.StoreAnswer(answers);
                        setColor(pos*4,buttonColors[onMCQ.getColor()]);
                        setColor(pos*4+1,buttonColors[0]);
                        setColor(pos*4+2,buttonColors[0]);
                        setColor(pos*4+3,buttonColors[0]);

                    }
                    else if(v.getId()==R.id.answer2TextView){
                        onMCQ.choices(v, pos);
                        onMCQ.StoreAnswer(answers);
                        setColor(pos*4,buttonColors[0]);
                        setColor(pos*4+1,buttonColors[onMCQ.getColor()]);
                        setColor(pos*4+2,buttonColors[0]);
                        setColor(pos*4+3,buttonColors[0]);
                    }
                    else if(v.getId()==R.id.answer3textView){
                        onMCQ.choices(v, pos);
                        onMCQ.StoreAnswer(answers);
                        setColor(pos*4,buttonColors[0]);
                        setColor(pos*4+1,buttonColors[0]);
                        setColor(pos*4+2,buttonColors[onMCQ.getColor()]);
                        setColor(pos*4+3,buttonColors[0]);
                    }
                    else if(v.getId()==R.id.answer4TextView) {
                        setColor(pos*4,buttonColors[0]);
                        setColor(pos*4+1,buttonColors[0]);
                        setColor(pos*4+2,buttonColors[0]);
                        setColor(pos*4+3,buttonColors[onMCQ.getColor()]);
                    }else
                    {}
                    notifyDataSetChanged();
                }
            }

            /**
             * notify holder about changes
             * If View id is  not equals to negative one ( clicked in the layout instead of buttons)
             */
            private void notifyChanges(){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(! (v.getId() ==RecyclerView.NO_POSITION))
                            notifyItemChanged(holder.getAdapterPosition());
                    }
                });
            }

        }
    }
}


