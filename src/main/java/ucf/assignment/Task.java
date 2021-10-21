package ucf.assignment;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Reese Spector
 */

import javafx.scene.control.ComboBox;

public class Task {

    int id;
    String name, description, status, date;

    //declare the public task method
    //id, title, description, date, status
    public Task(int id, String title, String description, String date, String status) {
        this.id = id;
        this.name = title;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    //getters - task data
    public int getId() {
        return id;
    }
    public String getTitle() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getStatus() {
        return status;
    }
    public String getDate() {
        return date;
    }

    //setters - task data
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.name = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setDate(String date) {
        this.date = date;
    }


}
