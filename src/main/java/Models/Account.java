/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author PC
 */
public class Account {
    private int id;
    private String user_number;
    private String username;
    private String password;
    private String full_name;
    private int gender;
    private String email;
    private String dob;
    private String mobile;
    private int status;
    private int roleId;

    public Account() {
    }

    public Account(int id, String user_number, String username, String password, String full_name, int gender, String email, String dob, String mobile, int status, int roleId) {
        this.id = id;
        this.user_number = user_number;
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

    public Account(String user_number, String username, String password, String full_name, int gender, String email, String dob, String mobile, int status, int roleId) {
        this.user_number = user_number;
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

    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
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
        return "Account{" + "id=" + id + ", user_number=" + user_number + ", username=" + username + ", password=" + password + ", full_name=" + full_name + ", gender=" + gender + ", email=" + email + ", dob=" + dob + ", mobile=" + mobile + ", status=" + status + ", roleId=" + roleId + '}';
    }
}
