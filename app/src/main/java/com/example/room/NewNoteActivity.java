package com.example.room;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class NewNoteActivity extends AppCompatActivity {

    public static final String NOTE_ADDED = "new_note";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        editText = findViewById(R.id.edtText);
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultntent = new Intent();
                if (TextUtils.isEmpty(editText.getText())){
                    setResult(RESULT_CANCELED,resultntent);
                }else {
                    resultntent.putExtra(NOTE_ADDED,editText.getText().toString());
                    setResult(RESULT_OK,resultntent);
                }
                finish();
            }
        });
    }

}
