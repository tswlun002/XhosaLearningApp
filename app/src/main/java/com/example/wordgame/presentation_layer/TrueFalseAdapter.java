package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordgame.R;
import com.example.wordgame.model_layer.TrueFalseGame;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class helps to inflate content into Listview of  Play
 * Subclass of ArrayAdapter<String>
 */
public class TrueFalseAdapter extends RecyclerView.Adapter<TrueFalseAdapter.Holder> implements OnExtractResults {


    /**
         * @serialField heading  list of headings of lessons
         * @serialField lesson  list of lessons
         * @serialField context  of fragment PlayFragment
         */
        private List<String> questions;
        private final List<Bitmap> figures = new ArrayList<>() ;
        private final List<View> answers = new ArrayList<>() ;
        private final Context context;
        private final int  layout;
        private  int size;
        private Holder holder;
        private final List<Integer>  colors = new ArrayList<>();
        private  final  int [] buttonColors = {Color.BLUE,Color.GREEN};
        private final OnTrueFalseQuestion onTruefalse;
        private List<TrueFalseGame> trueFalse;


        /**
         * Constructor of PlayFragment controller to initialise the fields
         * @param context of fragment TrueFalse
         * @param resource  number the layout to be inflated into listview
         */
        public TrueFalseAdapter(Context context, int resource) {

                this.context =context;
                this.layout=resource;
                this.onTruefalse=  new HandleTrueFalse();

        }
    /**
     * number of elements to inflated into listview
     * @return number of the element inflated
     */

    @Override
    public int getItemCount() {
        int y=0;
        if(questions!=null)
            y=getSize();

        return y ;
    }

        @NonNull
        @Override
        public TrueFalseAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater =LayoutInflater.from(context);
                View view = inflater.inflate(layout,parent,false);
                holder= new Holder(view);
                return holder;
        }

    /***
     * bit view of the holder
     * set text for True button and False button
     * set Figure for each item  set questions and pages number
     * @param viewHolder is the holder  of this adapter class
     * @param position is the current position in the recycle view
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holder viewHolder, int position) {
            viewHolder.True.setText("True");
            viewHolder.False.setText("False");
            viewHolder.True.setBackgroundColor(getColors().get(position*2));
            viewHolder.False.setBackgroundColor(getColors().get(position*2+1));
            viewHolder.picView.setImageBitmap(figures.get(position));
            viewHolder.question.setText("Picture is called "+questions.get(position)+" ?");
            viewHolder.page.setText((position+1)+"/"+ questions.size()+"");
            holder.setNumberOfQuestions(questions.size()+65);


    }


    /**
     * set up data into recycle view using helper class LoadImage
     * generate initial colors for buttons
     * @param questionsList is the data of TrueFalse being set
     * @param pictures  is list of url of the pictures
     */
    public void setData(List<String> questionsList, List<String> pictures,
                        List<TrueFalseGame> falseGame) throws IOException {
           questions =questionsList;
           trueFalse = falseGame;
           for(String picUrl : pictures){
               new LoadImage().execute(picUrl);
           }
           generateColors(questions.size());

    }

    /**view Holder
     * generate initial list of colors of buttons for all questions
     * List button is 2 times activity questions because each question contains two buttons
     * All colors  initial are set to zero
     * Even index store colors for true  buttons
     * And odd index store  colors for false buttons
     * @param setSize  number of questions set for activity
     */
    private  void generateColors(int setSize){
        int size  = setSize*2;
         for(int i =0 ; i<size; i++)
             colors.add(Color.BLUE);
    }

    /**
     * set new color into colors list at at index
     * @param index where we set the new color
     * @param color new color being set
     */
    private void setColor(int index, int color){
        colors.set(index,color);
    }

    /**
     * @return   list of  of all colors  for all questions
     */
    private  List<Integer> getColors(){
        return  colors;
    }

    /**
     *
     * @return all views that contain answers
     */
    public List<View> getAnswers() {
        return answers;
    }

    /**
     *
     * @return all questions for the activity
     */
    public List<String> getQuestions() {
        return questions;
    }

    /**
     *
     * @return hash map of questions and user final answers
     */
    @Override
    public HashMap<String, String> getUserAnswers() {
        return getUserFinalAnswers();
    }

    /**
     * Get all user  final answers
     * @return user final answers mapped to questions
     */
    private  HashMap<String ,String> getUserFinalAnswers(){
        HashMap<String,String > ans = new HashMap<>();

        int count =0;
        for(int i = 0 ; i < questions.size();i++){
            count++;
            if(colors.get(2*i)==Color.GREEN){
                ans.put(questions.get(i),"true");
            }else if(colors.get(2*i+1)==Color.GREEN){
                ans.put(questions.get(i),"false");
            }else {
                ans.put(questions.get(i),"none");
            }
        }
        return ans;
    }

    /**
     *
     * @return all game correct answer
     */
    @Override
    public HashMap<String, String> getGameAnswers() {
        return gameAnswers();
    }

    /**
     * Generate all the game answers
     * @return all game correct answer
     */
    private  HashMap<String, String> gameAnswers(){
        HashMap<String ,String> ans = new HashMap<>();
        for(TrueFalseGame trueFalseGame: trueFalse){
            String answer = trueFalseGame.getAnswers().
                    substring(trueFalseGame.getAnswers().indexOf(",")+1);
            ans.put(trueFalseGame.getQuestion(),answer.trim().toLowerCase());
        }
        return  ans;
    }

    /**
     * get true false information such id, level and total marks
     * @return concatenated true false game id, level and total marks
     */
    @Override
    public String getGameInformation() {
        String trueFalseGame="";
        for(TrueFalseGame trueFalseGame1: trueFalse){
           trueFalseGame =trueFalseGame1.toString();
           break;
        }
        return trueFalseGame;
    }

    /**
     * @Class  LoadImage  is the class that runs on back ground to help to load images from internet
     * All the images are stored in list and set on a recycle view
     */
    private class LoadImage extends  AsyncTask<String ,Void ,List<Bitmap>>{

        /**
         * Get images using url from database
         * This is done on background
         * All the loaded images are store on list called figures
         * @param strings is the url string which used to generate images
         * @return list of bitmap images
         */
        @Override
        protected List<Bitmap> doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                bitmap = Picasso.with(context).load(strings[0]).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            figures.add(bitmap);
            return figures;
        }

        /**
         * Increment number of item of the recycle view as they are being loaded
         * Notify recycle view about the data changes
         * @param bitmaps is the list of bitmap
         */
        @Override
        protected void onPostExecute(List<Bitmap> bitmaps) {
            super.onPostExecute(bitmaps);
            setSize();
            notifyItemChanged(0,getSize());
        }
    }
    /**
     * set size of the data
     */
    void  setSize(){
        size+= 1;
    }

    /**
     * get size of the data
     * @return size of the current store data
     */
    int getSize(){
        return size;
    }




    /**
     *@Class  Holder is the inner class that contain features of the TruFalse game
     */
     class  Holder  extends RecyclerView.ViewHolder{
            ImageView picView;
            TextView question,page;
            Button  True,False;

            /**
             * constructor to initialise the serial filed of this class
             * @param convertView view contains buttons
             */
            public Holder(@NonNull View convertView) {
                        super(convertView);
                       picView =  convertView.findViewById(R.id.QuestionImageViewID);
                       picView.setOnClickListener(null);
                       question =  convertView.findViewById(R.id.picQuestionTextViewID);
                       question.setOnClickListener(null);
                       page = convertView.findViewById(R.id.page);
                       True =  convertView.findViewById(R.id.trueButton);
                       False =  convertView.findViewById(R.id.falseButton);
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
             * set number of questions for this game
             */
            void setNumberOfQuestions(int value){
                onTruefalse.numberOfQuestions(value);
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
                    int pos  = getLayoutPosition();
                    notifyChanges();
                    if(view.getId()==R.id.trueButton) {
                        onTruefalse.trueButton(view, pos);
                        //.StoreAnswer(answers);
                        setColor(pos*2,buttonColors[onTruefalse.getColor()]);
                        setColor(pos*2+1,buttonColors[0]);

                    }
                    else if(view.getId()==R.id.falseButton) {
                        onTruefalse.falseButton(view, pos);
                        //onTruefalse.StoreAnswer(answers);
                        setColor(pos*2+1,buttonColors[onTruefalse.getColor()]);
                        setColor(pos*2,buttonColors[0]);
                    }else {}

                    notifyDataSetChanged();

                }



                 /**
                 * notify holder about changes
                 * If View id is  not equals to negative one ( clicked in the layout instead of buttons)
                 */
                private void notifyChanges(){
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if((v.getId() !=-1  | v.getId()!=R.id.picQuestionTextViewID | v.getId()!=R.id.picQuestionTextView))
                                notifyItemChanged(holder.getAdapterPosition());
                        }
                    });
                }
            }

        }

}
