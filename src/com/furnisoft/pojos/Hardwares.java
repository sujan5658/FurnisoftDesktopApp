
package com.furnisoft.pojos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="hardwares")
public class Hardwares {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    
    @Column(name="hardware_name")
    private String hardwareName;
    
    @Column(name="price")
    private double price;
    
    @Column(name="available_quantity")
    private int availableQuantity;
    
    @Column(name="category")
    private String category;
    
    @Column(name="size")
    private String size;
    
    @Column(name="reg_date")
    private String regDate;
    
    @Column(name="exp_date")
    private String expDate;
    
    @Column(name="status")
    private boolean status;
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="hardwares_stock_details",
            joinColumns = @JoinColumn(name="hardwares_id")
            //inverseJoinColumns= @JoinColumn(name="stock_id")
    )
    private List<StockDetails> stockDetails = new ArrayList<StockDetails>();

    public Hardwares() {
        this.id = 0;
        this.hardwareName = "";
        this.price = 0.0;
        this.availableQuantity = 0;
        this.size = "";
        this.regDate = "1111-11-11";
        this.expDate = "1111-11-11";
        this.status = true;
        this.stockDetails = null;
    }

    public Hardwares(long id, String hardwareName, double price, int quantity, String size, String regDate, String expDate, boolean status, List<StockDetails> stockDetails) {
        this.id = id;
        this.hardwareName = hardwareName;
        this.price = price;
        this.availableQuantity = quantity;
        this.size = size;
        this.regDate = regDate;
        this.expDate = expDate;
        this.status = status;
        this.stockDetails = stockDetails;
    }
    
    public Hardwares(Hardwares h) {
        this.id = h.id;
        this.hardwareName = h.hardwareName;
        this.price = h.price;
        this.availableQuantity = h.availableQuantity;
        this.size = h.size;
        this.regDate = h.regDate;
        this.expDate = h.expDate;
        this.status = h.status;
        this.stockDetails = h.stockDetails;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHardwareName(String hardwareName) {
        this.hardwareName = hardwareName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailableQuantity(int quantity) {
        this.availableQuantity = quantity;
    }

    public void setSize(String size) {
        this.size = size;
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

    public void setStockDetails(List<StockDetails> stockDetails) {
        this.stockDetails = stockDetails;
    }

    public long getId() {
        return id;
    }

    public String getHardwareName() {
        return hardwareName;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public String getSize() {
        return size;
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

    public List<StockDetails> getStockDetails() {
        return stockDetails;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Hardwares{" + "id=" + id + ", hardwareName=" + hardwareName + ", price=" + price + ", availableQuantity=" + availableQuantity + ", category=" + category + ", size=" + size + ", regDate=" + regDate + ", expDate=" + expDate + ", status=" + status + ", stockDetails=" + stockDetails + '}';
    }
}
