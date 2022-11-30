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
public class Account {

    private final IntegerProperty index;
    private final IntegerProperty id;
    private final StringProperty UID;
    private final StringProperty avatar;
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty full_name;
    private final IntegerProperty gender;
    private final StringProperty email;
    private final StringProperty dob;
    private final StringProperty mobile;
    private final IntegerProperty status;
    private final IntegerProperty roleId;
    private final StringProperty roleName;
    private final StringProperty createdAt;
    private final StringProperty updatedAt;

    public Account() {
        index = new SimpleIntegerProperty(this, "index");
        id = new SimpleIntegerProperty(this, "id");
        UID = new SimpleStringProperty(this, "UID");
        avatar = new SimpleStringProperty(this, "avatar");
        username = new SimpleStringProperty(this, "username");
        password = new SimpleStringProperty(this, "password");
        full_name = new SimpleStringProperty(this, "full_name");
        gender = new SimpleIntegerProperty(this, "gender");
        email = new SimpleStringProperty(this, "email");
        dob = new SimpleStringProperty(this, "dob");
        mobile = new SimpleStringProperty(this, "mobile");
        status = new SimpleIntegerProperty(this, "status");
        roleId = new SimpleIntegerProperty(this, "roleId");
        roleName = new SimpleStringProperty(this, "roleName");
        createdAt = new SimpleStringProperty(this, "createdAt");
        updatedAt = new SimpleStringProperty(this, "updatedAt");
    }

    public IntegerProperty indexProperty() {
        return index;
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty UIDProperty() {
        return UID;
    }
    
    public StringProperty avatarProperty() {
        return avatar;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty full_nameProperty() {
        return full_name;
    }

    public IntegerProperty genderProperty() {
        return gender;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty dobProperty() {
        return dob;
    }

    public StringProperty mobileProperty() {
        return mobile;
    }

    public IntegerProperty statusProperty() {
        return status;
    }

    public IntegerProperty roleIdProperty() {
        return roleId;
    }

    public StringProperty roleNameProperty() {
        return roleName;
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

    public void setIndex(int index) {
        this.index.set(index);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getUID() {
        return UID.get();
    }

    public void setUID(String UID) {
        this.UID.set(UID);
    }

    public String getAvatar() {
        return avatar.get();
    }

    public void setAvatar(String avatar) {
        this.avatar.set(avatar);
    }
    
    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getFull_name() {
        return full_name.get();
    }

    public void setFull_name(String full_name) {
        this.full_name.set(full_name);
    }

    public int getGender() {
        return gender.get();
    }

    public void setGender(int gender) {
        this.gender.set(gender);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getDob() {
        return dob.get();
    }

    public void setDob(String dob) {
        this.dob.set(dob);
    }

    public String getMobile() {
        return mobile.get();
    }

    public void setMobile(String mobile) {
        this.mobile.set(mobile);
    }

    public int getStatus() {
        return status.get();
    }

    public void setStatus(int status) {
        this.status.set(status);
    }

    public int getRoleId() {
        return roleId.get();
    }

    public void setRoleId(int roleId) {
        this.roleId.set(roleId);
    }

    public String getRoleName() {
        return roleName.get();
    }

    public void setRoleName(String roleName) {
        this.roleName.set(roleName);
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
        return this.username.get();
    }
}
