package com.molin.project200908.pojo;

public class Item {
    private int id;
    private String code;
    private String name;
    private char sex;
    private String age;
    private double price;
    private char type;
    private String attention;
    private String remark;

    public Item() {
    }

    public Item(int id, String code, String name, char sex, String age, double price, char type, String attention, String remark) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.price = price;
        this.type = type;
        this.attention = attention;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age='" + age + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", attention='" + attention + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
