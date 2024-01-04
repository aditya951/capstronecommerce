package com.example.capstronecommerce.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Rating {

    private double rate;
    private int count;

    Rating(double rate,int count) {
        this.rate = rate;
        this.count = count;
    }
    Rating() {
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
