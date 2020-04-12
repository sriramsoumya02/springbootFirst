package com.soumya.springbootFirst.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.util.Date;

public class Todo {
    private int id;
    private String user;
    @Size(min = 10, message = "description should be 10 charecters")
    private String desc;
    @Future(message = "please mention future date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")//(pattern = "MM-dd-yyyy")
    private Date targetDate;
    @AssertFalse(message = "it should be always false")
    private boolean isDone;

    public Todo() {
        super();
    }

    public Todo(int id, String user, String desc, Date targetDate, boolean isDone) {
        this.id = id;
        this.user = user;
        this.desc = desc;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Todo arg = (Todo) obj;
        if (id != arg.getId()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format(" Todo [id= %s,user=%s,description=%s,targetdate=%s,isDone=%s]", this.id, user, desc, targetDate.toString(), isDone);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
