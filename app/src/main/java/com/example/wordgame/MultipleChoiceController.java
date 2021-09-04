package com.example.wordgame;


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

public class MultipleChoiceController  extends RecyclerView.Adapter<MultipleChoiceController.Holder>  {

    private final String[] questions;
    @SuppressLint("StaticFieldLeak")
    private static Context context = null;
    private final int layout;
    private static  OnMCQ onMCQ ;
    private  static  Holder holder;
    private  static  int lastSelected =0;
    private  static  int Selected =0;
    @SuppressLint("StaticFieldLeak")
    static  TextView question ;
    @SuppressLint("StaticFieldLeak")
    static Button choice3,choice1,choice2,choice4, nextQ;
    private final String[] choices ={

            "Isitulo",
            "Ukuhamba",
            "ukutya",
            "Ekhaya"
    };
    private int pos;

    public MultipleChoiceController(@NonNull Context context, String[] question, int resource) {
        MultipleChoiceController.context = context;
        this.layout = resource;
        this.questions = question;
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
        if(context instanceof OnMCQ)
            onMCQ = (OnMCQ) context;
        else
            throw  new ClassCastException(context.toString()+" must implement OnMCQ button");
        //viewHolder.pages.setText(position+"");
        return  new MultipleChoiceController.Holder( view);
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
        choice1.setText(choices[0]);
        choice2.setText(choices[1]);
        choice3.setText(choices[2]);
        choice4.setText(choices[3]);
        holder =viewHolder;
        onMCQ.onMultipleChoice(choice1,choice2,choice3,choice4);
        setPos(position);

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
         int pos;

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

            handleRecycleView();

        }
        private void handleRecycleView (){

            choice1.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    notifyChanges();
                    onMCQ.choice1(v);


                }
            });



            choice2.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                   // onMCQ.onMultipleChoice(choice1,choice2,choice3,choice4);
                    notifyChanges();
                    onMCQ.choice2(v);


                }
            });


            choice3.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    //onMCQ.onMultipleChoice(choice1,choice2,choice3,choice4);
                    notifyChanges();
                    onMCQ.choice3(v);


                }
            });


            choice4.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {

                    //onMCQ.onMultipleChoice(choice1,choice2,choice3,choice4);
                    notifyChanges();
                    onMCQ.choice4(v);

                }
            });
        }



    }

    public interface  OnMCQ {
        public void choice1 (View view);
        public void choice2 (View view);
        public void choice3 (View view);
        public void choice4 (View view);
        public void onMultipleChoice(Button button1,Button button2,Button button3,Button button4);
        public   int scrollDown(int pos);

    }

    public void notifyChanges(){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelected = Selected;
                Selected = getPos();
                //notifyItemChanged(lastSelected);
                notifyItemChanged(holder.getAdapterPosition());


            }
        });
    }

    private  int getPos(){
        return pos;
    }
    private  void setPos(int pos){
        this.pos=pos;
    }

}


