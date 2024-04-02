package com.example.home3;

public class ProductDataModel {
    String name, period, location;
    String price;

    ProductDataModel() {

    }

    public ProductDataModel(String name, String price, String location,String period) {
        this.name = name;
        this.price = price;
        this.location = location;
        this.period = period;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {this.price= price;}


    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
//
//    public String getPurl() {
//        return purl;
//    }
//
//    public void setPurl(String purl) {
//        this.purl = purl;
//    }

}