package net.fux.support.vo;

import java.util.List;

/**
 * Created by fuxiaoj on 2018/12/24 0024.
 */
public class BootstrapTable {

    private List<?> rows;
    private Integer total;

    public BootstrapTable(List<?> rows, Integer total) {
        this.rows = rows;
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
