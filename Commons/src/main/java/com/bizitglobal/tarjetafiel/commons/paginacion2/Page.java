package com.bizitglobal.tarjetafiel.commons.paginacion2;

import org.apache.commons.logging.Log;

import java.util.List;

/**
 * @author Pietro Polsinelli ppolsinelli@open-lab.com
 */
public interface Page {

    public static final int DEFAULT_SIZE = 15;

    boolean isFirstPage();

    boolean isLastPage();

    boolean hasNextPage();

    boolean hasPreviousPage();

    int getLastPageNumber();

    List getThisPageElements();

    Log getLogger();

    int getTotalNumberOfElements();

    int getThisPageFirstElementNumber();

    int getThisPageLastElementNumber();

    int getNextPageNumber();

    int getPreviousPageNumber();

    int getPageSize();

    int getPageNumber();
}
