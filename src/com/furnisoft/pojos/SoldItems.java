
package com.furnisoft.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sold_items")
public class SoldItems {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    
    @Column(name="hardware_name")
    private String hardwareName;
    
    @Column(name="rate")
    private double rate;
    
    @Column(name="quantity")
    private int quantity;
    
    @Column(name="total")
    private double total;

    public SoldItems() {
        this.id = 0;
        this.hardwareName = "";
        this.rate = 0.0;
        this.quantity = 0;
        this.total = 0.0;
    }
    
    public SoldItems(long id, String hardwareName, double rate, int quantity, double total) {
        this.id = id;
        this.hardwareName = hardwareName;
        this.rate = rate;
        this.quantity = quantity;
        this.total = total;
    }
    
    public SoldItems(SoldItems si) {
        this.id = si.id;
        this.hardwareName = si.hardwareName;
        this.rate = si.rate;
        this.quantity = si.quantity;
        this.total = si.total;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHardwareName(String hardwareName) {
        this.hardwareName = hardwareName;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public String getHardwareName() {
        return hardwareName;
    }

    public double getRate() {
        return rate;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "SoldItems{" + "id=" + id + ", hardwareName=" + hardwareName + ", rate=" + rate + ", quantity=" + quantity + ", total=" + total + '}';
    }
}
