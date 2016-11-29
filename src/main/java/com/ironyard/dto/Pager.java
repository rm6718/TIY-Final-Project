package com.ironyard.dto;

import com.ironyard.data.Goal;
import org.springframework.data.domain.Page;

/**
 * Created by reevamerchant on 11/11/16.
 */
public class Pager {
    private int currentPage;
    private int previousPage;
    private int nextPage;
    private int totalPages;
    private int totalGoals;



    public Pager(Integer page, Page aPageOfGoals) {
        this.previousPage = page - 1;
        this.nextPage = page + 1;

        //used to check to see if there really is a next page
        if (nextPage >= aPageOfGoals.getTotalPages()){
            nextPage = -1;
        }
    }



    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalGoals() {
        return totalGoals;
    }

    public void setTotalGoals(int totalGoals) {
        this.totalGoals = totalGoals;
    }

    public boolean isNext(){
        return nextPage > 0;
    }

    public boolean isPrevious(){
        return previousPage >= 0;
    }


}
