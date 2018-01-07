package com.system.web.infrastructure.common;

import java.util.List;

public class SearchModel {
    private List<SearchFilter> filters;

    private List<OrderByParameter> sorts;

    public List<OrderByParameter> getSorts() {
        return sorts;
    }

    public void setSorts(List<OrderByParameter> sorts) {
        this.sorts = sorts;
    }

    public List<SearchFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<SearchFilter> filters) {
        this.filters = filters;
    }
}