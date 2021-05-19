package com.example.miniproject;

import java.io.*;
import java.util.ArrayList;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    public static final String TAG = "File:";
    private String[] textList;
    //private EditText editText;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> textLists;
    private static boolean firstPass = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    int pos;
    String str;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(getClass().getSimpleName(), "onCreate()");

        if (firstPass){
            Log.d(TAG, "first is true, setting to false");
            firstPass=false;
            textList = ListTitle.getArray();
        }
        else{
            Log.d(TAG, "firstpass is:" + firstPass);
            Bundle extras = getIntent().getExtras();

            pos = ListTitle.getPosition();
            str = getIntent().getStringExtra("title");
            ListTitle.setArray(pos, str);
            textList = ListTitle.getArray();
            Log.d(TAG, "firstpass is:" +textList[pos]);
        }

        File[] files;
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        Context context = getApplicationContext();
        Log.d(TAG,"context.getFilesDir() is: "+context.getFilesDir());
        String path = context.getFilesDir().getPath();
        Log.d(TAG,"String path = context.getFilesDir().getPath() is :"+path);
        for (int i =0; i < 12; i++){
            File file = new File (path+"/note"+i+".txt");

            try {
                fw = new FileWriter(file, true);
                bw = new BufferedWriter(fw);
                pw = new PrintWriter(bw);

                pw.println("");
                //don't forget to clean up
                pw.flush();
                pw.close();
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        listView = findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1,
                        textList);
        listView.setAdapter(arrayAdapter);
        //set the callbacks for the listView
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowID) {
                Object obj = listView.getItemAtPosition(position);
                Toast toast = Toast.makeText(MainActivity.this, obj.toString(), Toast.LENGTH_SHORT);
                toast.show();
                ListTitle.setPosition(position);
                Intent noteIntent = new Intent(MainActivity.this, NoteActivity.class);

                startActivity(noteIntent);
            }
        });

    }


}