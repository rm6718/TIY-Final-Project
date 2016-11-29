package com.ironyard.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by reevamerchant on 11/7/16.
 */
@Entity
public class Goal {
    private String item;
    private String dateToBeCompleted;
    private String comments;
    private boolean accomplished;

    public Goal() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDateToBeCompleted() {
        return dateToBeCompleted;
    }

    public void setDateToBeCompleted(String dateToBeCompleted) {
        this.dateToBeCompleted = dateToBeCompleted;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAccomplished() {
        return accomplished;
    }

    public void setAccomplished(boolean accomplished) {
        this.accomplished = accomplished;
    }

    public Goal(String item, String dateToBeCompleted, String comments) {
        this.item = item;
        this.dateToBeCompleted = dateToBeCompleted;
        this.comments = comments;
    }
}
