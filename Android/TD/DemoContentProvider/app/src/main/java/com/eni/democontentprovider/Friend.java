package com.eni.democontentprovider;

/**
 * Created by quentin for DemoContentProvider on 2019-12-02.
 */
public class Friend {
    private int id;
    private String displayname;

    public Friend(int id, String displayname) {
        this.id = id;
        this.displayname = displayname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    @Override
    public String toString() {
        return this.id + " : " + this.displayname;
    }
}
