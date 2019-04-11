package com.example.room;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements NoteListAdapter.OnDeleteClickListner {

    private static final String TAG = "MainActivity";
    private static final int NEW_NOTE_ACTIVITY_REQUEST_CODE = 100;
    public static final int UPDTE_NOTE_ACTIVITY_REQUEST_CODE = 101;
    private NoteViewModel noteViewModel;
    private RecyclerView recyclerView;
    private NoteListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        adapter = new NoteListAdapter(this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE);
            }
        });
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getmAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                adapter.setNoteList(notes);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            //code to insert into database

            final String note_id = UUID.randomUUID().toString();
            Note note = new Note(note_id, data.getStringExtra(NewNoteActivity.NOTE_ADDED));
            noteViewModel.insert(note);


            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == UPDTE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){

            //CODE TO UPDATE
            Note note = new Note(
                    data.getStringExtra(EditNoteActivity.NOTE_ID),
                    data.getStringExtra(EditNoteActivity.UPDATED_ID)
            );

            noteViewModel.update(note);
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnDeleteClickListner(Note myNote) {

        noteViewModel.delete(myNote);
    }
}
