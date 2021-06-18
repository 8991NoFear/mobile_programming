package com.example.a4xgridview;

import android.net.Uri;

public class MyItemData {
    private long id;
    private String title;
    private Uri figureUri;

    public MyItemData(long id, String titile, Uri figureUri) {
        setId(id);
        setTitle(titile);
        setFigureUri(figureUri);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Uri getFigureUri() {
        return figureUri;
    }

    public void setFigureUri(Uri figureUri) {
        this.figureUri = figureUri;
    }
}
