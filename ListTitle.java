package com.example.miniproject;

public class ListTitle {
    private static int position;
    private static String str;

    private static String[] titles = {"Note1", "Note2", "Note3", "Note4", "Note5", "Note6", "Note7","Note8", "Note9", "Note10", "Note11", "Note12"};


    public static void setPosition(int pos){
        position = pos;
    }

    public static void setArray(int pos, String str){
        titles[pos] = str;
    }

    public static String[] getArray(){
        return titles;
    }
    public static int getPosition(){
        return position;
    }


}
