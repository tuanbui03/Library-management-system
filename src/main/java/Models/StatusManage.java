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
public class StatusManage {

    private final IntegerProperty id;
    private final StringProperty name;

    public StatusManage() {
        id = new SimpleIntegerProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public void setId(int newId) {
        id.set(newId);
    }

    public void setName(String newName) {
        name.set(newName);
    }

    @Override
    public String toString() {
        return name.get();
    }
}
