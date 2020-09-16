
package com.furnisoft.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    
    @Column(name="category_name")
    private String categoryName;
    
    @Column(name="reg_date")
    private String regDate;
    
    @Column(name="exp_date")
    private String expDate;
    
    @Column(name="status")
    private boolean status;

    public Category() {
        this.id = 0;
        this.categoryName = "";
        this.regDate = "1111-11-11";
        this.expDate = "1111-11-11";
        this.status = true;
    }
    
    public Category(long id, String categoryName, String regDate, String expDate, boolean status) {
        this.id = id;
        this.categoryName = categoryName;
        this.regDate = regDate;
        this.expDate = expDate;
        this.status = status;
    }
    
    public Category(Category cat) {
        this.id = cat.id;
        this.categoryName = cat.categoryName;
        this.regDate = cat.regDate;
        this.expDate = cat.expDate;
        this.status = cat.status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getCategoryName() {
        return categoryName;
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
        return "Category{" + "id=" + id + ", categoryName=" + categoryName + ", regDate=" + regDate + ", expDate=" + expDate + ", status=" + status + '}';
    }
  
}
