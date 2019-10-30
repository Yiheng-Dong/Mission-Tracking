package com.example.groupie;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;

@Entity
public class Mission {
    @Id
    private int id;

    private String content;

    private String deadline;

    public int userid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id= id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content= content;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline= deadline;
    }

    public int getUserId() {
        return userid;
    }

    public void setUserId(int userid) {
        this.userid= userid;
    }
}
