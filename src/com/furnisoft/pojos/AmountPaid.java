package com.furnisoft.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="amounts_paid")
public class AmountPaid {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    
    @Column(name="amount_paid")
    private double amountPaid;
    
    @Column(name="amount_due")
    private double amountDue;
    
    @Column(name="date")
    private String date;

    public AmountPaid() {
        this.id = 0;
        this.amountPaid = 0;
        this.amountDue = 0;
        this.date = "1111-11-11";
    }

    public AmountPaid(long id, double amountPaid, double amountDue, String date) {
        this.id = id;
        this.amountPaid = amountPaid;
        this.amountDue = amountDue;
        this.date = date;
    }
    
    public AmountPaid(AmountPaid ap) {
        this.id = ap.id;
        this.amountPaid = ap.amountPaid;
        this.amountDue = ap.amountDue;
        this.date = ap.date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "AmountPaid{" + "id=" + id + ", amountPaid=" + amountPaid + ", amountDue=" + amountDue + ", date=" + date + '}';
    }
    
    
}
