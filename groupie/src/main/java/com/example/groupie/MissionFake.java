package com.example.groupie;

import javax.validation.constraints.Size;

public class MissionFake {
    private int id;

    @Size(min=1)
    private String content;

    @Size(min=1)
    private String deadline;

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
}
