/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import javafx.beans.property.*;

/**
 *
 * @author PC
 */
public class ManageBook {

    private final IntegerProperty index;
    private final IntegerProperty id;
    private final FloatProperty price_per_book;
    private final Account account;
    private final Book book;
    private final StatusManage status;
    private final StringProperty createdAt;
    private final StringProperty updatedAt;

    public ManageBook() {
        index = new SimpleIntegerProperty(this, "index");
        id = new SimpleIntegerProperty(this, "id");
        price_per_book = new SimpleFloatProperty(this, "price_per_book");
        account = new Account();
        book = new Book();
        status = new StatusManage();
        createdAt = new SimpleStringProperty(this, "createdAt");
        updatedAt = new SimpleStringProperty(this, "updatedAt");
    }

    public IntegerProperty indexProperty() {
        return index;
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public FloatProperty pricePerBookProperty() {
        return price_per_book;
    }
    
    public Account getAccount(){
        return account;
    }
    
    public Book getBook(){
        return book;
    }
    
    public StatusManage getStatus(){
        return status;
    }

    public StringProperty createdAtProperty() {
        return createdAt;
    }

    public StringProperty updatedAtProperty() {
        return updatedAt;
    }

    public int getIndex() {
        return index.get();
    }

    public int getId() {
        return id.get();
    }

    public float getPricePerBook() {
        return price_per_book.get();
    }

    public String getCreatedAt() {
        return createdAt.get();
    }

    public String getUpdatedAt() {
        return updatedAt.get();
    }
    
    public void setIndex(int index) {
        this.index.set(index);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setPricePerBook(float price_per_book) {
        this.price_per_book.set(price_per_book);
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt.set(createdAt);
    }

    public void setUpdatedAt(String udpatedAt) {
        this.updatedAt.set(udpatedAt);
    }
}
