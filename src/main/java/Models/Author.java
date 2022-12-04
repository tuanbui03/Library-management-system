/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;
import javafx.beans.property.*;

/**
 *
 * @author PC
 */
public class Author {

    private final IntegerProperty index;
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty dob;
    private final StringProperty sign_name;
    private final StringProperty createdAt;
    private final StringProperty updatedAt;

    public Author() {
        index = new SimpleIntegerProperty(this, "index");
        id = new SimpleIntegerProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        dob = new SimpleStringProperty(this, "dob");
        sign_name = new SimpleStringProperty(this, "sign_name");
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

    public StringProperty dobProperty() {
        return dob;
    }
    
    public StringProperty signNameProperty(){
        return sign_name;
    }
    
    public StringProperty createdAtProperty(){
        return createdAt;
    }
    
    public StringProperty updatedAtProperty(){
        return updatedAt;
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

    public String getDob() {
        return dob.get();
    }

    public void setDob(String dob) {
        this.dob.set(dob);
    }

    public String getSign_name() {
        return sign_name.get();
    }

    public void setSign_name(String sign_name) {
        this.sign_name.set(sign_name);
    }

    public String getCreatedAt() {
        return createdAt.get();
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt.set(createdAt);
    }

    public String getUpdatedAt() {
        return updatedAt.get();
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt.set(updatedAt);
    }

    @Override
    public String toString() {
        return this.name.get();
    }
}
