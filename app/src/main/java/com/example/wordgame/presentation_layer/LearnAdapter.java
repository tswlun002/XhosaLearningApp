package com.example.wordgame.presentation_layer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordgame.R;

/**
 * Class helps to inflate content into Listview of Learn
 * Subclass of ArrayAdapter<String>
 */
public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.Holder> {

    /**
     * @serialField hearding  list of headings of lessons
     * @serialField lesson  list of lessons
     * @serialField context  of fragment LearnFragment
     */
    private final String [] hearding;
    private final int[] lesson;
    private final Context context;
    private  int layout;

    /**
     * Constructor of Learn controller to initialise the fields
     * @param context of fragment LearnFragment
     * @param heading  list of headings of lessons
     * @param lessons   list of lessons
     * @param resource  number the layout to be inflated into listview
     */
    public LearnAdapter(@NonNull Context context, String[] heading, int[] lessons, int resource) {
        this.lesson=lessons;
        this.hearding=heading;
        this.context =context;
        this.layout =resource;
    }


    /**
     * get view of the listview
     * @param viewType is view of the listview
     * @param parent is the view of fragment LearnFragment
     * @return view of listview
     */
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout,parent,false);

        return new Holder(view);
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
     * bind LearnFragment adapter view to holder
     * @param holder for LearnFragment adapter
     * @param position position of each view
     */
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.picView.setImageResource((lesson[position]));
        holder.descriptionView.setText(hearding[position]);
    }


    /**
     * Inner class that contain features of the element to be inflated into listview
     */
    static  class  Holder extends RecyclerView.ViewHolder {
        ImageView picView;
        TextView descriptionView;

        /**
         * constructor to initialise serial fields of Holder
         * @param itemView LearnFragment adapter view
         */
        public Holder(@NonNull View itemView) {
            super(itemView);
            picView = itemView.findViewById(R.id.imageAdapterId);
            descriptionView = itemView.findViewById(R.id.textViewAdaoterID);
        }
    }


}
