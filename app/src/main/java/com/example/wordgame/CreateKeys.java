package com.example.wordgame;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public  abstract class CreateKeys {

    private  String[] letter ;
    private HashMap<String ,View>views ;
    public CreateKeys(String[] list,HashMap<String ,View>views){
        this.views=views;
        this.letter=list;

    }

    /**
     * add view into views when user play game
     * When user click button, clicked view is added
     * if view  was not clicked before, change background color to green
     * Else remove view  from views and set view background color to blue
     * Button is identified by keys,
     * key = position concatenate with a to d
     * @param view clicked view
     */
    @SuppressLint("ResourceAsColor")
    public void  addView(View view, int pos){
        if(views.containsValue(view)) {
            String key ="";
            view.setBackgroundColor(Color.BLUE);
            for (String key1 : views.keySet()){
                if(views.get(key1)==view) {
                    key = key1;
                    break;
                }
            }
            views.remove(key);

        }
        else {
            String key = makeKey(pos);
            views.put(key,view);
            view.setBackgroundColor(Color.GREEN);
            List<String> blueKeys = new ArrayList<>();
            for (String key1 : views.keySet()){
                try {
                    if(Integer.parseInt(key1.substring(0,1).trim()) ==pos & !key1.equalsIgnoreCase(key)) {
                        blueKeys.add(key1);
                        Objects.requireNonNull(views.get(key1)).setBackgroundColor(Color.BLUE);
                    }
                }
                catch (Exception e){
                    Toast.makeText(view.getContext(), key1.substring(0,1),Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
            remove(views, pos, view, blueKeys);

            Toast.makeText(view.getContext(), key+""+views.size(),key.length()).show();

        }
    }

    /**
     * create keys for buttons
     * @param pos  is the position of the view
     * @return key = position + letters from a-f
     */
    private  String makeKey(int pos){
        String key =pos+letter[0];
        for (String key1 : views.keySet()) {
            if (key1.equalsIgnoreCase(pos + "a"))
                key = pos + letter[1];
            else if (key1.equalsIgnoreCase(pos + "b"))
                key = pos + letter[2];
            else if (key1.equalsIgnoreCase(pos + "c"))
                key = pos + letter[3];
            else if (key1.equalsIgnoreCase(pos + "d"))
                key = pos + letter[4];
            else if (key1.equalsIgnoreCase(pos + "e"))
                key = pos + letter[5];
        }
        return  key;
    }

    private void remove(HashMap<String ,View> views , int pos, View view, List<String> blueKeys){
        for (int i=0; i<blueKeys.size(); i++){
            String key1 = blueKeys.get(i);
            try {
                if(Integer.parseInt(key1.substring(0,1).trim()) ==pos) {
                    Toast.makeText(view.getContext(), key1+""+views.size(),key1.length()).show();
                    views.remove(key1);

                }

            }
            catch (Exception e){
                Toast.makeText(view.getContext(), key1.substring(0,1),Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }

    }
}
