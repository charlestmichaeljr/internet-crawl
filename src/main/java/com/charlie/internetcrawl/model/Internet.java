package com.charlie.internetcrawl.model;

import java.util.ArrayList;
import java.util.List;

public class Internet {
    private List<Page> pages = new ArrayList<>();

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
}
