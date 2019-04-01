package net.fux.support.vo;

import net.fux.support.core.Page;
import net.fux.support.core.PageContext;

import java.util.Collection;

/**
 * Created by fuxiaoj on 2018/12/24 0024.
 */
public class BootstrapTable extends Page {

    protected Collection<?> rows;

    private int total;

    private Collection<?> footer;

    public BootstrapTable() {
        this(null);
    }

    public BootstrapTable(PageContext context) {
        super(context);
        if (context == null) {
            this.rows = null;
            total = 0;
            this.footer = null;
        } else {
            this.rows = context.getDatas();
            total = context.getTotal();
            this.footer = null;
        }
    }

    public Collection<?> getRows() {
        return rows;
    }

    public void setRows(Collection<?> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Collection<?> getFooter() {
        return footer;
    }

    public void setFooter(Collection<?> footer) {
        this.footer = footer;
    }
}
