
package com.furnisoft.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="stock_details")
public class StockDetails {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    
    @Column(name="quantity")
    private int quantity;
    
    @Column(name="action")
    private String action;
    
    @Column(name="comment")
    private String comment;
    
    @Column(name="reg_date")
    private String regDate;
    
    @Column(name="exp_date")
    private String expDate;
    
    @Column(name="status")
    private boolean status;
    

    public StockDetails() {
        this.id = 0;
        this.quantity = 0;
        this.action = "";
        this.comment = "";
        this.regDate = "1111-11-11";
        this.expDate = "1111-11-11";
        this.status = true;
    }
    
    public StockDetails(long id, int quantity, String action, String comment, String regDate, String expDate, boolean status) {
        this.id = id;
        this.quantity = quantity;
        this.action = action;
        this.comment = comment;
        this.regDate = regDate;
        this.expDate = expDate;
        this.status = status;
    }
    
    public StockDetails(StockDetails sd) {
        this.id = sd.id;
        this.quantity = sd.quantity;
        this.action = sd.action;
        this.comment = sd.comment;
        this.regDate = sd.regDate;
        this.expDate = sd.expDate;
        this.status = sd.status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getAction() {
        return action;
    }

    public String getComment() {
        return comment;
    }

    public String getRegDate() {
        return regDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "StockDetails{" + "id=" + id + ", quantity=" + quantity + ", action=" + action + ", comment=" + comment + ", regDate=" + regDate + ", expDate=" + expDate + ", status=" + status +'}';
    }
    
}
