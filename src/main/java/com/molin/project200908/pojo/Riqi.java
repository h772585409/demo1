package com.molin.project200908.pojo;

public class Riqi {
    private String year;
    private String month;
    private String day;

    @Override
    public String toString() {
        return "Riqi{" +
                "year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                '}';
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Riqi() {
    }

    public Riqi(String year, String month) {
        this.year = year;
        this.month = month;
    }

    public Riqi(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
