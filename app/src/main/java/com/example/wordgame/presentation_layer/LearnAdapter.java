package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Class helps to inflate content into Listview of LearnDB
 * Subclass of ArrayAdapter<String>
 */
public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.Holder> {

    /**
     * @serialField heading  list of headings of lessons
     * @serialField lesson  list of lessons
     * @serialField context  of fragment LearnFragment
     */
    private final Context context;
    private final int layout;
    HashMap<String, List<String> > data;
    private  Holder holder;
    LayoutInflater inflater;
    TableLayout tableLayout1 ;
    private List<TableRow> TableRowList = new ArrayList<>();
    int position,size;

    /**
     * Constructor of LearnDB controller to initialise the fields
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

        this.position=position;
        holder.descriptionView.setText(data.keySet().toArray()[position].toString());
        addRow(data);
        this.holder=holder;

    }

    void setData(HashMap<String, List<String>> material){
        data=material;
        setSize(1);
        notifyDataSetChanged();
        //holder.addRow(data);

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

                    if (j == 0)
                            ((TextView) view).setText(column1List.get(index));
                    else
                            ((TextView) view).setText(column2List.get(index));
                        //iew.setBackgroundColor(R.color.green);
                        Toast.makeText(context, ((TextView) view).getText() + " TableRowList", Toast.LENGTH_SHORT).show();

                }
                break;

            }
        }
    }
    private void getContent(List<String> column1 ,List<String> column2,List<String> content){
        for(String content1: content){
            column1.add(content1.substring(0,content1.indexOf(";")));
            column2.add(content1.substring(content1.indexOf(";")+1));
        }
    }

   void setSize(int s) {
       size += s;
   }
   int getSize(){
        return size;
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

            for(int i=0; i<5;i++){
                TextView column1 = new TextView(context);
                //column1.setId(1023);
                TextView column2 = new TextView(context);
                //column1.setId(1024);
                TableRow tableRow = new TableRow(context);
                column1.setText(i+"");
                column1.setBackgroundResource(R.drawable.textbackground);
                column1.setTextColor(Color.BLACK);
                column1.setPadding(5, 5, 200, 25);
                column2.setText(i+"");
                column2.setBackgroundResource(R.drawable.textbackground);
                column2.setTextColor(Color.BLACK);
                column2.setPadding(5, 5, 500, 25);
                tableRow.addView(column1);
                tableRow.addView(column2);
                TableRowList.add(tableRow);
                tableLayout.addView(tableRow);
            }
        }


    }





}
