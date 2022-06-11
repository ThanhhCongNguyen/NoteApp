package com.example.noteapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.noteapp.entity.Note;
import com.example.noteapp.repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    public NoteRepository noteRepository;
    public LiveData<List<Note>> getAllNotes;

    public NoteViewModel(Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        getAllNotes = noteRepository.getAllNotes;
    }

    public void insertNote(Note note){
        noteRepository.insertNote(note);
    }

    public void deleteNote(Note note){
        noteRepository.deleteNote(note);
    }

    public void updateNote(Note note){
        noteRepository.updateNote(note);
    }
}
