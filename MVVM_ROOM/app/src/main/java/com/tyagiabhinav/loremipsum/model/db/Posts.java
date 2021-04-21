package com.tyagiabhinav.loremipsum.model.db;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts_table")
public class Posts extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String title;
    public final String desc;
    @Ignore
    private boolean descVisible;

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

    public void setDescVisible(boolean descVisible) {
        this.descVisible = descVisible;
        notifyPropertyChanged(com.tyagiabhinav.loremipsum.BR.descVisible);
    }

    @Bindable
    public boolean isDescVisible() {
        return descVisible;
    }
}


