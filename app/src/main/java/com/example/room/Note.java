package com.example.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey
    @NonNull
    public String id;

    @NonNull
    @ColumnInfo(name = "note")
    public String mNote;

    public Note(@NonNull String id, @NonNull String mNote) {
        this.id = id;
        this.mNote = mNote;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getmNote() {
        return mNote;
    }

    public void setmNote(@NonNull String mNote) {
        this.mNote = mNote;
    }
}
