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

public class MultipleChoiceAdapter extends RecyclerView.Adapter<MultipleChoiceAdapter.Holder>  {

    private final String[] questions;
    @SuppressLint("StaticFieldLeak")
    private static Context context = null;
    private final int layout;
    private static OnMultipleChoice onMCQ ;
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
        viewHolder.page.setText((position+1)+"/"+questions.length);
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
            choice1.setBackgroundColor(Color.BLUE);
            choice2 = convertView.findViewById(R.id.answer2TextView);
            choice2.setBackgroundColor(Color.BLUE);
            choice3 = convertView.findViewById(R.id.answer3textView);
            choice3.setBackgroundColor(Color.BLUE);
            choice4 = convertView.findViewById(R.id.answer4TextView);
            choice4.setBackgroundColor(Color.BLUE);
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

            //nextQ.setOnClickListener(new HandleClickButtons(questions.length));
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
                if (v.getId() == R.id.moreQuestionID) {
                    int pos = getLayoutPosition();
                    if (pos + 1 < numberOfQuestions) {
                        nextQ.setVisibility(View.VISIBLE);
                        onMCQ.scrollDown(pos);
                    } else {
                        nextQ.setVisibility(View.GONE);
                    }
                }
                else {
                    if(v.getId()==R.id.answer1textView ||v.getId()==R.id.answer2TextView ||
                            v.getId()==R.id.answer3textView || v.getId()==R.id.answer4TextView) {
                        onMCQ.choices(v, getLayoutPosition());
                    }
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
                        if(! (v.getId() ==-1))
                            notifyItemChanged(holder.getAdapterPosition());
                    }
                });
            }

        }
    }
}


