/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author PC
 */
public class Account {
    private int id;
    private String UID;
    private String username;
    private String password;
    private String full_name;
    private int gender;
    private String email;
    private Date dob;
    private String mobile;
    private int status;
    private int roleId;

    public Account() {
    }

    public Account(int id, String UID, String username, String password, String full_name, int gender, String email, Date dob, String mobile, int status, int roleId) {
        this.id = id;
        this.UID = UID;
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.gender = gender;
        this.email = email;
        this.dob = dob;
        this.mobile = mobile;
        this.status = status;
        this.roleId = roleId;
    }

    public Account(String UID, String username, String password, String full_name, int gender, String email, Date dob, String mobile, int status, int roleId) {
        this.UID = UID;
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.gender = gender;
        this.email = email;
        this.dob = dob;
        this.mobile = mobile;
        this.status = status;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
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

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", UID=" + UID + ", username=" + username + ", password=" + password + ", full_name=" + full_name + ", gender=" + gender + ", email=" + email + ", dob=" + dob + ", mobile=" + mobile + ", status=" + status + ", roleId=" + roleId + '}';
    }
}
