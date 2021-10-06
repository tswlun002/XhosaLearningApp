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
    private final  HashMap<String, List<String> > data = new HashMap<>();
    private HashMap<String, List<String> > data1;
    private HashMap<Integer, List<String> > data2 = new HashMap<>();
    List<String> column1List ;
    List<String> column2List ;
    private List<String> columnData;
    private  Holder holder;
    private   Object[] keys;
    int size=0;
    boolean dataSet =false;

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
            y= data.size();
        return y;

    }

    /**
     * bind LearnFragment adapter view to holder
     * @param holder for LearnFragment adapter
     * @param position position of each view
     */

    @Override
    public void onBindViewHolder(@NonNull Holder holder, @SuppressLint("RecyclerView") int position) {
        int size = position+1;
        String key  = getHeadings()[position]+"";
        holder.setIsRecyclable(false);
        holder.pageNumber.setText((size)+"/"+getSize());
        addRow(data2.get(position*2), data2.get(position*2+1),key,holder);
    }
    /**
     * On the added row ,sets data
     * First row label columns
     * then on other row set data
     * @param column1List contains data of the first column
     * @param column2List contains data of the second column
     */
    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    void addRow(List<String>column1List,List<String>column2List,String key,Holder holder){
       // Toast.makeText(context,column1List.size()+" | "+key,Toast.LENGTH_SHORT).show();
        holder.descriptionView.setText(key);
        for (int index =0; index<column1List.size();index++) {
            int i = index+1;
            if( i < holder.tableLayout.getChildCount()){
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

    void generateHeadings(){
        keys=getData().keySet().toArray();
    }

    Object[] getHeadings(){
        return keys;
    }

    void setAllData(HashMap<String , List<String>>data1){
        data.putAll(data1);
    }
    HashMap<String , List<String>> getData(){
        return data;
    }



    /**
     * set data for learning
     * @param material is the learning material
     */
    void setData(HashMap<String, List<String>> material){
        setAllData(material);
        generateHeadings();
        int count =0;
        for (Object key : material.keySet().toArray()) {
            column1List= new ArrayList<>();
            column2List= new ArrayList<>();
            getContent(column1List,column2List,material.get(key+""));
            data2.put(count*2,column1List);
            data2.put(count*2+1,column2List);
            count++;

        }
        setSize(data.size());
        notifyDataSetChanged();
        if(!dataSet) {
            data1 = new HashMap<>(data);
            dataSet=true;
        }
        //notifyItemRangeInserted(0,getSize());


    }

    /**
     * set size of the learning
     * @param materialSize size of the learning
     */
    void setSize(int materialSize) {
        size = materialSize;
    }

    /**
     * @return size of learning available
     */
    int getSize(){
        return size;
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
                column1.add(content.get(i).substring(0, content.get(i).indexOf(";")));
                column2.add(content.get(i).substring(content.get(i).indexOf(";") + 1));

            }

        }
    }




    /**
     * Inner class that contain features of the element to be inflated into listview
     */
     class  Holder extends RecyclerView.ViewHolder {
        TableLayout tableLayout;
        TextView descriptionView,pageNumber;

        /**
         * constructor to initialise serial fields of Holder
         * @param itemView LearnFragment adapter view
         */
        public Holder(@NonNull View itemView) {
            super(itemView);
            tableLayout = itemView.findViewById(R.id.tablelayoutID);
            descriptionView = itemView.findViewById(R.id.textViewAdaoterID);
            pageNumber= itemView.findViewById(R.id.pageNo);
            makeTable();
        }

        /**
         * Creates table that will store data
         * Creates table with 6 rows
         */
        @SuppressLint("ResourceType")
        void makeTable(){

            for(int i=0; i<10;i++){
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
            Toast.makeText(context,constraint.toString()+" here ",Toast.LENGTH_SHORT).show();
            boolean found =false;
            if(constraint !=null || constraint.length()!=0){

                String pattern = constraint.toString().toLowerCase().trim();
                boolean inHeading  =searchHeading(pattern,filter);
                boolean inContent =false;
                boolean inHeadins  = false;
                if(! inHeading){
                }
                if(inHeading==true){
                     found=true;
                     inHeading=true;
                     Toast.makeText(context," hayibo",filter.size());
                }


            }
           else if(!found ) {
                 filter.putAll(data1);
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
            //if(((HashMap) results.values).size() !=0) {
                data.clear();
                data2.clear();
                setData((HashMap) results.values);
                Toast.makeText(context, getItemCount()+" count",Toast.LENGTH_SHORT).show();
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
                //break;
            }
        }
        return  found;
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
