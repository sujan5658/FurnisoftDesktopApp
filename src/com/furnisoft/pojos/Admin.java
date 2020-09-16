
package com.furnisoft.pojos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="email")
    private String email;
    
    @Column(name="pass")
    private String pass;
    
    @Column(name="invalid_count")
    private int invalidCount;
    
    @Column(name="status")
    private boolean status;

    public Admin() {
        this.id = 0;
        this.email = "";
        this.pass = "";
        this.invalidCount = 0;
        this.status = true;
    }
    
    public Admin(int id, String email, String pass, int invalidCount, boolean status) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.invalidCount = invalidCount;
        this.status = status;
    }
    
    public Admin(Admin admin) {
        this.id = admin.id;
        this.email = admin.email;
        this.pass = admin.pass;
        this.invalidCount = admin.invalidCount;
        this.status = admin.status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setInvalidCount(int invalidCount) {
        this.invalidCount = invalidCount;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public int getInvalidCount() {
        return invalidCount;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", email=" + email + ", pass=" + pass + ", invalidCount=" + invalidCount + ", status=" + status + '}';
    }
   
}
