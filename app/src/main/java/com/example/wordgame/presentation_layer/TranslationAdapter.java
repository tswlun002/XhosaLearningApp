package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordgame.R;

public class TranslationAdapter extends RecyclerView.Adapter<TranslationAdapter.Holder> {

    private final String [] questions;
    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final int  layout ;
    private static  OnHints onHints;
    private  static  LayoutInflater inflater;
    private static  Holder holder;

    public TranslationAdapter(@NonNull Context context, String[] question, int resource) {
        this.context=context;
        this.layout=resource;
        this.questions=question;
        onHints =new HintHandler();

    }

    /**
     * get view of the recycle view
     * @param viewType is view of the recycle view
     * @param parent is the view of fragment LearnFragment
     * @return view of recycle view
     */

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout,parent,false);
       holder=  new Holder(view);
       return holder;
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
     class  Holder extends  RecyclerView.ViewHolder{
        TextView descriptionView,pageNumber, numberHints;
        Button hints;
        public Holder(@NonNull View itemView) {
            super(itemView);

            descriptionView = itemView.findViewById(R.id.transQuestionTextView);
            pageNumber = itemView.findViewById(R.id.pageNo);
            hints = itemView.findViewById(R.id.hintButton);
            numberHints = itemView.findViewById(R.id.noHintsTextView);
            numberHints.setClickable(false);

            /**
             * handle hint button click
             */
            hints.setOnClickListener(new View.OnClickListener() {
                /**
                 * notify changes
                 * Then request hint
                 * And update the number of hints left
                 * @param view is the hint button view
                 */
                @Override
                public void onClick(View view) {
                    notifyChanges();
                    onHints.onRequestHint(inflater,view,getLayoutPosition());
                    onHints.updateNumberHints(numberHints);
                }
            });

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
