package com.example.wordgame.presentation_layer;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordgame.R;
import com.example.wordgame.model_layer.MultipleChoice;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MultipleChoiceAdapter extends RecyclerView.Adapter<MultipleChoiceAdapter.Holder>
        implements OnExtractResults  {

    @SuppressLint("StaticFieldLeak")
    private static Context context = null;
    private final int layout;
    private static OnMultipleChoice onMCQ ;
    private  OnScroll onScroll;
    private  final  int [] buttonColors = {Color.BLUE,Color.GREEN};
    private final List<Integer> colors = new ArrayList<>();
    private final List<String> answers = new ArrayList<>();
    private   Holder holder;
    private HashMap<String ,List<String>> Data = new HashMap<>();
    private  int size =0;
    private  RecyclerView recyclerView;
    private List<MultipleChoice>multipleChoices;

    /**
     * construct to initialise the serial fields
     * @param context is the context that inflate recycleview
     * @param resource id of the adapter to be inflate on listview
     */
    public MultipleChoiceAdapter(@NonNull Context context, int resource) {
        MultipleChoiceAdapter.context = context;
        this.layout = resource;
        HandleMQCButtons handleMQCButtons = new HandleMQCButtons();
        onScroll= handleMQCButtons;
        onMCQ=handleMQCButtons;
        //generateColors(question.length);
    }
    /**
     * get view of the listview
     * @param parent      is the view of fragment Multiple choice
     * @return view of listview
     */
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout,parent,false);

        holder=  new MultipleChoiceAdapter.Holder( view);
        return  holder;
    }

    /**
     * set questions , choices(answers) and question number
     * @param viewHolder holds the view ( button, texts)
     * @param position position of the item
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holder viewHolder, @SuppressLint("RecyclerView") int position) {
        Object[] questionsData = Data.keySet().toArray();
        String key = questionsData[position]+"";
        List<String> choices  = Data.get(key);
        viewHolder.question.setText(key+" is called ?");
        assert choices != null;
        viewHolder.choice1.setText(choices.get(0));
        viewHolder.choice2.setText(choices.get(1));
        viewHolder.choice3.setText(choices.get(2));
        viewHolder.choice4.setText(choices.get(3));
        viewHolder.choice1.setBackgroundColor(getColors().get(position*4));
        viewHolder.choice2.setBackgroundColor(getColors().get(position*4+1));
        viewHolder.choice3.setBackgroundColor(getColors().get(position*4+2));
        viewHolder.choice4.setBackgroundColor(getColors().get(position*4+3));
        viewHolder.page.setText((position+1)+"/"+questionsData.length);
        holder.setNumberOfQuestions(questionsData.length+65);
    }

    /**
     * set up data into recycle view using helper class LoadImage
     * generate initial colors for buttons
     * @param data  is list of url of the pictures
     */
    public void setData(HashMap<String ,List<String>> data, List<MultipleChoice>multipleChoiceList){
        Data = data;
        multipleChoices=multipleChoiceList;
        setSize( Data.size());
        generateColors(getSize());
        notifyItemMoved(0, getSize());

    }

    /**
     * set size of the data
     */
    void  setSize(int number){
        size=number;
    }

    /**
     * get size of the data
     * @return size of the current store data
     */
    int getSize(){
        return size;
    }


    /**
     * number of elements to inflated into listview
     *
     * @return number of the element inflated
     */
    @Override
    public int getItemCount() {
        return getSize();
    }

    /**viewHolder
     * generate initial list of colors of buttons for all questions
     * List button is 4 times activity questions because each question contains four buttons
     * All colors  initial are set to zero
     * Even index store colors for true  buttons
     * And odd index store  colors for false buttons
     * @param setSize  number of questions set for activity
     */
    private  void generateColors(int setSize){
        int size  = setSize*4;
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
    private List<Integer> getColors(){
        return  colors;
    }

    void setRecycleView( RecyclerView recyclerView1){
            recyclerView =recyclerView1;
    }
    RecyclerView getRecycleView(){
        return recyclerView;
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
     *
     * @return user final answers mapped to questions
     */
    private  HashMap<String ,String> getUserFinalAnswers(){
        HashMap<String,String > ans = new HashMap<>();
        Object[] questionsData = Data.keySet().toArray();
        List<String> answer = new ArrayList<>();
        String key="";
        for(int i = 0 ; i < questionsData.length;i++) {
            key = questionsData[i] + "";
            answer.addAll(Objects.requireNonNull(Data.get(key)));
        }

        for(int i = 0 ; i < questionsData.length;i++) {
            if(colors.get(4*i)==Color.GREEN){

                ans.put(questionsData[i]+"".trim().toLowerCase(),answer.get(4*i)+"");
            }
            else if(colors.get(4*i+1)==Color.GREEN){

                ans.put(questionsData[i]+"".trim().toLowerCase(),answer.get(4*i+1)+"");
            }
            else if(colors.get(4*i+2)==Color.GREEN){

                ans.put(questionsData[i]+"".trim().toLowerCase(),answer.get(4*i+2)+"");
            }
            else if(colors.get(4*i+3)==Color.GREEN){

                ans.put(questionsData[i]+"".trim().toLowerCase(),answer.get(4*i+3)+"");
            }
            else {

                ans.put(questionsData[i]+"".trim().toLowerCase(),"none");
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
        for(MultipleChoice multipleChoice: multipleChoices){
            String answer = multipleChoice.getAnswer().trim().toLowerCase();
            String question =multipleChoice.getQuestion().trim().toLowerCase();
            ans.put(question,answer);
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
        for(MultipleChoice multipleChoice: multipleChoices){
            trueFalseGame =multipleChoice.toString();
            break;
        }
        return trueFalseGame;
    }

    /**
     * Inner class that contain features of the element to be inflated into listview
     */
    class  Holder extends RecyclerView.ViewHolder{
        TextView page;
        TextView question;
        Button choice3,choice1,choice2,choice4, nextQ;
        public Holder(@NonNull View convertView) {
            super(convertView);
            question = convertView.findViewById(R.id.transQuestionTextView);
            choice1 = convertView.findViewById(R.id.answer1textView);
            choice2 = convertView.findViewById(R.id.answer2TextView);
            choice3 = convertView.findViewById(R.id.answer3textView);
            choice4 = convertView.findViewById(R.id.answer4TextView);
            nextQ   = convertView.findViewById(R.id.moreQuestionID);
            page = convertView.findViewById(R.id.page);
            onMCQ.onMultipleChoice(choice1,choice2,choice3,choice4);

            handleRecycleView();


        }

        /**
         * Helper method to handle click listeners
         */
        private void handleRecycleView (){
            //HandleClickButtons handleClickButtons = );
            choice1.setOnClickListener(new HandleClickButtons(getSize()));
            choice2.setOnClickListener(new HandleClickButtons(getSize()));
            choice3.setOnClickListener(new HandleClickButtons(getSize()));
            choice4.setOnClickListener(new HandleClickButtons(getSize()));
            nextQ.setOnClickListener(new HandleClickButtons(getSize()));
        }
        /**
         * set number of questions for this game
         */
        void setNumberOfQuestions(int value){
            onMCQ.numberOfQuestions(value);
        }

        /**
         * @Class  HandleClickButtons handle MCQ button clicks by implement click listeners
         */
        private   class HandleClickButtons implements View.OnClickListener{
            private final int numberOfQuestions;

            /**
             * constructor to initialise  serial fileds
             * @param numberQ number of the question
             */
            HandleClickButtons(int numberQ){
                numberOfQuestions =numberQ;
            }

            /**
             * notify changes
             * handles the button clicks of multiple choice
             * @param v view clicked by MCQ buttons
             */
            @Override
            public void onClick(View v) {
                notifyChanges();
                final  int [] buttonColors = {Color.BLUE,Color.GREEN};
                int pos = getLayoutPosition();
                if (v.getId() == R.id.moreQuestionID) {
                    nextQ.setVisibility(View.VISIBLE);
                    onScroll.scrollDown(getLayoutPosition(),getRecycleView());


                }
                else {
                    if(v.getId()==R.id.answer1textView ){
                        onMCQ.choices(v, pos);
                        onMCQ.StoreAnswer(answers);
                        setColor(pos*4,buttonColors[onMCQ.getColor()]);
                        setColor(pos*4+1,buttonColors[0]);
                        setColor(pos*4+2,buttonColors[0]);
                        setColor(pos*4+3,buttonColors[0]);

                    }
                    else if(v.getId()==R.id.answer2TextView){
                        onMCQ.choices(v, pos);
                        onMCQ.StoreAnswer(answers);
                        setColor(pos*4,buttonColors[0]);
                        setColor(pos*4+1,buttonColors[onMCQ.getColor()]);
                        setColor(pos*4+2,buttonColors[0]);
                        setColor(pos*4+3,buttonColors[0]);
                    }
                    else if(v.getId()==R.id.answer3textView){

                        onMCQ.choices(v, pos);
                        onMCQ.StoreAnswer(answers);
                        setColor(pos*4,buttonColors[0]);
                        setColor(pos*4+1,buttonColors[0]);
                        setColor(pos*4+2,buttonColors[onMCQ.getColor()]);
                        setColor(pos*4+3,buttonColors[0]);
                    }
                    else if(v.getId()==R.id.answer4TextView) {
                        onMCQ.choices(v, pos);
                        onMCQ.StoreAnswer(answers);
                        setColor(pos*4,buttonColors[0]);
                        setColor(pos*4+1,buttonColors[0]);
                        setColor(pos*4+2,buttonColors[0]);
                        setColor(pos*4+3,buttonColors[onMCQ.getColor()]);
                    }else
                    {}
                    notifyDataSetChanged();
                }
            }

            /**
             * notify holder about changes
             * If View id is  not equals to negative one ( clicked in the layout instead of buttons)
             */
            private void notifyChanges(){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(! (v.getId() ==RecyclerView.NO_POSITION))
                            notifyItemChanged(holder.getAdapterPosition());
                    }
                });
            }

        }
    }
}


