package com.tyagiabhinav.loremipsum.model.db;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts_table")
public class Posts {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String desc;
    @Ignore
    private boolean isDescVisible = false;

    public Posts(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isDescVisible() {
        return isDescVisible;
    }

    public void setDescVisible(boolean descVisible) {
        isDescVisible = descVisible;
    }
}


