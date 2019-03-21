package net.fux.support.vo;

import java.util.Map;

/**
 * Created by fuxiaoj on 2018/12/21 0021.
 */
public class SearchVo {

    private int offset = 0;

    private int limit = 10;

    private Direct order;

    private String sort;

    private Map<String, Object> params;

    public enum Direct {
        asc, desc
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Direct getOrder() {
        return order;
    }

    public void setOrder(Direct order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
