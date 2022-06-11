package com.example.noteapp.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "NoteDatabase")
public class Note {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String noteTitle;
    public String noteSubtitle;
    public String noteDate;
    public String noteContent;
    public String notePriority;
}
