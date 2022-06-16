package com.example.noteapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.noteapp.action.DeleteNote;
import com.example.noteapp.R;
import com.example.noteapp.adapter.NoteAdapter;
import com.example.noteapp.databinding.ActivityMainBinding;
import com.example.noteapp.entity.Note;
import com.example.noteapp.viewmodel.NoteViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    NoteViewModel noteViewModel;
    List<Note> notes;
    NoteAdapter noteAdapter;
    final int SPAN_COUNT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        notes = new ArrayList<>();
        noteAdapter = new NoteAdapter(notes, new DeleteNote() {
            @Override
            public void deleteNote(Note note) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.delete_bottom_sheet, findViewById(R.id.bottomSheet));
                bottomSheetDialog.setContentView(view);
                TextView yesButton, noButton;

                yesButton = view.findViewById(R.id.deleteButton);
                noButton = view.findViewById(R.id.notDeleteButton);

                yesButton.setOnClickListener(v -> {
                    noteViewModel.deleteNote(note.getId());
                    bottomSheetDialog.dismiss();
                });

                noButton.setOnClickListener(v -> {
                    bottomSheetDialog.dismiss();
                });

                bottomSheetDialog.show();
            }
        });
        binding.recyclerviewMain.setAdapter(noteAdapter);
        binding.recyclerviewMain.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));

        binding.goToInsertActButton.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, InsertNoteActivity.class));
        });

        noteViewModel.getAllNotes().observe(this, notes -> {
            noteAdapter.setNoteList(notes);
        });
    }
}