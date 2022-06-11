package com.example.noteapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.noteapp.dao.NoteDao;
import com.example.noteapp.database.NoteDatabase;
import com.example.noteapp.entity.Note;

import java.util.List;

public class NoteRepository {

    public NoteDao noteDao;
    public LiveData<List<Note>> getAllNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getDatabaseInstance(application);
        noteDao = database.noteDao();
        getAllNotes = noteDao.getAllNotes();
    }

    public void insertNote(Note note){
        noteDao.insertNote(note);
    }

    public void deleteNote(Note note){
        noteDao.deleteNote(note);
    }

    public void updateNote(Note note){
        noteDao.updateNote(note);
    }

}
