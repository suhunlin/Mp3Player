package com.suhun.mp3player;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

public class Mp3ListHandler {
    private String tag = Mp3ListHandler.class.getSimpleName();
    ContentResolver mp3ContentResolver;
    ArrayList<String> mp3SongList;

    public Mp3ListHandler(ContentResolver contentResolver){
        this.mp3ContentResolver = contentResolver;
        mp3SongList = new ArrayList<>();
    }

    public ArrayList<String> getMp3SongList(){
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor = mp3ContentResolver.query(uri, null, null, null, null);
//        for(int i=0; i<cursor.getColumnCount();i++){
//            Log.d(tag, "-----Mp3ListHandler column name:"+ cursor.getColumnName(i));
//        }
        while (cursor.moveToNext()){
            String mp3Path = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
            if(mp3Path.endsWith("mp3")){
                Log.d(tag, "-----Mp3ListHandler column name:"+ mp3Path);
                mp3SongList.add(mp3Path);
            }
        }
        return mp3SongList;
    }
}
