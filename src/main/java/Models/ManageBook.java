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
public class ManageBook {
    private int id;
    private Date createdAt;
    private float price_per_book;
    private int accountId;
    private int bookId;
    private int statusId;

    public ManageBook() {
    }

    public ManageBook(int id, Date createdAt, float price_per_book, int accountId, int bookId, int statusId) {
        this.id = id;
        this.createdAt = createdAt;
        this.price_per_book = price_per_book;
        this.accountId = accountId;
        this.bookId = bookId;
        this.statusId = statusId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public float getPrice_per_book() {
        return price_per_book;
    }

    public void setPrice_per_book(float price_per_book) {
        this.price_per_book = price_per_book;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "ManageBook{" + "id=" + id + ", createdAt=" + createdAt + ", price_per_book=" + price_per_book + ", accountId=" + accountId + ", bookId=" + bookId + ", statusId=" + statusId + '}';
    }
    
}
