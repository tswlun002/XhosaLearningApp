package com.example.wordgame.data_layer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    static ArrayList<String> myArray = new ArrayList<>();

    public static void readFile(String fileName, ArrayList<String> arrayName){
        File myFile = new File(fileName);
        try {
            Scanner inputFile = new Scanner(myFile);
            while (inputFile.hasNextLine()){
                String myLine = inputFile.nextLine();
                System.out.println(myLine);
                arrayName.add(myLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(arrayName.size());
    }
}
