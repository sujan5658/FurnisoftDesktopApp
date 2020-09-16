
package com.furnisoft.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shop_details")
public class ShopDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="shop_name")
    private String shopName;
    
    @Column(name="shop_address")
    private String shopAddress;
    
    @Column(name="vat_no")
    private long vatNo;

    public ShopDetails() {
        this.id = 1;
        this.shopName = "";
        this.shopAddress = "";
        this.vatNo = 0;
    }
    
    public ShopDetails(int id, String shopName, String shopAddress, long vatNo) {
        this.id = id;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.vatNo = vatNo;
    }
    
    public ShopDetails(ShopDetails sd) {
        this.id = sd.id;
        this.shopName = sd.shopName;
        this.shopAddress = sd.shopAddress;
        this.vatNo = sd.vatNo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public void setVatNo(long vatNo) {
        this.vatNo = vatNo;
    }

    public int getId() {
        return id;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public long getVatNo() {
        return vatNo;
    }

    @Override
    public String toString() {
        return "ShopDetails{" + "id=" + id + ", shopName=" + shopName + ", shopAddress=" + shopAddress + ", panNo=" + vatNo + '}';
    }

}
