package com.example.noteapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.Update;

import com.example.noteapp.entity.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM NoteDatabase ORDER BY noteTitle ASC")
    LiveData<List<Note>> getAllNotes();

    @Insert()
    void insertNote(Note note);

    @Query("DELETE FROM NoteDatabase WHERE id=:id")
    void deleteNote(int id);

    @Update()
    void updateNote(Note note);
}
