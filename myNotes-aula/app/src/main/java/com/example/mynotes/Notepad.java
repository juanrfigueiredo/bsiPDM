package com.example.mynotes;

import java.util.ArrayList;
import java.util.List;

public class Notepad {
    private List<Note> notes;

    public Notepad() {
        this.notes = new ArrayList<>();
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void add(Note note){
        this.notes.add(note);
    }
}
