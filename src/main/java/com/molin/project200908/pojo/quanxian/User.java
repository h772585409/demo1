package com.molin.project200908.pojo.quanxian;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Arrays;
import java.util.Date;

public class User {
    private int id;
    @JsonFormat(pattern = "yyyy-MM-dd",locale = "zh",timezone = "GMT+8")
    private Date birthday;
    private String gender;
    private String username;
//    @JsonIgnore
    private String password;
    private String remark;
    private String station;
    private String telephone;
    private int[] roleArr;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", remark='" + remark + '\'' +
                ", station='" + station + '\'' +
                ", telephone='" + telephone + '\'' +
                ", roleArr=" + Arrays.toString(roleArr) +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int[] getRoleArr() {
        return roleArr;
    }

    public void setRoleArr(int[] roleArr) {
        this.roleArr = roleArr;
    }

    public User() {
    }

    public User(int id, Date birthday, String gender, String username, String password, String remark, String station, String telephone, int[] roleArr) {
        this.id = id;
        this.birthday = birthday;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.remark = remark;
        this.station = station;
        this.telephone = telephone;
        this.roleArr = roleArr;
    }
}
