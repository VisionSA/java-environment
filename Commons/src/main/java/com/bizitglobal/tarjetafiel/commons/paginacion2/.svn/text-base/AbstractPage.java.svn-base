package com.bizitglobal.tarjetafiel.commons.paginacion2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public abstract class AbstractPage implements Page {

    protected final Log logger = LogFactory.getLog(getClass());
    protected List elements;
    protected int pageSize;
    protected int pageNumber;

    protected AbstractPage(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Log getLogger() {
        return logger;
    }

    public boolean isFirstPage() {
        return getPageNumber() == 0;
    }

    public boolean isLastPage() {
        return getPageNumber() >= getLastPageNumber();
    }

    public boolean hasPreviousPage() {
        return getPageNumber() > 0;
    }

    public int getThisPageFirstElementNumber() {
        return getPageNumber() * getPageSize() + 1;
    }

    public int getThisPageLastElementNumber() {
        int fullPage = getThisPageFirstElementNumber() + getPageSize() - 1;
        return (getTotalNumberOfElements() < fullPage ? getTotalNumberOfElements() : fullPage);
    }

    public int getLastPageNumber() {
        /*
        * We use the Math.floor() method because page numbers are zero-based
        * (i.e. the first page is page 0).
        */
        double totalResults = new Integer(getTotalNumberOfElements()).doubleValue();
        return new Double(Math.floor(totalResults / getPageSize())).intValue();
    }

    public int getNextPageNumber() {
        return getPageNumber() + 1;
    }

    public int getPreviousPageNumber() {
        return getPageNumber() - 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

}
