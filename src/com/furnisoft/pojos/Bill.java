
package com.furnisoft.pojos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="bill")
public class Bill {
    @Id
    @Column(name="bill_no")
    private long billNo;
    
    @Column(name="bill_date")
    private String billDate;
    
    @Column(name="customer_name")
    private String customerName;
    
    @Column(name="customer_address")
    private String customerAddress;
    
    @Column(name="customer_no")
    private String customerContactNumber;
    
    @Column(name="payment_status")
    private String paymentStatus;
    
    @Column(name="total")
    private double total;
    
    @Column(name="vat_amount")
    private double vatAmount;
    
    @Column(name="net_total")
    private double netTotal;
    
    @Column(name="discount_amount")
    private double discountAmount;
    
    @Column(name="grand_total")
    private double grandTotal;
    
    @Column(name="bill_name")
    private String billName;
    
    @Column(name="status")
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(
            name="bill_sold_items",
            joinColumns = @JoinColumn(name="bill_bill_no")
    )
    private List<SoldItems> soldItems = new ArrayList<SoldItems>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "bill_amounts_paid",
            joinColumns = @JoinColumn(name="bill_bill_no")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<AmountPaid> amountPaid = new ArrayList<AmountPaid>();
    
    public Bill() {
        this.billNo = 0;
        this.billDate = "1111-11-11";
        this.customerName = "";
        this.customerAddress = "";
        this.customerContactNumber = "";
        this.paymentStatus = "";
        this.total = 0;
        this.vatAmount = 0;
        this.netTotal = 0;
        this.discountAmount = 0;
        this.grandTotal = 0;
        this.status = true;
        this.billName = "";
    }

    public Bill(long billNo, String billDate, String customerName, String customerAddress, String customerContactNumber, String paymentStatus, double total, double vatAmount, double netTotal, double discountAmount, double grandTotal, String billName, boolean status) {
        this.billNo = billNo;
        this.billDate = billDate;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerContactNumber = customerContactNumber;
        this.paymentStatus = paymentStatus;
        this.total = total;
        this.vatAmount = vatAmount;
        this.netTotal = netTotal;
        this.discountAmount = discountAmount;
        this.grandTotal = grandTotal;
        this.billName = billName;
        this.status = status;
    }

    
    public Bill(Bill b) {
        this.billNo = b.billNo;
        this.billDate = b.billDate;
        this.customerName = b.customerName;
        this.customerAddress = b.customerAddress;
        this.customerContactNumber = b.customerContactNumber;
        this.paymentStatus = b.paymentStatus;
        this.total = b.total;
        this.vatAmount = b.vatAmount;
        this.netTotal = b.netTotal;
        this.discountAmount = b.discountAmount;
        this.grandTotal = b.grandTotal;
        this.billName = b.billName;
    }

    public void setBillNo(long billNo) {
        this.billNo = billNo;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerContactNumber(String customerContactNumber) {
        this.customerContactNumber = customerContactNumber;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setVatAmount(double vatAmount) {
        this.vatAmount = vatAmount;
    }

    public void setNetTotal(double netTotal) {
        this.netTotal = netTotal;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public void setSoldItems(List<SoldItems> soldItems) {
        this.soldItems = soldItems;
    }

    public long getBillNo() {
        return billNo;
    }

    public String getBillDate() {
        return billDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerContactNumber() {
        return customerContactNumber;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public double getTotal() {
        return total;
    }

    public double getVatAmount() {
        return vatAmount;
    }

    public double getNetTotal() {
        return netTotal;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public List<SoldItems> getSoldItems() {
        return soldItems;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }
    
    public String getBillName() {
        return billName;
    }

    public void setAmountPaid(List<AmountPaid> amountPaid) {
        this.amountPaid = amountPaid;
    }

    public List<AmountPaid> getAmountPaid() {
        return amountPaid;
    }
    
    @Override
    public String toString() {
        return "Bill{" + "billNo=" + billNo + ", billDate=" + billDate + ", customerName=" + customerName + ", customerAddress=" + customerAddress + ", customerContactNumber=" + customerContactNumber + ", paymentStatus=" + paymentStatus + ", total=" + total + ", vatAmount=" + vatAmount + ", netTotal=" + netTotal + ", discountAmount=" + discountAmount + ", grandTotal=" + grandTotal + ", billName=" + billName + ", status=" + status + ", soldItems=" + soldItems + '}';
    }
}
