package com.example.noteapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.noteapp.adapter.NoteAdapter;
import com.example.noteapp.databinding.ActivityMainBinding;
import com.example.noteapp.entity.Note;
import com.example.noteapp.viewmodel.NoteViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NoteViewModel noteViewModel;
    private List<Note> noteList;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        noteList = new ArrayList<>();
        noteAdapter = new NoteAdapter(noteList);
        binding.recyclerviewMain.setAdapter(noteAdapter);
        binding.recyclerviewMain.setLayoutManager(new GridLayoutManager(this, 2));

        binding.goToInsertActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertNoteActivity.class));
            }
        });

        noteViewModel.getAllNotes.observe(this, notes -> {
            noteAdapter.setNoteList(notes);
        });


    }
}