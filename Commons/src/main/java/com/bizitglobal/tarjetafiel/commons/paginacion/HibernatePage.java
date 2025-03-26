package com.bizitglobal.tarjetafiel.commons.paginacion;

import java.util.List;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import java.util.HashSet;
import java.util.Set;


public class HibernatePage implements Page {

	protected Query query;

	  protected List elements;
	  protected int pageSize;
	  protected int pageNumber;
	  protected int totalElements = -1;
	  
	  public static final String DRIVER_INFORMIX = "com.informix.jdbc.IfxDriver";
	  public static final String DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
	  
	  public static String defaultDriver = null;
	  
	  public static Set jdbcClassesSupportingScrollCursors = new HashSet();
	  private ScrollableResults scrollableResults;
	  public static final int DEFAULT_PAGE_SIZE = 10 ;

	  private HibernatePage(int pageNumber, int pageSize) {
	    this.pageNumber = pageNumber;
	    this.pageSize = pageSize;
	  }


	  public boolean isFirstPage() {
	    return getPageNumber() == 0;
	  }

	  public boolean isLastPage() {
	    return getPageNumber() >= getLastPageNumber();
	  }

	  public boolean hasNextPage() {
	    return !isLastPage();
	  }

	  public boolean hasPreviousPage() {
	    return getPageNumber() > 0;
	  }

	  public int getLastPageNumber() {

	    double totalResults = new Integer(getTotalNumberOfElements()).doubleValue();
	    return new Double(Math.floor(totalResults / getPageSize())).intValue();
	  }

	  public List getThisPageElements() {
	    return elements;
	  }

	  public Logger getLogger() {
	     //WARN: THIS CODE MUST BE REPLACED
	    return Logger.getLogger(HibernatePage.class);
	  }

	  public int getTotalNumberOfElements() {
	    return totalElements;
	  }

	  public int getThisPageFirstElementNumber() {
	    return getPageNumber() * getPageSize() + 1;
	  }

	  public int getThisPageLastElementNumber() {
	    int fullPage = getThisPageFirstElementNumber() + getPageSize() - 1;
	    return getTotalNumberOfElements() < fullPage ?
	            getTotalNumberOfElements() :
	            fullPage;
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

	  public List getAllElements() {
	    HibernatePage pageTmp = getHibernatePageInstance(query,1,getTotalNumberOfElements());
	    return pageTmp.getThisPageElements();
	  }

	  /**
	   * Este metodo devuelve una instacia HibernatePage utilizando el defaultDriver = "oracle.jdbc.driver.OracleDriver"
	   * @param query
	   * @param pageNumber
	   * @param pageSize
	   * @return HibernatePage
	   */
	  public static HibernatePage getHibernatePageInstance(Query query, int pageNumber, int pageSize) {
		  if(HibernatePage.defaultDriver == null){
			  String driver = HibernatePage.DRIVER_ORACLE;
			  jdbcClassesSupportingScrollCursors.add(DRIVER_ORACLE);
			  HibernatePage.setDefaultDriver(driver);
		  }
			  
		  return getHibernatePageInstance(query, pageNumber, pageSize, HibernatePage.defaultDriver);
	  }

	  public static HibernatePage getHibernatePageInstance(Query query,
	                                                       int pageNumber,
	                                                       int pageSize,
	                                                       String driverClass) {

	    /*if (query.getQueryString().toLowerCase().indexOf("order by")==-1) {
	     // WARN IN SOME WAY: warn("Using pagination without order by can lead to inconsistent results, for example on certain Oracle instances: "+query.getQueryString());
	    }*/		
	    if (jdbcClassesSupportingScrollCursors.contains(driverClass))
	      return HibernatePage.getScrollPageInstanceWithTotalByScroll(query, pageNumber, pageSize);
	    else
	      return HibernatePage.getScrollPageInstanceWithTotalByList(query, pageNumber, pageSize);
	  }

	  /**
	   * Construct a new HibernatePage. HibernatePage numbers are zero-based so the
	   * first page is page 0.
	   *
	   * @param query      the Hibernate Query
	   * @param pageNumber the page number (zero-based);
	   *                   if Integer.MAX_VALUE will return the last page for the query
	   * @param pageSize   the number of results to display on the page
	   */
	  protected static HibernatePage getScrollPageInstanceWithTotalByScroll(Query query, int pageNumber, int pageSize) {

	    HibernatePage sp = new HibernatePage(pageNumber, pageSize);
	    sp.query = query;
	    try {
	      sp.scrollableResults = query.scroll(ScrollMode.SCROLL_SENSITIVE);
	      sp.scrollableResults.last();	      
	      sp.totalElements = sp.scrollableResults.getRowNumber();

	      sp.fixThisPageElements();
	      sp.scrollableResults.close();
	    } catch (HibernateException e) {
	      e.printStackTrace();
	      sp.getLogger().error("Failed to create ScrollPage by getScrollPageInstanceWithTotalByScroll: " + e.getMessage());
	      throw new RuntimeException(e);
	    }

	    return sp;
	  }


	  /**
	   * Construct a new HibernatePage. HibernatePage numbers are zero-based so the
	   * first page is page 0.
	   *
	   * @param query      the Hibernate Query
	   * @param pageNumber the page number (zero-based);
	   *                   if Integer.MAX_VALUE will return the last page for the query
	   * @param pageSize   the number of results to display on the page
	   */
	  protected static HibernatePage getScrollPageInstanceWithTotalByList(Query query, int pageNumber, int pageSize) {

	    HibernatePage sp = new HibernatePage(pageNumber, pageSize);
	    sp.query = query;
	    try {
	      //bacame useless
	      //sp.scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);
	      sp.totalElements = sp.calculateTotalElementsByList();
	      sp.fixThisPageElements();

	    } catch (HibernateException e) {
	      e.printStackTrace();
	      sp.getLogger().error("Failed to create ScrollPage by getScrollPageInstanceWithTotalByQuery: " + e.getMessage());
	      throw new RuntimeException(e);
	    }

	    return sp;
	  }

	  private void fixThisPageElements() throws HibernateException {

	    if (this.pageSize<=0)
	      this.pageSize=HibernatePage.DEFAULT_PAGE_SIZE;

	    if (Integer.MAX_VALUE == this.pageNumber)
	      this.pageNumber = (getTotalNumberOfElements() / this.pageSize);
	    else if (pageNumber>(totalElements/pageSize))
	      pageNumber = totalElements/pageSize;
	    query = query.setFirstResult(this.pageNumber * this.pageSize);
	    query = query.setMaxResults(this.pageSize);
	    elements = query.list();
	  }

	  private int calculateTotalElementsByList() throws HibernateException {

	    return query.list().size()-1;

	  }
	  
	  public static String getDefaultDriver() {
		return defaultDriver;
	}
	  
	  public static void setDefaultDriver(String driver) {
		 HibernatePage.defaultDriver = driver;			
	  }
}
