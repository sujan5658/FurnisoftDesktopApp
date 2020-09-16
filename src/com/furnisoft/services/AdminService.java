
package com.furnisoft.services;

import com.furnisoft.dao.AdminDAO;
import com.furnisoft.pojos.Admin;
import com.furnisoft.pojos.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements AdminDAO{
    
    @Autowired
    private Admin admin,tmpAdmin;
    private Message message;
    private SessionFactory factory;
    private Session session;
    
    public AdminService(){
        this.admin = new Admin();
        this.tmpAdmin = new Admin();
        this.message = new Message();
        this.factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Admin.class)
                .buildSessionFactory();
    }
    public AdminService(Admin admin){
        this.admin = new Admin();
        this.tmpAdmin = new Admin();
        this.message = new Message();
        this.factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Admin.class)
                .buildSessionFactory();
        this.admin = admin;
    }
    
    private void initSession(){
        this.session = this.factory.getCurrentSession();
    }
    
    @Override
    public Message loginAdmin() {
       this.initSession();
       try{
           this.session.beginTransaction();
           String HQL = "FROM Admin WHERE email=:userEmail";
           Query query = this.session.createQuery(HQL);
           query.setParameter("userEmail",this.admin.getEmail());
           this.tmpAdmin = (Admin)query.uniqueResult();
           if(this.tmpAdmin!=null){
               BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
               
               if(encoder.matches(this.admin.getPass(),this.tmpAdmin.getPass())){
                  if(this.tmpAdmin.getInvalidCount()>=5){
                      this.message.setMessageStatus(true);
                      this.message.setMessage("Account Locked due to multiple Invalid Access.!!!!!");
                  }
                  else{
                      this.tmpAdmin.setInvalidCount(0);
                      this.message.setMessageStatus(false);
                      this.message.setMessage("Valid User");
                  }
               }
               else{
                   this.message.setMessageStatus(true);
                   this.message.setMessage("Invalid Password...!!!");
                   if(this.tmpAdmin.getInvalidCount()<5){
                       this.tmpAdmin.setInvalidCount(this.tmpAdmin.getInvalidCount()+1);
                   }
               }
           }
           else{
               this.message.setMessageStatus(true);
               this.message.setMessage("Invalid Email...!!!!");
           }
           this.session.getTransaction().commit();
           
       }catch(Exception er){
           System.out.println("Error : "+er.getMessage());
           this.message.setMessageStatus(true);
           this.message.setMessage(er.getMessage());
       }
       return this.message;
    }  
    
    @Override
    public Message changeCredentials(Admin oldAdmin,Admin newAdmin){
        this.initSession();
        try{
            this.session.beginTransaction();
            String HQL = "FROM Admin WHERE email=:varEmail";
            Query query = this.session.createQuery(HQL);
            query.setParameter("varEmail", oldAdmin.getEmail());
            this.tmpAdmin = (Admin)query.uniqueResult();
            if(this.tmpAdmin!=null){
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
               
                if(encoder.matches(oldAdmin.getPass(),this.tmpAdmin.getPass())){
                    this.tmpAdmin.setEmail(newAdmin.getEmail());
                    this.tmpAdmin.setPass(encoder.encode(newAdmin.getPass()));
                    this.tmpAdmin.setInvalidCount(0);
                    this.message.setMessageStatus(false);
                    this.message.setMessage("Credentials Change Succeed ...!!!");
                }
                else{
                    this.message.setMessageStatus(true);
                    this.message.setMessage("Invalid Old Password...!!!");
                }
            }
            else{
                this.message.setMessageStatus(true);
                this.message.setMessage("Invalid old Email.!!");
            }
            this.session.getTransaction().commit();
        }catch(Exception er){
           this.message.setMessageStatus(true);
           this.message.setMessage(er.getMessage());
        }
        return this.message;
    }
}
