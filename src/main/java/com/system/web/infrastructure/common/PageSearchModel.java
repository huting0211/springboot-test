package com.system.web.infrastructure.common;

public class PageSearchModel extends SearchModel {
    private int pageIndex;

    private int pageSize;

    private int startIndex;

    private int endIndex;

    private void setIndexes() {
        startIndex = pageIndex * pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        setIndexes();
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        setIndexes();
    }

    public int getEndIndex() {
        return endIndex;
    }

    private int getStartIndex() {
        return startIndex;
    }
}
