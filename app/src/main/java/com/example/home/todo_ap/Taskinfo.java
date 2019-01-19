package com.example.home.todo_ap;

public class Taskinfo {
    private    String taskname,date,time,comment,priorty;

    public Taskinfo(String taskname, String date, String time, String comment, String priorty) {
        this.taskname = taskname;
        this.date = date;
        this.time = time;
        this.comment = comment;
        this.priorty = priorty;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPriorty(String priorty) {
        this.priorty = priorty;
    }

    public String getTaskname() {
        return taskname;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getComment() {
        return comment;
    }

    public String getPriorty() {
        return priorty;
    }
}
