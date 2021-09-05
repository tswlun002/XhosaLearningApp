package com.example.wordgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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

import java.util.TreeMap;

/**
 * Class helps to inflate content into Listview of  Play
 * Subclass of ArrayAdapter<String>
 */
public class TrueFalseController extends RecyclerView.Adapter<TrueFalseController.Holder> {
        /**
         * @serialField hearding  list of headings of lessons
         * @serialField lesson  list of lessons
         * @serialField context  of fragment play
         */
        private final String [] hearding;
        private final int[] lesson;
        private final Context context;
        private  int inflateClass;
        private boolean status;
        private  int  layout;
        private OnTrueFalseQuestion onTruefalse;

        /**
         * Constructor of play controller to initialise the fields
         * @param context of fragment TrueFalse
         * @param heading  list of headings of lessons
         * @param lessons   list of lessons
         * @param resource  number the layout to be inflated into listview
         */
        public TrueFalseController(Context context, String[] heading, int[] lessons, int resource) {

                this.lesson=lessons;
                this.hearding=heading;
                this.context =context;
                this.inflateClass=resource;
                this.layout=resource;
        }


        @NonNull
        @Override
        public TrueFalseController.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = (LayoutInflater)LayoutInflater.from(context);
                View view = inflater.inflate(layout,parent,false);

                if(context instanceof OnTrueFalseQuestion)
                        this.onTruefalse= (OnTrueFalseQuestion) context;
                else
                        throw  new ClassCastException(context.toString()+" must implement onTrueFalse buttons");
                //viewHolder.pages.setText(position+"");
                return new Holder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull Holder viewHolder, int position) {
                viewHolder.picView.setImageResource((lesson[position]));
                viewHolder.True.setText("True");
                 viewHolder.False.setText("False");
                viewHolder.descriptionView.setText(hearding[position]);
                viewHolder.page.setText((position+1)+"/"+hearding.length+"");

        }
        /**
         * number of elements to inflated into listview
         * @return number of the element inflated
         */

        @Override
        public int getItemCount() {
                return hearding.length;
        }


        /**
         * Inner class that contain features of the person
         */
          class  Holder  extends RecyclerView.ViewHolder{
            ImageView picView;
            TextView descriptionView,page;
            Button  True,False;

                public Holder(@NonNull View convertView) {
                        super(convertView);
                       picView = (ImageView) convertView.findViewById(R.id.QuestionImageViewID);
                       descriptionView = (TextView) convertView.findViewById(R.id.picQuestionTextViewID);
                       page = convertView.findViewById(R.id.page);
                       True = (Button) convertView.findViewById(R.id.trueButton);
                       True.setBackgroundColor(Color.BLUE);
                       False = (Button) convertView.findViewById(R.id.falseButton);
                       False.setBackgroundColor(Color.BLUE);
                       handleButtons();

                }

                private   void handleButtons(){
                        True.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        onTruefalse.trueButton(v,getAdapterPosition());
                                }
                        });

                        False.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        onTruefalse.falseButton(v,getAdapterPosition());
                                }
                        });
                }

        }



}
