package com.tyagiabhinav.loremipsum.model.db;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Posts)) return false;
        Posts posts = (Posts) o;
        return id == posts.id && descVisible == posts.descVisible && title.equals(posts.title) && desc.equals(posts.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, desc, descVisible);
    }
}


