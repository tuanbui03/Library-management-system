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
public class Borrow {
    private int id;
    private Date borrowAt;
    private int time_out;
    private Date refundAt;
    private float amount_of_pay;
    private int manageId;
    private int statusId;

    public Borrow() {
    }

    public Borrow(int id, Date borrowAt, int time_out, Date refundAt, float amount_of_pay, int manageId, int statusId) {
        this.id = id;
        this.borrowAt = borrowAt;
        this.time_out = time_out;
        this.refundAt = refundAt;
        this.amount_of_pay = amount_of_pay;
        this.manageId = manageId;
        this.statusId = statusId;
    }

    public Borrow(Date borrowAt, int time_out, Date refundAt, float amount_of_pay, int manageId, int statusId) {
        this.borrowAt = borrowAt;
        this.time_out = time_out;
        this.refundAt = refundAt;
        this.amount_of_pay = amount_of_pay;
        this.manageId = manageId;
        this.statusId = statusId;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBorrowAt() {
        return borrowAt;
    }

    public void setBorrowAt(Date borrowAt) {
        this.borrowAt = borrowAt;
    }

    public int getTime_out() {
        return time_out;
    }

    public void setTime_out(int time_out) {
        this.time_out = time_out;
    }

    public Date getRefundAt() {
        return refundAt;
    }

    public void setRefundAt(Date refundAt) {
        this.refundAt = refundAt;
    }

    public float getAmount_of_pay() {
        return amount_of_pay;
    }

    public void setAmount_of_pay(float amount_of_pay) {
        this.amount_of_pay = amount_of_pay;
    }

    public int getManageId() {
        return manageId;
    }

    public void setManageId(int manageId) {
        this.manageId = manageId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "Borrow{" + "id=" + id + ", borrowAt=" + borrowAt + ", time_out=" + time_out + ", refundAt=" + refundAt + ", amount_of_pay=" + amount_of_pay + ", manageId=" + manageId + ", statusId=" + statusId + '}';
    }
    
}
