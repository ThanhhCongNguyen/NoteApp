package com.example.noteapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.noteapp.dao.NoteDao;
import com.example.noteapp.database.NoteDatabase;
import com.example.noteapp.entity.Note;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getDatabaseInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insertNote(Note note) {
        new Thread(() -> noteDao.insertNote(note)).start();
    }

    public void deleteNote(int id) {
        new Thread(() -> noteDao.deleteNote(id)).start();
    }

    public void updateNote(Note note) {
        new Thread(() -> noteDao.updateNote(note)).start();
    }
}
