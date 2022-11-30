/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;

/**
 *
 * @author PC
 */
public class Category {

    private final IntegerProperty index;
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty createdAt;
    private final StringProperty updatedAt;

    public Category() {
        index = new SimpleIntegerProperty(this, "index");
        id = new SimpleIntegerProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        createdAt = new SimpleStringProperty(this, "createdAt");
        updatedAt = new SimpleStringProperty(this, "updatedAt");
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

    public String getName() {
        return name.get();
    }

    public String getCreatedAt() {
        return createdAt.get();
    }

    public String getUpdatedAt() {
        return updatedAt.get();
    }

    public void setIndex(int newIndex) {
        index.set(newIndex);
    }

    public void setId(int newId) {
        id.set(newId);
    }

    public void setName(String newName) {
        name.set(newName);
    }

    public void setCreatedAt(String newCreatedAt) {
        createdAt.set(newCreatedAt);
    }

    public void setUpdatedAt(String newUpdatedAt) {
        updatedAt.set(newUpdatedAt);
    }

    @Override
    public String toString() {
        return name.get();
    }
}
