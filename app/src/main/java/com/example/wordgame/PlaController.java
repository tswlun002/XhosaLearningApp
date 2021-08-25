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

public class PlaController extends ArrayAdapter<String> {

private final String [] hearding;
private final int[] lesson;
private final Context context;
private  int inflateClass;

public PlaController(Context context, String[] heading, int[] lessons, int resource) {
        super(context, resource);

        this.lesson=lessons;
        this.hearding=heading;
        this.context =context;
        this.inflateClass=resource;
        }

@Override
public int getCount() {
        return hearding.length;
        }

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
