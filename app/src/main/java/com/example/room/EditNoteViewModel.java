package com.example.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

public class EditNoteViewModel extends AndroidViewModel {


    private static final String TAG = "EditNoteViewModel";
    private NoteDoa noteDoa;
    private NoteRoomDatabase db;

    public EditNoteViewModel(@NonNull Application application) {
        super(application);
        db = NoteRoomDatabase.getDatabase(application);
        noteDoa = db.noteDoa();
    }


    public LiveData<Note> getNote(String noteId){

        return noteDoa.getNote(noteId);

    }
}
