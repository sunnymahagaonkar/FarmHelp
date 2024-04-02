package com.example.home3;

public class DataHolder {
    String name,course,location;

    public DataHolder(String name, String course, String location) {
        this.name = name;
        this.course = course;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
