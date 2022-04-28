package edu.ucsd.cse110.cse110group51;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@Entity(tableName = "AnimalData")
public class AnimalData {

    @NonNull
    public static String text;
    public boolean opened;
    public boolean checked;
    public int order;

    //"animal": "shark",  "opened": true,  "checked": false, "order": 0}
    AnimalData(@NonNull String text, boolean opened, boolean checked) {
        this.text = text;
        this.opened = opened;
        this.checked = checked;
        this.order = order;
    }

    public static String getText(){return text;}

    public static List<AnimalData> loadJSON(Context context, String path){
        try  {
            InputStream input = context.getAssets().open(path);
            Reader reader = new InputStreamReader(input);
            Gson gson = new Gson();
            Type type = new TypeToken<List<AnimalData>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }
}
