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
    private LayoutInflater inflater;
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
        inflater = LayoutInflater.from(context);
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
            //Toast.makeText(context,  " On 31", Toast.LENGTH_SHORT).show();
            data1 = new HashMap<>(data);
        }
        return y;

    }

    @Override
    public void onViewRecycled(@NonNull Holder holder) {
        super.onViewRecycled(holder);
        //Toast.makeText(context, data.size()+" TableRowList", Toast.LENGTH_SHORT).show();


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

    void setData(HashMap<String, List<String>> material){
        data=material;
        setSize(1);
       notifyItemChanged(0, getSize());


    }
    void setSize(int s) {
        size += s;
    }
    int getSize(){
        return size;
    }

    void addRow( HashMap<String, List<String>>  data ) {

        List<String> column1List = new ArrayList<>();
        List<String> column2List = new ArrayList<>();
        List<String> values = data.get(data.keySet().toArray()[position].toString());
        assert values != null;
        getContent(column1List, column2List, values);
        addRow(column1List, column2List, values);
        column1List.clear();
        column2List.clear();

    }
    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    void addRow(List<String>column1List,List<String>column2List,List<String>values){
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

    @Override
    public Filter getFilter() {
        return filter;
    }

    private  Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            HashMap<String ,List<String>> filter = new HashMap<>();

            if(constraint !=null || constraint.length() !=0 || !constraint.equals("")){
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
            //Toast.makeText(context, filter.size()+" data size here", Toast.LENGTH_SHORT).show();
            FilterResults filterResults = new FilterResults();
            filterResults.values = filter;
            return filterResults;
        }

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
     boolean searchContent(String pattern,HashMap<String ,List<String>> filter){
         int  found =0;
         for(String key1 : data.keySet()) {
             List<String> list = new ArrayList<>();
             getContent(list,data.get(key1));
             for (String searched : list) {
                 if (searched.toLowerCase().equalsIgnoreCase(pattern)) {
                     filter.put(key1, data.get(key1));
                     found ++;
                 }
             }
         }
         if(found >0)
            return  true;
         else
             return false;
     }
    void getContent(List<String>list,List<String>values){
        for(int i =values.size()-1; i>=0; i--){
            list.add(values.get(i).substring(0,values.get(i).indexOf(";")));
        }
    }







}
