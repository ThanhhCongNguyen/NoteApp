package com.example.noteapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.noteapp.R;
import com.example.noteapp.databinding.ActivityInsertNoteBinding;
import com.example.noteapp.entity.Note;
import com.example.noteapp.utils.Priority;
import com.example.noteapp.viewmodel.NoteViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertNoteActivity extends AppCompatActivity {

    ActivityInsertNoteBinding binding;
    NoteViewModel noteViewModel;
    Priority priority = Priority.GREEN;
    Note note;
    int iconActive = R.drawable.ic_done;
    int iconDeactivate = 0;
    public static final String NOTE_EXTRA = "note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        Intent intent = getIntent();
        if (intent != null) {
            note = (Note) intent.getSerializableExtra(InsertNoteActivity.NOTE_EXTRA);
            if (note != null) {
                binding.titleEdittext.setText(note.getNoteTitle());
                binding.noteEdittext.setText(note.getNoteContent());
                binding.greenPriority.setImageResource(note.getNotePriority().equals(Priority.GREEN) ? iconActive : iconDeactivate);
                binding.yellowPriority.setImageResource(note.getNotePriority().equals(Priority.YELLOW) ? iconActive : iconDeactivate);
                binding.redPriority.setImageResource(note.getNotePriority().equals(Priority.RED) ? iconActive : iconDeactivate);
                binding.insertButton.setVisibility(View.GONE);
                binding.updateButton.setVisibility(View.VISIBLE);
            }
        }

        binding.greenPriority.setOnClickListener(view -> {
            binding.greenPriority.setImageResource(iconActive);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);

            priority = Priority.GREEN;
        });

        binding.yellowPriority.setOnClickListener(view -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(iconActive);
            binding.redPriority.setImageResource(0);

            priority = Priority.YELLOW;
        });

        binding.redPriority.setOnClickListener(view -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(iconActive);

            priority = Priority.RED;
        });

        binding.insertButton.setOnClickListener(view -> {
            insertNote();
        });

        binding.updateButton.setOnClickListener(view -> {
            updateNote();
        });
    }

    private void updateNote() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String timeInsert = formatter.format(date);
        String title = binding.titleEdittext.getText().toString().trim();
        String noteContent = binding.noteEdittext.getText().toString().trim();
        Note note = new Note(title, timeInsert, noteContent, priority);
        noteViewModel.updateNote(note);
        finish();
    }

    private void insertNote() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String timeInsert = formatter.format(date);
        String title = binding.titleEdittext.getText().toString().trim();
        String noteContent = binding.noteEdittext.getText().toString().trim();
        Note note = new Note(title, timeInsert, noteContent, priority);
        noteViewModel.insertNote(note);
        finish();
    }
}