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
        LayoutInflater inflater = (LayoutInflater)LayoutInflater.from(context);
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
            question = (TextView) convertView.findViewById(R.id.transQuestionTextView);
            choice1 = (Button) convertView.findViewById(R.id.answer1textView);
            choice1.setBackgroundColor(Color.BLUE);
            choice2 = (Button) convertView.findViewById(R.id.answer2TextView);
            choice2.setBackgroundColor(Color.BLUE);
            choice3 = (Button) convertView.findViewById(R.id.answer3textView);
            choice3.setBackgroundColor(Color.BLUE);
            choice4 = (Button) convertView.findViewById(R.id.answer4TextView);
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
            choice1.setOnClickListener(new HandleClickButtons(questions.length));
            choice2.setOnClickListener(new HandleClickButtons(questions.length));
            choice3.setOnClickListener(new HandleClickButtons(questions.length));
            choice4.setOnClickListener(new HandleClickButtons(questions.length));

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
                    onMCQ.choices(v,getLayoutPosition());
                }
            }
            /**
             * notify holder about number of hints changes
             */
            private void notifyChanges(){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        notifyItemChanged(holder.getAdapterPosition());
                    }
                });
            }

        }
    }
}


