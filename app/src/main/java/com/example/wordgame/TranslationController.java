package com.example.wordgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TranslationController  extends ArrayAdapter<String> {

    private final String [] questions;
    private final Context context;
    private final int  layout ;

    public TranslationController(@NonNull Context context, String[] question, int resource) {
        super(context, resource);
        this.context=context;
        this.layout=resource;
        this.questions=question;
    }
    /**
     * number of elements to inflated into listview
     * @return number of the element inflated
     */
    @Override
    public int getCount() {
        return questions.length;
    }


    /**
     * get view of the listview
     * @param position of each element/item to inflate into listview
     * @param convertView is view of the listview
     * @param parent is the view of fragment learn
     * @return view of listview
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TranslationController.Holder viewHolder = new TranslationController.Holder();
        if(convertView ==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //get listview into convertView
            convertView = inflater.inflate(layout, parent, false);

            //inflate picture and text description
            viewHolder.descriptionView = (TextView) convertView.findViewById(R.id.transQuestionTextView);

            //set into holder
            convertView.setTag(viewHolder);

        }else{
            viewHolder  = (TranslationController.Holder) convertView.getTag();

        }
        //set pictures and description of lost people
        viewHolder.descriptionView.setText(questions[position]);

        return convertView;

    }



    /**
     * Inner class that contain features of the element to be inflated into listview
     */
    static  class  Holder{
        TextView descriptionView;
    }


}
