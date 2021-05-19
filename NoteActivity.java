package com.example.miniproject;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class NoteActivity extends MainActivity {
    private EditText title;
    private EditText bodyText;
    private Button submit;
    private String editTitle;
    private File rootDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        File[] files;
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        Context context = getApplicationContext();
        String path = context.getFilesDir().getPath();

        rootDir = this.getFilesDir();
        files = rootDir.listFiles();
        //System.out.println(files[0].toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        title = findViewById(R.id.title);
        bodyText = findViewById(R.id.body_text);

        submit = findViewById(R.id.btn);
        submit.setOnClickListener(new clickListener());
    }



    private class clickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String t =title.getText().toString();
            //editTitle = title.getText().toString();
            //ListTitle.setStr(editTitle);
            Intent noteIntent = new Intent(NoteActivity.this, MainActivity.class);
            noteIntent.putExtra("title", t);

            startActivity(noteIntent);
        }
    }
}
