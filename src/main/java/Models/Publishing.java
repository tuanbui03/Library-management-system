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
public class Publishing {
    private int id;
    private String name;
    private String address;
    private Date co_year;

    public Publishing() {
    }

    public Publishing(int id, String name, String address, Date co_year) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.co_year = co_year;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCo_year() {
        return co_year;
    }

    public void setCo_year(Date co_year) {
        this.co_year = co_year;
    }

    @Override
    public String toString() {
        return "Publishing{" + "id=" + id + ", name=" + name + ", address=" + address + ", co_year=" + co_year + '}';
    }
}
