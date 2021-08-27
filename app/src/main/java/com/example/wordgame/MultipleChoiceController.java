package com.example.wordgame;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MultipleChoiceController  extends ArrayAdapter<String> {

    private final String[] questions;
    private final Context context;
    private final int layout;
    private final String[] choices ={

            "Isitulo",
            "Ukuhamba",
            "ukutya",
            "Ekhaya"
    };

    public MultipleChoiceController(@NonNull Context context, String[] question, int resource) {
        super(context, resource);
        this.context = context;
        this.layout = resource;
        this.questions = question;
    }

    /**
     * number of elements to inflated into listview
     *
     * @return number of the element inflated
     */
    @Override
    public int getCount() {
        return questions.length;
    }


    /**
     * get view of the listview
     *
     * @param position    of each element/item to inflate into listview
     * @param convertView is view of the listview
     * @param parent      is the view of fragment Multiple choice
     * @return view of listview
     */
    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MultipleChoiceController.Holder viewHolder = new MultipleChoiceController.Holder();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //get listview into convertView
            convertView = inflater.inflate(layout, parent, false);


            viewHolder.question = (TextView) convertView.findViewById(R.id.transQuestionTextView);
            viewHolder.choice1 = (Button) convertView.findViewById(R.id.answer1textView);
            viewHolder.choice2 = (Button) convertView.findViewById(R.id.answer2TextView);
            viewHolder.choice3 = (Button) convertView.findViewById(R.id.answer3textView);
            viewHolder.choice4 = (Button) convertView.findViewById(R.id.answer4TextView);
            viewHolder.pages = (TextView)convertView.findViewById(R.id.questionNumber) ;
            //set into holder
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (MultipleChoiceController.Holder) convertView.getTag();

        }
        //set pictures and description of lost people
        viewHolder.question.setText(questions[position]);
        viewHolder.choice1.setText(choices[0]);
        viewHolder.choice2.setText(choices[1]);
        viewHolder.choice3.setText(choices[2]);
        viewHolder.choice4.setText(choices[3]);
        viewHolder.pages.setText(position+"");


        return convertView;

    }
    /**
     * Inner class that contain features of the element to be inflated into listview
     */
    static  class  Holder{
        TextView question ,pages;
        Button choice3,choice1,choice2,choice4;
    }

}


