package com.example.miniproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import java.io.*;

public class NoteActivity extends MainActivity {
    private EditText title;
    private EditText bodyText;
    private Button submit;
    private File rootDir;
    private String editText;
    private String editBody;
    private static boolean firstPass = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rootDir = this.getFilesDir();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        if (firstPass){
            firstPass = false;
            // Creating the text files required to store information
            Context context = getApplicationContext();
            String path = context.getFilesDir().getPath();
            Log.d(TAG,"String path = context.getFilesDir().getPath() is :"+path);
            for (int i =0; i < 12; i++){
                File file = new File (path+"/note"+i+".txt");
            }
        }
        else{
            //to retrieve the information for text file to update the title and bodyText
            //used readFile where it reads the file and updates the title and bodyText
            title = findViewById(R.id.title);
            bodyText = findViewById(R.id.body_text);
            readFile();
            title.setText(editText);
            bodyText.setText(editBody);
        }

        title = findViewById(R.id.title);
        bodyText = findViewById(R.id.body_text);
        submit = findViewById(R.id.btn);
        submit.setOnClickListener(new clickListener());

    }



    private class clickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // when the user hits the submit button calls writeFile
            writeFile();

            String t = title.getText().toString();
            Intent noteIntent = new Intent(NoteActivity.this, MainActivity.class);
            noteIntent.putExtra("title", t);

            startActivity(noteIntent);
        }
    }
    private void writeFile(){
        int pos;
        String noteTitle;
        String bodytext;
        pos = ListTitle.getPosition();
        FileWriter fw;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        final String path = rootDir.getAbsolutePath()+"/note"+pos+".txt";

        Log.d("C", path);
        try {
            fw = new FileWriter(path);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            //gets information from editText and writes in the file
            noteTitle = title.getText().toString();
            bodytext = bodyText.getText().toString();

            pw.println(noteTitle);
            pw.println(bodytext);

            Log.d("title: ", noteTitle);
            Log.d("bodytext: ", bodytext);

            //don't forget to clean up
            pw.flush();

            pw.close();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readFile(){
        int pos;
        pos = ListTitle.getPosition();
        final String path = rootDir.getAbsolutePath()+"/note"+pos+".txt";

        String line;
        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader((path))
                    );
            line = br.readLine();
            Log.d(TAG,"first read of line:"+line);
            editText = line;
            line = br.readLine();
            Log.d(TAG,"second read of line:"+line);
            editBody = line;
            br.close();
        } catch (FileNotFoundException e) {
            Log.d(TAG,"FileNotFound exception");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG,"IOException");
        }

    }

}
