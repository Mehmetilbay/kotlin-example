package com.example.kotlinexample.models;

import java.math.BigDecimal;

public class ChartItemModelJava {

    private BigDecimal averageRate;
    private String rateDate;

    public ChartItemModelJava(BigDecimal averageRate, String rateDate) {
        this.averageRate = averageRate;
        this.rateDate = rateDate;
    }

    public BigDecimal getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(BigDecimal averageRate) {
        this.averageRate = averageRate;
    }

    public String getRateDate() {
        return rateDate;
    }

    public void setRateDate(String rateDate) {
        this.rateDate = rateDate;
    }
}
