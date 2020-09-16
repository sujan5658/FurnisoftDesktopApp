
package com.furnisoft.services;

import com.furnisoft.dao.HardwareDAO;
import com.furnisoft.pojos.Hardwares;
import com.furnisoft.pojos.Message;
import com.furnisoft.pojos.StockDetails;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HardwareService implements HardwareDAO{

    private Message message;
    private Hardwares hardware;
    private SessionFactory factory;
    private Session session;
    
     public HardwareService(){
        this.message = new Message();
        this.factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Hardwares.class)
                .addAnnotatedClass(StockDetails.class)
                .buildSessionFactory();
    }
    private void initSession(){
        this.session = this.factory.getCurrentSession();
    }
    
    @Override
    public Message registerHardware(Hardwares hardware) {
       this.initSession();
       this.message = new Message();
       try{
           this.session.beginTransaction();
           this.session.save(hardware);
           this.session.getTransaction().commit();
           this.message.setMessageStatus(false);
           this.message.setMessage("Hardware register succeed.");
       }catch(Exception er){
           this.message.setMessageStatus(true);
           this.message.setMessage("Internal Error.!!!");
       }
       return this.message;
    }

    @Override
    public List<Hardwares> getHardwares() {
        List<Hardwares> hardwares = new ArrayList<Hardwares>();
        this.initSession();
        try{
            this.session.beginTransaction();
            hardwares = this.session.createQuery("FROM Hardwares h WHERE status=1 ORDER BY h.id DESC").list();
            this.session.getTransaction().commit();
        }catch(Exception er){
            hardwares = null;
        }
        return hardwares;
    }

    @Override
    public Message updateHardware(Hardwares hardware) {
        this.initSession();
        this.message = new Message();
        this.hardware = new Hardwares();
        try{
            this.session.beginTransaction();
            this.hardware = this.session.get(Hardwares.class, hardware.getId());
            this.hardware.setCategory(hardware.getCategory());
            this.hardware.setHardwareName(hardware.getHardwareName());
            this.hardware.setAvailableQuantity(hardware.getAvailableQuantity());
            this.hardware.setRegDate(hardware.getRegDate());
            this.hardware.setSize(hardware.getSize());
            this.hardware.setPrice(hardware.getPrice());
            this.session.getTransaction().commit();
            this.message.setMessageStatus(false);
            this.message.setMessage("Hardware Update Succeed..!!!");
        }catch(Exception er){
            System.out.println("Error : "+er.getMessage());
            this.message.setMessageStatus(true);
            this.message.setMessage("Internal Error.!!!");
        }
        return this.message;
    }

    @Override
    public void updateHardwares(String oldCategory,String newCategory) {
        this.initSession();
        List<Hardwares> hardwares = new ArrayList<Hardwares>();
        try{
            this.session.beginTransaction();
            hardwares = this.session.createQuery("FROM Hardwares WHERE status=1").list();
            for(Hardwares hardware : hardwares){
                if(hardware.getCategory().equals(oldCategory)){
                    hardware.setCategory(newCategory);
                }
            }
            this.session.getTransaction().commit();   
        }catch(Exception er){
            System.out.println("Error : "+er.getMessage());
        }
    }

    @Override
    public Message deleteHardware(Hardwares hardware) {
        this.initSession();
        this.message = new Message();
        this.hardware = new Hardwares();
        try{
            this.session.beginTransaction();
            this.hardware = this.session.get(Hardwares.class, hardware.getId());
            this.hardware.setStatus(false);
            this.hardware.setExpDate(hardware.getExpDate());
            this.session.getTransaction().commit();
            this.message.setMessageStatus(false);
            this.message.setMessage("Hardware deleted Successfull..!!!");
        }catch(Exception er){
            this.message.setMessageStatus(true);
            this.message.setMessage("Internal Error.!!!");
        }
        return this.message;
    }
}
