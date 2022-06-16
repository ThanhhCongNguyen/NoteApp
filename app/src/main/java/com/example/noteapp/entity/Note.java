package com.example.noteapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.noteapp.utils.Priority;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "NoteDatabase")
public class Note implements Serializable {

    @NotNull
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "noteTitle")
    public String noteTitle;

    @ColumnInfo(name = "noteDate")
    public String noteDate;

    @ColumnInfo(name = "noteContent")
    public String noteContent;

    @ColumnInfo(name = "notePriority")
    public Priority notePriority;

    public Note(String noteTitle, String noteDate, String noteContent, Priority notePriority) {
        this.noteTitle = noteTitle;
        this.noteDate = noteDate;
        this.noteContent = noteContent;
        this.notePriority = notePriority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public Priority getNotePriority() {
        return notePriority;
    }

    public void setNotePriority(Priority notePriority) {
        this.notePriority = notePriority;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteDate='" + noteDate + '\'' +
                ", noteContent='" + noteContent + '\'' +
                ", notePriority=" + notePriority +
                '}';
    }
}
