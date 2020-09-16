
package com.furnisoft.services;

import com.furnisoft.dao.BillDAO;
import com.furnisoft.pojos.Bill;
import com.furnisoft.pojos.Message;
import com.furnisoft.pojos.SoldItems;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BillService implements BillDAO {
    
    private Message message;
    private SessionFactory factory;
    private Session session;
    private Bill bill;
    
    public BillService(){
        this.bill = new Bill();
        this.message = new Message();
        this.factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Bill.class)
                .addAnnotatedClass(SoldItems.class)
                .buildSessionFactory();
    }
    private void initSession(){
        this.session = this.factory.getCurrentSession();
    }
    @Override
    public Message saveBill(Bill bill) {
        this.initSession();
        try{
            this.session.beginTransaction();
            this.session.save(bill);
            this.session.getTransaction().commit();
            this.message.setMessageStatus(false);
            this.message.setMessage("Bill saved");
        }catch(Exception er){
           this.message.setMessageStatus(true);
           this.message.setMessage(er.getMessage());
        }
        return this.message;
    }

    @Override
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<Bill>();
        this.initSession();
        try{
            this.session.beginTransaction();
            bills = this.session.createQuery("FROM Bill b WHERE status=1 ORDER BY b.billNo DESC").list();
            this.session.getTransaction().commit();
        }catch(Exception er){
            bills =null;
        }
        return bills;
    }

    @Override
    public Message updateBill(Bill bill) {
        System.out.println("Bill : "+bill.getAmountPaid());
        this.initSession();
        try{
            this.session.beginTransaction();
            this.session.saveOrUpdate(bill);
            this.session.getTransaction().commit();
            this.message.setMessageStatus(false);
            this.message.setMessage("Bill Updated");
        }catch(Exception er){
           this.message.setMessageStatus(true);
           this.message.setMessage(er.getMessage());
        }
        return this.message;
    }

    @Override
    public Message deleteBill(Bill bill) {
        this.initSession();
        try{
            this.session.beginTransaction();
            this.bill = this.session.get(Bill.class, bill.getBillNo());
            this.bill.setStatus(false);
            this.session.getTransaction().commit();
            this.message.setMessageStatus(false);
            this.message.setMessage("Bill Deleted.");
        }catch(Exception er){
            this.message.setMessageStatus(true);
           this.message.setMessage(er.getMessage());
        }
        return this.message;
    }
}
