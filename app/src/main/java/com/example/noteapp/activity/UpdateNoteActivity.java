package com.example.noteapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.noteapp.R;
import com.example.noteapp.databinding.ActivityUpdateNoteBinding;
import com.example.noteapp.entity.Note;
import com.example.noteapp.utility.Utility;
import com.example.noteapp.viewmodel.NoteViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateNoteActivity extends AppCompatActivity {

    private ActivityUpdateNoteBinding binding;
    private NoteViewModel noteViewModel;
    private String gTitle, gSubTitle, gContentNote;
    private String gPriority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        gTitle = intent.getStringExtra(Utility.NOTE_TITLE_EXTRA);
        gSubTitle = intent.getStringExtra(Utility.NOTE_SUBTITLE_EXTRA);
        gContentNote = intent.getStringExtra(Utility.NOTE_CONTENT_EXTRA);
        gPriority = intent.getStringExtra(Utility.NOTE_PRIORITY_EXTRA);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        binding.titleEdittext.setText(gTitle);
        binding.subtitleEdittext.setText(gSubTitle);
        binding.noteEdittext.setText(gContentNote);
        if(gPriority.equals("1")){
            binding.greenPriority.setImageResource(R.drawable.ic_done);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
        }else if(gPriority.equals("2")){
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.ic_done);
            binding.redPriority.setImageResource(0);
        }else {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.ic_done);
        }

        binding.updateButton.setOnClickListener(view -> {
            gTitle = binding.titleEdittext.getText().toString().trim();
            gSubTitle = binding.subtitleEdittext.getText().toString().trim();
            gContentNote = binding.noteEdittext.getText().toString().trim();

            updateNote(gTitle, gSubTitle, gContentNote);
        });

    }

    private void updateNote(String title, String subtitle, String contentNote) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String timeUpdate = formatter.format(date);

        Note note = new Note();
        note.noteTitle = title;
        note.noteSubtitle = subtitle;
        note.noteDate = timeUpdate;
        note.noteContent = contentNote;
        note.notePriority = gPriority;

        noteViewModel.updateNote(note);

        Toast.makeText(UpdateNoteActivity.this,
                R.string.note_updated_successfully_toast,
                Toast.LENGTH_LONG).show();

        startActivity(new Intent(UpdateNoteActivity.this, MainActivity.class));
        finish();
    }
}