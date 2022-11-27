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
public class Publishing {

    private final IntegerProperty index;
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty address;
    private final StringProperty co_year;
    private final StringProperty createdAt;
    private final StringProperty updatedAt;

    public Publishing() {
        index = new SimpleIntegerProperty(this, "index");
        id = new SimpleIntegerProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        address = new SimpleStringProperty(this, "address");
        co_year = new SimpleStringProperty(this, "co_year");
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

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty co_yearProperty() {
        return co_year;
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

    public String getAddress() {
        return address.get();
    }

    public String getCoyear() {
        return co_year.get();
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

    public void setName(String name) {
        this.name.set(name);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setCoyear(String co_year) {
        this.co_year.set(co_year);
    }

    public void setCreatedAt(String newCreatedAt) {
        createdAt.set(newCreatedAt);
    }

    public void setUpdatedAt(String newUpdatedAt) {
        updatedAt.set(newUpdatedAt);
    }

    @Override
    public String toString() {
        return this.name.get();
    }
}
