package com.alifabdulrahman.malaysiakinireader.storage.substorage;

import android.content.Context;
import android.content.SharedPreferences;

import com.alifabdulrahman.malaysiakinireader.model.NewsSectionData;
import com.alifabdulrahman.malaysiakinireader.storage.storage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class newsSectionStorage extends storage {
     private final String storageName = "newsSectionStorage";
     private final String storageName2 = "NewYorkTimeStorage";
     private ArrayList<NewsSectionData> newsSection2;
     private final String newsSectionType = "newsSectionType";

    public newsSectionStorage(Context context) {
        super(context);
        this.sp = context.getSharedPreferences(storageName, Context.MODE_PRIVATE);
        this.editor = sp.edit();
    }


    public ArrayList<NewsSectionData> loadData() {
        Gson hson = new Gson();
        String kson = sp.getString(newsSectionType, null);
        Type dataType = new TypeToken<ArrayList<NewsSectionData>>() {}.getType();
        newsSection2 = hson.fromJson(kson, dataType);

        if (newsSection2 == null) {
           return newsSection2 = new ArrayList<>();
        }
        return newsSection2;
    }

    @Override
    public void saveData() {
        Gson hson = new Gson();
        String kson = hson.toJson(newsSection2);
        editor.putString(newsSectionType, kson);
        editor.apply();
    }

    public void saveReading(String url, String newsType, String wasReading){
        editor.putString("sectionURL", url);
        editor.putString("sectionType", newsType);
        editor.putString ("wasReading", wasReading);

        //System.out.println("checking65" + sp.getString("sectionURL", ""));
    }

    public void setReading(String wasReading){
        editor.putString ("wasReading", wasReading);
    }

    public void loadReading(){
        String wasReading = sp.getString("wasReading", "");

        if (wasReading.equals("yes")){
            sectionManager.section(3);
        }
    }

    public String getSectionURL(){
        return sp.getString("sectionURL", "");

    }

    public String getNewsSectionType(){
        return sp.getString("sectionType", "");
    }

}


