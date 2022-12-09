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
public class Borrow {
 
    private final IntegerProperty index;
    private final IntegerProperty id;
    private final StringProperty borrowAt;
    private final IntegerProperty time_out;
    private final StringProperty refundAt;
    private final FloatProperty amount_of_pay;
    private final IntegerProperty manageId;
    private final IntegerProperty statusId;

//    join manage_book, status_borrow
    private final IntegerProperty bookid;
    private final IntegerProperty accountid;

    private final StringProperty statusName;
    private final StringProperty bookName;
    private final StringProperty accountName;

    public Borrow() {
        this.index = new SimpleIntegerProperty(this, "index");
        this.id = new SimpleIntegerProperty(this, "id");
        this.borrowAt = new SimpleStringProperty(this, "borrowAt");
        this.time_out = new SimpleIntegerProperty(this, "time_out");
        this.refundAt = new SimpleStringProperty(this, "refundAt");
        this.amount_of_pay = new SimpleFloatProperty(this, "amount_of_pay");
        this.manageId = new SimpleIntegerProperty(this, "manageId");
        this.statusId = new SimpleIntegerProperty(this, "statusId");
        this.statusName = new SimpleStringProperty(this, "statusName");
        this.bookName = new SimpleStringProperty(this, "bookName");
        this.accountName = new SimpleStringProperty(this, "accountName");
        this.accountid = new SimpleIntegerProperty(this, "accountid");
        this.bookid = new SimpleIntegerProperty(this, "bookid");

    }

    public IntegerProperty getBookid() {
        return bookid;
    }

    public IntegerProperty getAccountid() {
        return accountid;
    }

    public void setBookID(int index) {
        this.bookid.set(index);
    }

    public void setAccountID(int index) {
        this.accountid.set(index);
    }

    public StringProperty bookNameProperty() {
        return bookName;
    }

    public StringProperty accountNameProperty() {
        return accountName;
    }

    public IntegerProperty indexProperty() {
        return index;
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty borrowAtProperty() {
        return borrowAt;
    }

    public IntegerProperty time_outProperty() {
        return time_out;
    }

    public StringProperty refundAtProperty() {
        return refundAt;
    }

    public FloatProperty amount_of_payProperty() {
        return amount_of_pay;
    }

    public IntegerProperty manageIdProperty() {
        return manageId;
    }

    public IntegerProperty statusIdProperty() {
        return statusId;
    }

    public StringProperty statusNameProperty() {
        return statusName;
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

    public String getBorrowAt() {
        return borrowAt.get();
    }

    public void setBorrowAt(String borrowAt) {
        this.borrowAt.set(borrowAt);
    }

    public int getTime_out() {
        return time_out.get();
    }

    public void setTime_out(int time_out) {
        this.time_out.set(time_out);
    }

    public String getRefundAt() {
        return refundAt.get();
    }

    public void setRefundAt(String refundAt) {
        this.refundAt.set(refundAt);
    }

    public float getAmount_of_pay() {
        return amount_of_pay.get();
    }

    public void setAmount_of_pay(float amount_of_pay) {
        this.amount_of_pay.set(amount_of_pay);
    }

    public int getManageId() {
        return manageId.get();
    }

    public void setBookName(String book) {
        this.bookName.set(book);
    }

    public void setAccountName(String account) {
        this.accountName.set(account);
    }

    public String getBookName() {
        return bookName.get();
    }

    public String getAccountName() {
        return accountName.get();
    }

    public void setManageId(int manageId) {
        this.manageId.set(manageId);
    }

    public int getStatusId() {
        return statusId.get();
    }

    public void setStatusId(int statusId) {
        this.statusId.set(statusId);
    }

    public String getStatusName() {
        return statusName.get();
    }

    public void setStatusName(String statusId) {
        this.statusName.set(statusId);
    }

    @Override
    public String toString() {
        return "Borrow{" + "id=" + id.get()
                + ", borrowAt=" + borrowAt.get()
                + ", time_out=" + time_out.get()
                + ", refundAt=" + refundAt.get()
                + ", amount_of_pay=" + amount_of_pay.get()
                + ", manageId=" + manageId.get()
                + ", statusId=" + statusId.get()
                + ", bookId=" + bookid.get()
                + ", accountId=" + accountid.get()
                + ", statusName=" + statusName.get()
                + ", bookName=" + bookName.get()
                + ", accountName=" + accountName.get()
                + '}';
    }
}
