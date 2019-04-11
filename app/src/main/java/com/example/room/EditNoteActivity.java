package com.example.room;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {

    EditText editText;
    public static final String NOTE_ID="note_id";
    public static final String UPDATED_ID="note_text";
    private Bundle bundle;
    private String noteId;
    private LiveData<Note> note;
    EditNoteViewModel noteModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        editText=findViewById(R.id.getNote);

        bundle = getIntent().getExtras();
        if (bundle !=null){
            noteId=bundle.getString("note_id");
        }

        noteModel = ViewModelProviders.of(this).get(EditNoteViewModel.class);

        note=noteModel.getNote(noteId);
        note.observe(this, new Observer<Note>() {
            @Override
            public void onChanged(@Nullable Note note) {
                editText.setText(note.getmNote());
            }
        });



        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String updatedNote = editText.getText().toString();
                Intent result = new Intent();
                result.putExtra(NOTE_ID,noteId);
                result.putExtra(UPDATED_ID,updatedNote);
                setResult(RESULT_OK,result);
                finish();


            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
