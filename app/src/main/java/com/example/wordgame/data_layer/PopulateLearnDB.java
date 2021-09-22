package com.example.wordgame.data_layer;

import android.os.AsyncTask;

import com.example.wordgame.model_layer.Learn;


 class PopulateLearnDB extends AsyncTask<Void,Void,Void> {
    private final LearnDao learnDao ;

    protected PopulateLearnDB(WordGameDB wordGameDB){

        learnDao= wordGameDB.learnDao();

    }


    @Override
    protected Void doInBackground(Void... voids) {

        String instructions = "Read material before attempting game";
        String[] section = {
                "Numbers Or Izibalo",
                "Vowels",
                "Consonants Or Amaqabane",
                "Clicks Or Izandi"
        };

        String[] content1 = {"0; unothi", "1; enye","2; zimbini"};
        String[] content2 = {"a; like a in hard ", "e; like e in red",
                "i; like ee in seen","o; like a in all",
                "u; like oo in moon"};
        String[] content3 = {"a; like a in hard ", "e; like e in red",
                "i; like ee in seen","o; like a in all",
                "u; like oo in moon"};
        String[] content4 = {"c; Place your tongue at the back of the teeth and suck in, like when expressing annoyance. ",
                "q; Place your tongue on the roof of the mouth and suck in, like imitating a clock's ticking.",
                "x; Place your tongue on your upper right jaw and pull it down, as if urging on a horse."};

        for (String item : content1) learnDao.insert(new Learn(1, section[0], item, instructions));
        for (String value : content2) learnDao.insert(new Learn(1, section[1], value, instructions));
        for (String s : content3) learnDao.insert(new Learn(1, section[2], s, instructions));
        for (String s : content4) learnDao.insert(new Learn(1, section[3], s, instructions));

        return null;
    }
}