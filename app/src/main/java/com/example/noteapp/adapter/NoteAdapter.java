package com.example.noteapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.DeleteNote;
import com.example.noteapp.R;
import com.example.noteapp.activity.InsertNoteActivity;
import com.example.noteapp.entity.Note;
import com.example.noteapp.utils.Priority;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private DeleteNote deleteNote;

    public NoteAdapter(List<Note> noteList, DeleteNote deleteNote) {
        this.noteList = noteList;
        this.deleteNote = deleteNote;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Note note = noteList.get(position);
        holder.itemTitle.setText(note.getNoteTitle());
        holder.itemContent.setText(note.getNoteContent());
        holder.itemTime.setText(note.getNoteDate());
        if (note.notePriority.equals(Priority.GREEN)) {
            holder.itemPriority.setBackgroundResource(R.drawable.green_circle);
        } else if (note.notePriority.equals(Priority.YELLOW)) {
            holder.itemPriority.setBackgroundResource(R.drawable.yellow_circle);
        } else {
            holder.itemPriority.setBackgroundResource(R.drawable.red_circle);
        }

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, InsertNoteActivity.class);
            intent.putExtra(InsertNoteActivity.NOTE_EXTRA, note);
            context.startActivity(intent);
        });

        holder.itemView.setOnLongClickListener(view -> {
            deleteNote.deleteNote(note);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView itemTitle, itemContent, itemTime;
        private View itemPriority;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemContent = itemView.findViewById(R.id.itemContent);
            itemTime = itemView.findViewById(R.id.itemTime);
            itemPriority = itemView.findViewById(R.id.itemPriority);

        }
    }
}
