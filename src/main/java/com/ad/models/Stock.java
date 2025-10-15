package com.ad.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import sw.IStock;

public class Stock implements IStock {

    private SimpleDoubleProperty price;
    private SimpleStringProperty name;

    public Stock(String name, Double price) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public String getName() {
        return name.get();
    }

    public Double getPrice() {
        return price.get();
    }

    public void setName(String n) {
        name.set(n);
    }

    public void setPrice(Double d) {
        price.set(d);
    }

    @Override
    public String toString() {
        return getName() +" trading at: "+getPrice();
    }
}
