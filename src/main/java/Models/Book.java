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
public class Book {
    private int id;
    private String name;
    private String image;
    private Date co_year;
    private float price;
    private int quantity;
    private String description;
    private int categoryId;
    private int authorId;
    private int publishId;

    public Book() {
    }

    public Book(int id, String name, String image, Date co_year, float price, int quantity, String description, int categoryId, int authorId, int publishId) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.co_year = co_year;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.categoryId = categoryId;
        this.authorId = authorId;
        this.publishId = publishId;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCo_year() {
        return co_year;
    }

    public void setCo_year(Date co_year) {
        this.co_year = co_year;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPublishId() {
        return publishId;
    }

    public void setPublishId(int publishId) {
        this.publishId = publishId;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name=" + name + ", image=" + image + ", co_year=" + co_year + ", price=" + price + ", quantity=" + quantity + ", description=" + description + ", categoryId=" + categoryId + ", authorId=" + authorId + ", publishId=" + publishId + '}';
    }
}
