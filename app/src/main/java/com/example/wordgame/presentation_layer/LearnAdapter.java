package com.example.wordgame.presentation_layer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
        Toast.makeText(context, y+" data size", Toast.LENGTH_SHORT).show();
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
        holder.descriptionView.setText(data.keySet().toArray()[position].toString());
        addRow(data);
        Toast.makeText(context, data.size()+" at binding", Toast.LENGTH_SHORT).show();

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
                    ((EditText) view1).setText("Object");
                    View view2 = row1.getChildAt(1);
                    view2.setBackgroundColor(Color.GREEN);
                    ((EditText) view2).setText("Translation");
                }
                for (int j = 0; j < row.getChildCount(); j++) {
                    View view = row.getChildAt(j);

                    if (j == 0)
                            ((EditText) view).setText(column1List.get(index));
                    else
                            ((EditText) view).setText(column2List.get(index));

                }
                break;

            }
        }
    }
    private void getContent(List<String> column1 ,List<String> column2,List<String> content){
        for(int i =content.size()-1; i>=0; i--){
            column1.add(content.get(i).substring(0,content.get(i).indexOf(";")));
            column2.add(content.get(i).substring(content.get(i).indexOf(";")+1));
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
                EditText column1 = new EditText(context);
                column1.setClickable(false);
                column1.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                column1.setKeyListener(null);
                EditText column2 = new EditText(context);
                column2.setClickable(false);
                column2.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                column2.setKeyListener(null);
                TableRow tableRow = new TableRow(context);
                column1.setBackgroundResource(R.drawable.textbackground);
                column1.setTextColor(Color.BLACK);
                column1.setPadding(20, 5, 200, 25);
                column2.setBackgroundResource(R.drawable.textbackground);
                column2.setTextColor(Color.BLACK);
                column2.setPadding(20, 5, 200, 25);
                tableRow.addView(column1);
                tableRow.addView(column2);
                TableRowList.add(tableRow);
                tableLayout.addView(tableRow);
            }
        }


    }





}
