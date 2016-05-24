package com.manoj.biz.object;

/**
 * Created by MK033906 on 5/14/2016.
 */
public class UrlContainer {

    private String url;
    private boolean isVisited;

    public boolean isVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
