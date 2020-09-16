
package com.furnisoft.services;

import com.furnisoft.dao.ShopDetailsDAO;
import com.furnisoft.pojos.Message;
import com.furnisoft.pojos.ShopDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ShopDetailsService implements ShopDetailsDAO {

    private Message message;
    private SessionFactory factory;
    private Session session;
    
    public ShopDetailsService(){
        this.message = new Message();
        this.factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(ShopDetails.class)
                .buildSessionFactory();
    }
    private void initSession(){
        this.session = this.factory.getCurrentSession();
    }
    @Override
    public Message updateShop(ShopDetails shopDetails) {
        try{
            this.initSession();
            this.session.beginTransaction();
            this.session.saveOrUpdate(shopDetails);
            this.session.getTransaction().commit();
            this.message.setMessageStatus(false);
            this.message.setMessage("Shop Details Updated Successfully.");
        }catch(Exception er){
            this.message.setMessageStatus(true);
            this.message.setMessage("Internal Error.!!!");
        }
        return this.message;
    }

    @Override
    public ShopDetails getShopDetails() {
        ShopDetails shopDetails = new ShopDetails();
        this.initSession();
        try{
            this.session.beginTransaction();
            shopDetails = this.session.get(ShopDetails.class,1);
            this.session.getTransaction().commit();
        }catch(Exception er){
            shopDetails = null;
        }
        return shopDetails;
    }
}
