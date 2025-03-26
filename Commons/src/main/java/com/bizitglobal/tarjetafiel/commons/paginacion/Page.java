package com.bizitglobal.tarjetafiel.commons.paginacion;

import java.util.List;

import org.apache.log4j.Logger;

public interface Page {
	 boolean isFirstPage();

	  boolean isLastPage();

	  boolean hasNextPage();

	  boolean hasPreviousPage();

	  int getLastPageNumber();

	  List getThisPageElements();

	  Logger getLogger();

	  int getTotalNumberOfElements();

	  int getThisPageFirstElementNumber();

	  int getThisPageLastElementNumber();

	  int getNextPageNumber();

	  int getPreviousPageNumber();

	  int getPageSize();

	  int getPageNumber();

	  List getAllElements();  


}
