package com.molin.project200908.pojo;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Arrays;

public class Setmeal implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String code;
    private String helpCode;
    private char sex;
    private String age;
    private double price;
    private String remark;
    private String attention;
    private String img;
    private int[] groupIdArr;
    private MultipartFile picture;

    @Override
    public String toString() {
        return "Setmeal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", helpCode='" + helpCode + '\'' +
                ", sex=" + sex +
                ", age='" + age + '\'' +
                ", price=" + price +
                ", remark='" + remark + '\'' +
                ", attention='" + attention + '\'' +
                ", img='" + img + '\'' +
                ", groupIdArr=" + Arrays.toString(groupIdArr) +
                ", picture=" + picture +
                '}';
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public Setmeal() {
    }

    public int[] getGroupIdArr() {
        return groupIdArr;
    }

    public void setGroupIdArr(int[] groupIdArr) {
        this.groupIdArr = groupIdArr;
    }

    public Setmeal(int id, String name, String code, String helpCode, char sex, String age, double price, String remark, String attention, String img, int[] groupIdArr, MultipartFile picture) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.helpCode = helpCode;
        this.sex = sex;
        this.age = age;
        this.price = price;
        this.remark = remark;
        this.attention = attention;
        this.img = img;
        this.groupIdArr = groupIdArr;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHelpCode() {
        return helpCode;
    }

    public void setHelpCode(String helpCode) {
        this.helpCode = helpCode;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
