package com.example.noteapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.R;
import com.example.noteapp.activity.UpdateNoteActivity;
import com.example.noteapp.entity.Note;
import com.example.noteapp.utility.Utility;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    
    private List<Note> noteList;

    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        Context context = holder.itemView.getContext();
        
        holder.itemTitle.setText(note.noteTitle);
        holder.itemSubTitle.setText(note.noteSubtitle);
        holder.itemTime.setText(note.noteDate);
        if(note.notePriority.equals("1")){
            holder.itemPriority.setBackgroundResource(R.drawable.green_circle);
        }else if(note.notePriority.equals("2")){
            holder.itemPriority.setBackgroundResource(R.drawable.yellow_circle);
        }else {
            holder.itemPriority.setBackgroundResource(R.drawable.red_circle);
        }
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateNoteActivity.class);
            intent.putExtra(Utility.NOTE_TITLE_EXTRA, note.noteTitle);
            intent.putExtra(Utility.NOTE_SUBTITLE_EXTRA, note.noteSubtitle);
            intent.putExtra(Utility.NOTE_CONTENT_EXTRA, note.noteContent);
            intent.putExtra(Utility.NOTE_PRIORITY_EXTRA, note.notePriority);
            context.startActivity(intent);
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

    class NoteViewHolder extends RecyclerView.ViewHolder{

        private TextView itemTitle, itemSubTitle, itemTime;
        private View itemPriority;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemSubTitle = itemView.findViewById(R.id.itemSubTitle);
            itemTime = itemView.findViewById(R.id.itemTime);
            itemPriority = itemView.findViewById(R.id.itemPriority);

        }
    }
}
