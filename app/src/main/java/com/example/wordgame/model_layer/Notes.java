package com.example.wordgame.model_layer;

import androidx.room.Entity;

@Entity
public class Notes {
    private int notesid;
    private  String section;
    private  int level;

    public int getNotesid() {
        return notesid;
    }

    public void setNotesid(int notesId, String section, int level) {
        this.notesid = notesId;
        this.level=level;
        this.section=section;
    }

    public String getSection() {
        return section;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "notesId=" + notesid +
                ", section='" + section + '\'' +
                ", level=" + level +
                '}';
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Notes(int notesid) {
        this.notesid = notesid;
    }
}
