package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordgame.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Class helps to inflate content into Listview of WordGameDB
 * Subclass of ArrayAdapter<String>
 */
public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.Holder> implements Filterable{

    /**
     * @serialField heading  list of headings of lessons
     * @serialField lesson  list of lessons
     * @serialField context  of fragment LearnFragment
     */
    private final Context context;
    private final int layout;
    private HashMap<String, List<String> > data;
    private HashMap<String, List<String> > data1;
    private  Holder holder;

    int position,size;
    int count =0;

    /**
     * Constructor of WordGameDB controller to initialise the fields
     * @param context of fragment LearnFragment

     * @param resource  number the layout to be inflated into listview
     */
    public LearnAdapter(@NonNull Context context, int resource) {
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
       LayoutInflater inflater; inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout,parent,false);
        holder = new Holder(view);
        return holder;
    }

    /**
     * number of elements to inflated into listview
     * @return number of the element inflated
     */
    @Override
    public int getItemCount() {
        int y =0;
        if(data!=null)
            y= getSize();
        if(y ==4) {
            data1 = new HashMap<>(data);
        }
        return y;

    }

    /**
     * bind LearnFragment adapter view to holder
     * @param holder for LearnFragment adapter
     * @param position position of each view
     */

    @Override
    public void onBindViewHolder(@NonNull Holder holder, @SuppressLint("RecyclerView") int position) {
       this.position =position;
       if(position< data.size()) {
           holder.descriptionView.setText(data.keySet().toArray()[position].toString());
           addRow(data);

       }
    }

    /**
     * set data for learning
     * @param material is the learning material
     */
    void setData(HashMap<String, List<String>> material){
        data=material;
        setSize(1);
       notifyItemChanged(0, getSize());


    }

    /**
     * set size of the learning
     * @param materialSize size of the learning
     */
    void setSize(int materialSize) {
        size += materialSize;
    }

    /**
     * @return size of learning available
     */
    int getSize(){
        return size;
    }

    /**
     * Add row to store learning material
     * @param data set on the rows
     */
    void addRow( HashMap<String, List<String>>  data ) {

        List<String> column1List = new ArrayList<>();
        List<String> column2List = new ArrayList<>();
        List<String> values = data.get(data.keySet().toArray()[position].toString());
        assert values != null;
        getContent(column1List, column2List, values);
        addRow(column1List, column2List);
        column1List.clear();
        column2List.clear();

    }

    /**
     * On the added row ,sets data
     * First row label columns
     * then on other row set data
     * @param column1List contains data of the first column
     * @param column2List contains data of the second column
     */
    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    void addRow(List<String>column1List,List<String>column2List){
        for (int index =0; index<column1List.size();index++) {
            for (int i = index+1; i < holder.tableLayout.getChildCount(); i++) {
                TableRow row = (TableRow) holder.tableLayout.getChildAt(i);
                if (i -1 == 0) {
                    TableRow row1 = (TableRow) holder.tableLayout.getChildAt(0);
                    View view1 = row1.getChildAt(0);
                    view1.setBackgroundColor(Color.GREEN);
                    ((TextView) view1).setText("Object");
                    View view2 = row1.getChildAt(1);
                    view2.setBackgroundColor(Color.GREEN);
                    ((TextView) view2).setText("Translation");
                }
                for (int j = 0; j < row.getChildCount(); j++) {
                    View view = row.getChildAt(j);

                    if (j == 0) {
                        String dataLine  =column1List.get(index);
                        setText(dataLine,view,20);
                    }
                    else {
                        String dataLine  =column2List.get(index);
                         setText(dataLine,view,40);
                    }

                }
                break;

            }
        }
    }

    /**
     * method size data to equal with row size and columns size
     * @param data set of data being sized
     * @param view where data is being set
     * @param divider value that used to size data to fit into row and column
     */
    private void  setText(String data, View view,int divider){
        String dataConcatenated ="";
        String dataLine  =data;
        do {
            int len = dataLine.length();

            String newData ="";
            if(len <divider)
                newData = dataLine.substring(0, len);
            else
                newData = dataLine.substring(0,(int)len/2);
            dataConcatenated +=newData+"\n";
            if(newData.length() !=0)
                dataLine = dataLine.substring(newData.length());
            else
                dataLine ="";


        }while (!(dataLine.length() ==0));


        ((TextView) view).setText(dataConcatenated);

    }

    /**
     * Store data in the columns lists
     * @param column1 list store data for first column
     * @param column2 list store data for second column
     * @param content list data that is split into columns
     */
    private void getContent(List<String> column1 ,List<String> column2,List<String> content){
        for(int i =content.size()-1; i>=0; i--){
            if(content.get(i).length()!= 0){
                column1.add(content.get(i).substring(0,content.get(i).indexOf(";")));
                column2.add(content.get(i).substring(content.get(i).indexOf(";")+1));
            }

        }
    }




    /**
     * Inner class that contain features of the element to be inflated into listview
     */
     class  Holder extends RecyclerView.ViewHolder {
        TableLayout tableLayout;
        TextView descriptionView;

        /**
         * constructor to initialise serial fields of Holder
         * @param itemView LearnFragment adapter view
         */
        public Holder(@NonNull View itemView) {
            super(itemView);
            tableLayout = itemView.findViewById(R.id.tablelayoutID);
            descriptionView = itemView.findViewById(R.id.textViewAdaoterID);
            makeTable();
        }

        /**
         * Creates table that will store data
         * Creates table with 6 rows
         */
        @SuppressLint("ResourceType")
        void makeTable(){

            for(int i=0; i<6;i++){
                TextView column1 = new TextView(context);
                column1.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                column1.setKeyListener(null);
                column1.setSingleLine(false);
                TextView column2 = new TextView(context);
                column2.setClickable(false);
                column2.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                column2.setKeyListener(null);
                column2.setSingleLine(false);
                TableRow tableRow = new TableRow(context);
                column1.setTextColor(Color.BLACK);
                column1.setPadding(20, 5, TableLayout.LayoutParams.MATCH_PARENT, 25);
                column2.setTextColor(Color.BLACK);
                column2.setPadding(20, 5, TableLayout.LayoutParams.MATCH_PARENT, 25);
                tableRow.addView(column1);
                tableRow.addView(column2);
                tableLayout.addView(tableRow);
            }
        }


    }

    /**
     * Method implement search
     * Used to filter data when word is searched
     * @return filtered data
     */
    @Override
    public Filter getFilter() {
        return filter;
    }

    /**
     * Instance that actual implements filtering when filter is invoked
     */
    private final Filter filter = new Filter() {
        /**
         * search the given word first on the headings of the learning material
         * if not found , word searched through content on the first column
         * @param constraint is the sequence of the word being searched
         * @return results of the searched word
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            HashMap<String ,List<String>> filter = new HashMap<>();

            if(constraint !=null  || !constraint.toString().trim().equals("")){
                String pattern = constraint.toString().toLowerCase().trim();
                boolean inHeading  =searchHeading(pattern,filter);
                boolean inContent =false;
                if(! inHeading){
                    inContent =searchContent(pattern,filter);
                }
                if(! inContent){
                    filter.putAll(data);
                }

            }
           else {
                 filter.putAll(data);
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filter;
            return filterResults;
        }

        /**
         * publish filter results of the searched word into data and into interface by notifying data
         * changes
         * If searched word in null or zero length, not data changes
         * Else change our data to  results
         * @param constraint is searched word
         * @param results is the results of word was searched
         */
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if(((HashMap) results.values).size() !=data.size()) {
                data.clear();
                data.putAll((HashMap) results.values);
                size = data.size();
               notifyDataSetChanged();
               count=0;
            }else {
                setSize(-data.size());
                notifyDataSetChanged();
                data.putAll(data1);
                setSize(data.size());
                notifyDataSetChanged();
            }


        }
    };

    /**
     * Helper method to search the word on the headings
     * @param pattern word being searched
     * @param filter hash map to store filter results
     * @return true if found else false
     */
     boolean searchHeading(String pattern,HashMap<String ,List<String>> filter ){
         boolean found = false;
        for(String key : data.keySet()){
            if(key.toLowerCase().trim().equalsIgnoreCase(pattern)){
                filter.put(key,data.get(key));
                found=true;
                break;
            }
        }
        return  found;
     }

    /**
     * Helper method to search the word on the content (first column)
     * @param pattern word being searched
     * @param filter hash map to store filter results
     * @return true if found else false
     */
     boolean searchContent(String pattern,HashMap<String ,List<String>> filter){
         int  found =0;
         for(String key1 : data.keySet()) {
             List<String> list = new ArrayList<>();
             getContent(list, Objects.requireNonNull(data.get(key1)));
             for (String searched : list) {
                 if (searched.toLowerCase().equalsIgnoreCase(pattern)) {
                     filter.put(key1, data.get(key1));
                     found ++;
                 }
             }
         }
         return found > 0;
     }

    /**
     * get content of the first column
     * @param list to store content of the first column
     * @param values is the content used to filter first column content
     */
    void getContent(List<String>list,List<String>values){
        for(int i =values.size()-1; i>=0; i--){
            list.add(values.get(i).substring(0,values.get(i).indexOf(";")));
        }
    }







}
