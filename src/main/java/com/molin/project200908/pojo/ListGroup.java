package com.molin.project200908.pojo;

public class ListGroup {
    private String month;
    private int sumRes;
    private int sumNum;
    private double rate;

    @Override
    public String toString() {
        return "ListGroup{" +
                "month=" + month +
                ", sumRes=" + sumRes +
                ", sumNum=" + sumNum +
                ", rate=" + rate +
                '}';
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getSumRes() {
        return sumRes;
    }

    public void setSumRes(int sumRes) {
        this.sumRes = sumRes;
    }

    public int getSumNum() {
        return sumNum;
    }

    public void setSumNum(int sumNum) {
        this.sumNum = sumNum;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public ListGroup() {
    }

    public ListGroup(String month, int sumRes, int sumNum, double rate) {
        this.month = month;
        this.sumRes = sumRes;
        this.sumNum = sumNum;
        this.rate = rate;
    }
}
