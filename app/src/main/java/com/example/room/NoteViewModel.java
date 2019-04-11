package com.example.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private static final String TAG = "NoteViewModel";
    private NoteDoa noteDoa;
    private NoteRoomDatabase noteDB;
    private LiveData<List<Note>> mAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteDB = NoteRoomDatabase.getDatabase(application);
        noteDoa = noteDB.noteDoa();
        mAllNotes = noteDoa.getAllNotes();
    }

    public void insert(Note note) {

        new InsertAsyncTask(noteDoa).execute(note);
    }

    LiveData<List<Note>> getmAllNotes() {

        return mAllNotes;
    }

    public void update(Note note){

        new UpdateAsyncTask(noteDoa).execute(note);
    }

    public void delete(Note note){
        new DeleteAsyncTask(noteDoa).execute(note);
    }




    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }

    private class InsertAsyncTask extends AsyncTask<Note, Void, Void> {

        NoteDoa mNoteDao;

        public InsertAsyncTask(NoteDoa mNoteDao) {
            this.mNoteDao = mNoteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            mNoteDao.insert(notes[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends AsyncTask<Note,Void,Void>{

        NoteDoa mNoteDao;
        public UpdateAsyncTask(NoteDoa noteDoa) {
            this.mNoteDao = noteDoa;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.update(notes[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends AsyncTask<Note,Void,Void> {
        NoteDoa mNoteDao;
        public DeleteAsyncTask(NoteDoa noteDoa) {
            this.mNoteDao = noteDoa;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.delete(notes[0]);
            return null;
        }
    }
}
