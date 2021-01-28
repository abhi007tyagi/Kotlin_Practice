package com.tyagiabhinav.loremipsum.model.service;

import com.tyagiabhinav.loremipsum.model.db.Posts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "body";

    public static List<Posts> getPosts(String response){
        List<Posts> posts = new ArrayList<>();
        try {
            JSONArray resp = new JSONArray(response);
            for(int i=0; i<resp.length(); i++){
                JSONObject obj = resp.getJSONObject(i);
                posts.add(new Posts(obj.getString(TITLE), obj.getString(DESCRIPTION)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            posts.add(new Posts("Error Occurred", "Try again later!"));
        }
        return posts;
    }
}
