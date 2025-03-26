package com.bizitglobal.tarjetafiel.commons.paginacion2;

import org.hibernate.Query;
import org.hibernate.Criteria;

import java.util.List;

/**
 * @author Pietro Polsinelli ppolsinelli@open-lab.com
 * @author Ales Justin ales.justin@gmail.com
 */
public abstract class HibernatePage extends AbstractPage {

    protected int totalElements = -1;

    protected HibernatePage(int pageNumber, int pageSize) {
        super(pageNumber, pageSize);
    }

    public boolean hasNextPage() {
        return elements.size() > getPageSize();
    }

    public List getThisPageElements() {
        /*
        * Since we retrieved one more than the specified pageSize when the
        * class was constructed, we now trim it down to the pageSize if a next
        * page exists.
        */
        return hasNextPage() ? elements.subList(0, getPageSize()) : elements;
    }

    private void isLastPageNumber() {
        if (Integer.MAX_VALUE == this.pageNumber) {
            this.pageNumber = (getTotalNumberOfElements() / this.pageSize);
        }
    }

    protected void createElements(Query query) {
        isLastPageNumber();
        /**
         * We set the max results to one more than the specfied pageSize to
         * determine if any more results exist (i.e. if there is a next page
         * to display). The result set is trimmed down to just the pageSize
         * before being displayed later (in getThisPageElements()).
         */
        elements = query
                .setFirstResult(this.pageNumber * this.pageSize)
                .setMaxResults(this.pageSize + 1)
                .list();
    }

    protected void createElements(Criteria criteria) {
        isLastPageNumber();
        /**
         * We set the max results to one more than the specfied pageSize to
         * determine if any more results exist (i.e. if there is a next page
         * to display). The result set is trimmed down to just the pageSize
         * before being displayed later (in getThisPageElements()).
         */
        elements = criteria
                .setFirstResult(this.pageNumber * this.pageSize)
                .setMaxResults(this.pageSize + 1)
                .list();
    }

}