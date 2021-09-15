package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordgame.R;

/**
 * Class helps to inflate content into Listview of  Play
 * Subclass of ArrayAdapter<String>
 */
public class TrueFalseAdapter extends RecyclerView.Adapter<TrueFalseAdapter.Holder> {
        /**
         * @serialField hearding  list of headings of lessons
         * @serialField lesson  list of lessons
         * @serialField context  of fragment PlayFragment
         */
        private final String [] questions;
        private final int[] lesson;
        private final Context context;
        private final int  layout;
        private Holder holder;
        private final OnTrueFalseQuestion onTruefalse;

        /**
         * Constructor of PlayFragment controller to initialise the fields
         * @param context of fragment TrueFalse
         * @param heading  list of headings of lessons
         * @param lessons   list of lessons
         * @param resource  number the layout to be inflated into listview
         */
        public TrueFalseAdapter(Context context, String[] heading, int[] lessons, int resource) {
                this.lesson=lessons;
                this.questions =heading;
                this.context =context;
                this.layout=resource;
                this.onTruefalse= (HandleTrueFalse) new HandleTrueFalse();
        }


        @NonNull
        @Override
        public TrueFalseAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = (LayoutInflater)LayoutInflater.from(context);
                View view = inflater.inflate(layout,parent,false);
                holder= new Holder(view);
                return holder;
        }


        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull Holder viewHolder, int position) {
                viewHolder.picView.setImageResource((lesson[position]));
                viewHolder.True.setText("True");
                 viewHolder.False.setText("False");
                viewHolder.descriptionView.setText(questions[position]);
                viewHolder.page.setText((position+1)+"/"+ questions.length+"");


        }

        /**
         * number of elements to inflated into listview
         * @return number of the element inflated
         */

        @Override
        public int getItemCount() {
                return questions.length;
        }


        /**
         * @Class  Holder is the inner class that contain features of the TruFalse game
         */
          class  Holder  extends RecyclerView.ViewHolder{
            ImageView picView;
            TextView descriptionView,page;
            Button  True,False;

            /**
             * constructor to initialise the serial filed of this class
             * @param convertView view contains buttons
             */
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

            /**
             * helper method to set click listeners of the buttons
             */
            private   void handleButtons(){
                        True.setOnClickListener(new Click());
                        False.setOnClickListener(new Click());
            }

            /**
             * @Class Click handle button clicks
             */
            private   class  Click implements  View.OnClickListener{

                /**
                 * If true clicked , true button is invoked
                 * else if false clicked , false button is invoked
                 * @param view is the clicked view
                 */
                @Override
                public void onClick(View view) {
                    notifyChanges();
                    if(view.getId()==R.id.trueButton)
                        onTruefalse.trueButton(view,getLayoutPosition());
                    else if(view.getId()==R.id.falseButton)
                        onTruefalse.falseButton(view,getLayoutPosition());
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
