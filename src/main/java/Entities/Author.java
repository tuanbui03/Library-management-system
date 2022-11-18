/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author PC
 */
public class Author {
    private int id;
    private String name;
    private Date dob;
    private String sign_name;

    public Author() {
    }

    public Author(int id, String name, Date dob, String sign_name) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.sign_name = sign_name;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSign_name() {
        return sign_name;
    }

    public void setSign_name(String sign_name) {
        this.sign_name = sign_name;
    }

    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", name=" + name + ", dob=" + dob + ", sign_name=" + sign_name + '}';
    }
}
