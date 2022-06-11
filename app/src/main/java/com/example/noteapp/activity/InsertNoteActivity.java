package com.example.noteapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.noteapp.R;
import com.example.noteapp.databinding.ActivityInsertNoteBinding;
import com.example.noteapp.entity.Note;
import com.example.noteapp.viewmodel.NoteViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertNoteActivity extends AppCompatActivity {

    private ActivityInsertNoteBinding binding;
    private String gTitle, gSubTitle, gContentNote;
    private NoteViewModel noteViewModel;
    private String gPriority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        binding.greenPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.greenPriority.setImageResource(R.drawable.ic_done);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(0);

                gPriority = "1";
            }
        });

        binding.yellowPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.greenPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(R.drawable.ic_done);
                binding.redPriority.setImageResource(0);

                gPriority = "2";
            }
        });

        binding.redPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.greenPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(R.drawable.ic_done);

                gPriority = "3";
            }
        });

        binding.insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gTitle = binding.titleEdittext.getText().toString().trim();
                gSubTitle = binding.subtitleEdittext.getText().toString().trim();
                gContentNote = binding.noteEdittext.getText().toString().trim();

                insertNote(gTitle, gSubTitle, gContentNote);
            }
        });


    }

    private void insertNote(String title, String subTitle, String contentNote) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String timeInsert = formatter.format(date);

        Note note = new Note();
        note.noteTitle = title;
        note.noteSubtitle = subTitle;
        note.noteDate = timeInsert;
        note.noteContent = contentNote;
        note.notePriority = gPriority;

        noteViewModel.insertNote(note);

        Toast.makeText(InsertNoteActivity.this, R.string.note_inserted_successfully_toast, Toast.LENGTH_LONG).show();

        finish();
    }
}