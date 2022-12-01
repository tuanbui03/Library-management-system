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
public class Book {

    private final IntegerProperty index;
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty image;
    private final StringProperty co_year;
    private final FloatProperty price;
    private final IntegerProperty quantity;
    private final StringProperty description;
    private final IntegerProperty categoryId;
    private final IntegerProperty authorId;
    private final IntegerProperty publishingId;
    private final StringProperty categoryName;
    private final StringProperty authorSignName;
    private final StringProperty publishingName;

    public Book() {
        index = new SimpleIntegerProperty(this, "index");
        id = new SimpleIntegerProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        image = new SimpleStringProperty(this, "image");
        co_year = new SimpleStringProperty(this, "co_year");
        price = new SimpleFloatProperty(this, "price");
        quantity = new SimpleIntegerProperty(this, "quantity");
        description = new SimpleStringProperty(this, "description");
        categoryId = new SimpleIntegerProperty(this, "categoryId");
        authorId = new SimpleIntegerProperty(this, "authorId");
        publishingId = new SimpleIntegerProperty(this, "publishingId");
        categoryName = new SimpleStringProperty(this, "categoryName");
        authorSignName = new SimpleStringProperty(this, "authorSignName");
        publishingName = new SimpleStringProperty(this, "publishingName");
    }

    public IntegerProperty indexProperty() {
        return index;
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty imageProperty() {
        return image;
    }

    public StringProperty coYearProperty() {
        return co_year;
    }

    public FloatProperty priceProperty() {
        return price;
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public IntegerProperty authorIdProperty() {
        return authorId;
    }

    public IntegerProperty publishingIdProperty() {
        return publishingId;
    }

    public IntegerProperty categoryIdProperty() {
        return categoryId;
    }
    
    public StringProperty authorSignNameProperty() {
        return authorSignName;
    }
    
    public StringProperty publishingNameProperty() {
        return publishingName;
    }
        
    public StringProperty categoryNameProperty() {
        return categoryName;
    }
    
    public int getIndex() {
        return index.get();
    }

    public void setIndex(int index) {
        this.index.set(index);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getImage() {
        return image.get();
    }

    public void setImage(String image) {
        this.image.set(image);
    }

    public String getCoyear() {
        return co_year.get();
    }

    public void setCoyear(String co_year) {
        this.co_year.set(co_year);
    }

    public Float getPrice() {
        return price.get();
    }

    public void setPrice(Float price) {
        this.price.set(price);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getCategoryId() {
        return categoryId.get();
    }

    public void setCategoryId(int categoryId) {
        this.categoryId.set(categoryId);
    }

    public int getAuthorId() {
        return authorId.get();
    }

    public void setAuthorId(int authorId) {
        this.authorId.set(authorId);
    }

    public int getPublishingId() {
        return publishingId.get();
    }

    public void setPublishingId(int publishingId) {
        this.publishingId.set(publishingId);
    }

    public String getCategoryName() {
        return categoryName.get();
    }

    public void setCategoryName(String categoryName) {
        this.categoryName.set(categoryName);
    }

    public String getAuthorSignName() {
        return authorSignName.get();
    }

    public void setAuthorSignName(String authorSignName) {
        this.authorSignName.set(authorSignName);
    }

    public String getPublishingName() {
        return publishingName.get();
    }

    public void setPublishingName(String publishingName) {
        this.publishingName.set(publishingName);
    }

    @Override
    public String toString() {
        return this.name.get();
    }
}
