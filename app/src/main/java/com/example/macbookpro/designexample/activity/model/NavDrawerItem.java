package com.example.macbookpro.designexample.activity.model;

/**
 * Created by macbookpro on 29/08/15.
 */
public class NavDrawerItem {
    private String showNotify;
    private String title;

    public NavDrawerItem() {

    }

    public NavDrawerItem(String showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShowNotify() {
        return showNotify;
    }

    public void setShowNotify(String showNotify) {
        this.showNotify = showNotify;
    }
}
