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
    private static  OnMultipleChoice onMCQ ;
    private  static  Holder holder;
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
        if(context instanceof OnMultipleChoice)
            onMCQ = (OnMultipleChoice) context;
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
        viewHolder.page.setText((position+1)+"/"+questions.length);
        holder =viewHolder;
        onMCQ.onMultipleChoice(choice1,choice2,choice3,choice4);


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
            handleRecycleView();

        }
        private void handleRecycleView (){

            choice1.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    notifyChanges();
                    onMCQ.choice1(v,getLayoutPosition());


                }
            });



            choice2.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                   // onMCQ.onMultipleChoice(choice1,choice2,choice3,choice4);
                    notifyChanges();

                    onMCQ.choice2(v,getLayoutPosition());


                }
            });


            choice3.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    //onMCQ.onMultipleChoice(choice1,choice2,choice3,choice4);
                    notifyChanges();
                    onMCQ.choice3(v,getLayoutPosition());


                }
            });


            choice4.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {

                    //onMCQ.onMultipleChoice(choice1,choice2,choice3,choice4);
                    notifyChanges();
                    onMCQ.choice4(v,getLayoutPosition());

                }
            });

            nextQ.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getLayoutPosition();
                    if(pos+1<questions.length) {
                        nextQ.setVisibility(View.VISIBLE);
                        onMCQ.scrollDown(pos);
                    }else{
                        nextQ.setVisibility(View.GONE);

                    }
                }
            });
        }



    }

    public void notifyChanges(){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }


}


