package com.example.miniproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    public static final String TAG = "File:";
    private String[] textList;
    private ArrayAdapter<String> arrayAdapter;
    private static boolean firstPass = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    int pos;
    String str;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(getClass().getSimpleName(), "onCreate()");

        //to avoid rewriting entire information in this activity while switching between activities
        if (firstPass){
            firstPass=false;

        }
        else{
            Log.d(TAG, "firstpass is:" + firstPass);
            Bundle extras = getIntent().getExtras();
            //Gets the position from the ListTitle
            pos = ListTitle.getPosition();
            //gets title information from NoteActivity
            str = getIntent().getStringExtra("title");
            //updates the array in ListTitle
            ListTitle.setArray(pos, str);
        }
        //gets array from a normal class ListTitle
        textList = ListTitle.getArray();


        listView = findViewById(R.id.listView);

         arrayAdapter = new ArrayAdapter<>
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
                //Could have used putExtra but felt it may get too complicated hence use ListTitle to store position information
                ListTitle.setPosition(position);
                Intent noteIntent = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(noteIntent);
                arrayAdapter.notifyDataSetChanged();
            }
        });

    }


}