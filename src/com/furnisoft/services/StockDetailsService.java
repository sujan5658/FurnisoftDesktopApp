
package com.furnisoft.services;

import com.furnisoft.dao.StockDetailsDAO;
import com.furnisoft.pojos.Hardwares;
import com.furnisoft.pojos.Message;
import com.furnisoft.pojos.StockDetails;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class StockDetailsService implements StockDetailsDAO {
    private Message message;
    private Hardwares hardware;
    private StockDetails stockDetails;
    private SessionFactory factory;
    private Session session;
    
     public StockDetailsService(){
        this.message = new Message();
        this.stockDetails = new StockDetails();
        this.factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Hardwares.class)
                .addAnnotatedClass(StockDetails.class)
                .buildSessionFactory();
    }
    private void initSession(){
        this.session = this.factory.getCurrentSession();
    }

    @Override
    public Message updateStock(Hardwares hardware, StockDetails stockDetails,String stockAction) {
        this.initSession();
        try{
            this.session.beginTransaction();
            String HQL = "FROM Hardwares WHERE hardwareName=:varHardwareName AND category=:varCategory";
            Query query = this.session.createQuery(HQL);
            query.setParameter("varHardwareName",hardware.getHardwareName());
            query.setParameter("varCategory",hardware.getCategory());
            this.hardware = (Hardwares)query.uniqueResult();
            if(stockAction.equals("ADD")){
                this.hardware.setAvailableQuantity(this.hardware.getAvailableQuantity()+hardware.getAvailableQuantity());
            }
            else if(stockAction.equals("REMOVE")){
                this.hardware.setAvailableQuantity(this.hardware.getAvailableQuantity()-hardware.getAvailableQuantity());
            }
            List<StockDetails> stockDetailsList;
            stockDetailsList = this.hardware.getStockDetails();
            stockDetailsList.add(stockDetails);
            this.hardware.setStockDetails(stockDetailsList);
            this.session.saveOrUpdate(this.hardware);
            this.session.getTransaction().commit();
            this.message.setMessageStatus(false);
            this.message.setMessage("Hardware Stock Updated.!!");
        }catch(Exception er){
            System.out.println("Error : "+er.getMessage());
            this.message.setMessageStatus(true);
            this.message.setMessage("Internal Error.!!!");
        }
        return this.message;
    }
}
