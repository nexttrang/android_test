package com.next.androidtest.data.model;

import java.io.Serializable;

public class Visitor implements Serializable {

    private String id;
    private String timeStamp;
    private String visitorName;
    private String type;
    private String status;

    public Visitor(String timeStamp, String visitorName, String type, String status) {
        this.timeStamp = timeStamp;
        this.visitorName = visitorName;
        this.type = type;
        this.status = status;
    }

    public Visitor(String id, String timeStamp, String visitorName, String type, String status) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.visitorName = visitorName;
        this.type = type;
        this.status = status;
    }

//Getter

    public String getId() {
        return id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    //Setter

    public void setId(String id) {
        this.id = id;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
