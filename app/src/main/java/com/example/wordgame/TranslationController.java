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
import androidx.recyclerview.widget.RecyclerView;

public class TranslationController  extends RecyclerView.Adapter<TranslationController.Holder> {

    private final String [] questions;
    @SuppressLint("StaticFieldLeak")
    private static  Context context;
    private final int  layout ;
    private  static  LayoutInflater inflater;

    public TranslationController(@NonNull Context context, String[] question, int resource) {
        this.context=context;
        this.layout=resource;
        this.questions=question;
    }

    /**
     * get view of the recycle view
     * @param viewType is view of the recycle view
     * @param parent is the view of fragment learn
     * @return view of recycle view
     */

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout,parent,false);
        return new Holder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holder viewHolder, int position) {
        viewHolder.descriptionView.setText(questions[position]);
        viewHolder.pageNumber.setText((position+1)+"/"+questions.length);
    }

    /**
     * number of elements to inflated into listview
     * @return number of the element inflated
     */
    @Override
    public int getItemCount() {
        return questions.length ;
    }


    /**
     * Inner class that contain features of the element to be inflated into listview
     */
    static  class  Holder extends  RecyclerView.ViewHolder{
        TextView descriptionView,pageNumber, numberHints;
        Button hints;
        int numberOfHints =10;

        public Holder(@NonNull View itemView) {
            super(itemView);

            descriptionView = itemView.findViewById(R.id.transQuestionTextView);
            pageNumber = itemView.findViewById(R.id.pageNo);
            hints = itemView.findViewById(R.id.hintButton);
            numberHints = itemView.findViewById(R.id.noHintsTextView);
            hints.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    numberOfHints --;
                    if(numberOfHints <0)
                        hints.setEnabled(false);
                    else
                        numberHints.setText("No of hints: "+numberOfHints);
                        HintController hintController = new HintController(inflater,context);
                        hintController.getHint();
                }
            });
        }
    }


}
