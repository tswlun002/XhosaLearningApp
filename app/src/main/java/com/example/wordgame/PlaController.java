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
/**
 * Class helps to inflate content into Listview of  Play
 * Subclass of ArrayAdapter<String>
 */
public class PlaController extends ArrayAdapter<String> {
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

        /**
         * Constructor of play controller to initialise the fields
         * @param context of fragment learn
         * @param heading  list of headings of lessons
         * @param lessons   list of lessons
         * @param resource  number the layout to be inflated into listview
         */
        public PlaController(Context context, String[] heading, int[] lessons, int resource) {
                super(context, resource);

                this.lesson=lessons;
                this.hearding=heading;
                this.context =context;
                this.inflateClass=resource;
        }

        /**
         * number of elements to inflated into listview
         * @return number of the element inflated
         */
        @Override
        public int getCount() {
                return hearding.length;
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
                Holder  viewHolder = new Holder();
                if(convertView ==null) {
                LayoutInflater inflaterLost_relative = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                //get listview into convertView
                convertView = inflaterLost_relative.inflate(inflateClass, parent, false);

                //inflate picture and text description
                viewHolder.picView = (ImageView) convertView.findViewById(R.id.QuestionImageViewID);
                viewHolder.descriptionView = (TextView) convertView.findViewById(R.id.picQuestionTextViewID);

                //set into holder
                convertView.setTag(viewHolder);

                }else{
                viewHolder  = (Holder) convertView.getTag();

                }
                //set pictures and description of lost people
                viewHolder.picView.setImageResource((lesson[position]));
                viewHolder.descriptionView.setText(hearding[position]);

                return convertView;

       }



        /**
         * Inner class that contain features of the person
         */
         static  class  Holder{
            ImageView picView;
            TextView descriptionView;
        }





}