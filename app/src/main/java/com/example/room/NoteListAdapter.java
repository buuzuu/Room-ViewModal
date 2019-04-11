package com.example.room;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder> {

    public interface OnDeleteClickListner{
        void OnDeleteClickListner(Note myNote);
    }

    private Context context;
    private List<Note> noteList;
    private OnDeleteClickListner onDeleteClickListner;
    public NoteListAdapter(Context context,OnDeleteClickListner listner) {

        this.context = context;
        this.onDeleteClickListner = listner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if (noteList != null) {
            Note note = noteList.get(i);
            viewHolder.setData(note.getmNote(), i);
            viewHolder.setListner();
        } else {
            viewHolder.noteItemView.setText("No Notes...");
        }


    }


    @Override
    public int getItemCount() {
        if (noteList != null)
            return noteList.size();
        else
            return 0;
    }

    public void setNoteList(List<Note> notes) {
        noteList = notes;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView noteItemView;
        private int mPosition;
        private ImageView imgDelete, imgEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteItemView = itemView.findViewById(R.id.notetxt);
            imgDelete=itemView.findViewById(R.id.ivRowDelete);
            imgEdit=itemView.findViewById(R.id.ivRowEdit);
        }

        public void setData(String getmNote, int i) {

            noteItemView.setText(getmNote);
            mPosition = i;
        }

        public void setListner() {
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,EditNoteActivity.class);
                i.putExtra("note_id",noteList.get(mPosition).getId());
                ((Activity)context).startActivityForResult(i,MainActivity.UPDTE_NOTE_ACTIVITY_REQUEST_CODE);
            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onDeleteClickListner !=null){
                    onDeleteClickListner.OnDeleteClickListner(noteList.get(mPosition));
                }


            }
        });

        }
    }
}
