package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordgame.R;
import com.example.wordgame.model_layer.TranslationGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class TranslationAdapter extends RecyclerView.Adapter<TranslationAdapter.Holder> implements OnExtractResults {

    private final List<String> questions = new ArrayList<>();
    private final List<Integer> totalNumberOfHints = new ArrayList<>();
    private final HashMap<String, String[]> hintsData = new HashMap<>();
    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final int  layout ;
    private  OnHints onHints;
    private   LayoutInflater inflater;
    private   Holder holder;
    private int size=0;
    private final List<String>userEditText  = new ArrayList<>();
    private List<TranslationGame> translationGame;


    public TranslationAdapter(@NonNull Context context, int resource) {
        this.context=context;
        this.layout=resource;
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
       holder =  new Holder(view);
       return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holder viewHolder, int position) {
        viewHolder.descriptionView.setText(questions.get(position));
        viewHolder.pageNumber.setText((position+1)+"/"+getSize());
        viewHolder.numberHints.setText("No of hints: " + totalNumberOfHints.get(position));
        viewHolder.answerEditText.setText(userEditText.get(position));



    }

    void setData(HashMap<String,String> data,List<TranslationGame>translationGameList){
        translationGame =translationGameList;
        for(Object key: data.keySet().toArray()){
            questions.add(key.toString());
            String [] list = data.get(key.toString()).split(",");
            hintsData.put(key.toString(),list);
        }
        setSize(data.size());
        generateTotalHints();
        initEditText();
        notifyDataSetChanged();

    }
    private void generateTotalHints(){
        for (String question : questions) {
            int size = Objects.requireNonNull(hintsData.get(question)).length;
            totalNumberOfHints.add(size);
        }
    }

    private void initEditText()
    {
        for(int i=0; i<getSize();i++){
            userEditText.add(0,".");
        }
    }
    void setUserEditText(int pos , String text)
    {
        userEditText.set(pos,text);
    }

    void setSize(int value){
        size = value;
    }
    int getSize(){
        return size;
    }

    /**
     * number of elements to inflated into listview
     * @return number of the element inflated
     */
    @Override
    public int getItemCount() {
        return getSize() ;
    }

    @Override
    public HashMap<String, String> getUserAnswers() {

        HashMap<String ,String> hash = new HashMap<>();
        int count=0;
        for(String answer :userEditText) {
            if(!(answer.trim().length() <= 1)) {
                hash.put(questions.get(count).trim().toLowerCase(),
                        answer.trim().toUpperCase());
                count++;
            }
            else{
                 hash.put(questions.get(count),"none");
                 count++;
            }

        }
        return hash;
    }

    @Override
    public HashMap<String, String> getGameAnswers() {
        HashMap<String ,String> hash = new HashMap<>();
        for(TranslationGame translationGame1:translationGame) {
            String ans = translationGame1.getAnswers().trim().toUpperCase();
            String que =translationGame1.getQuestion().trim().toLowerCase();
            hash.put(que, ans);
        }

        return hash;
    }

    @Override
    public String getGameInformation() {
        String infor ="";
        for ( TranslationGame translationGame:translationGame) {
            infor = translationGame.toString();
        }
        return infor;

    }


    /**
     * Inner class that contain features of the element to be inflated into listview
     */
     class  Holder extends  RecyclerView.ViewHolder{
        TextView descriptionView,pageNumber, numberHints;
        Button hints;
        EditText answerEditText;
        public Holder(@NonNull View itemView) {
            super(itemView);

            descriptionView = itemView.findViewById(R.id.transQuestionTextView);
            pageNumber = itemView.findViewById(R.id.pageNo);
            hints = itemView.findViewById(R.id.hintButton);
            numberHints = itemView.findViewById(R.id.noHintsTextView);
            answerEditText = itemView.findViewById(R.id.transAnswerEditText);
            numberHints.setClickable(false);
            handleEditText();
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
                    int position = getLayoutPosition();
                    String key = questions.get(position);
                    onHints.onRequestHint(inflater,view,totalNumberOfHints.get(position),
                            position,hintsData.get(key));
                    onHints.updateNumberHints(totalNumberOfHints,position);
                    notifyDataSetChanged();
                }
            });

        }

        void handleEditText(){
            final StringBuilder[] stringBuilder = new StringBuilder[1];
            answerEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    stringBuilder[0] = new StringBuilder();
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                       if(s!=null){
                           stringBuilder[0].append(s);
                       }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    setUserEditText(getLayoutPosition(),stringBuilder[0].toString());

                }

            });
        }


        /**
         * notify holder about changes
         * If View id is  not equals to negative one ( clicked in the layout instead of buttons)
         */
        private void notifyChanges(){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(! (v.getId() ==-1))
                        notifyItemChanged(holder.getAdapterPosition());
                }
            });
        }
    }


}
