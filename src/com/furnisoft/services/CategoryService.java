
package com.furnisoft.services;

import com.furnisoft.dao.CategoryDAO;
import com.furnisoft.pojos.Category;
import com.furnisoft.pojos.Message;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CategoryService implements CategoryDAO {
    
    private SessionFactory factory;
    private Session session;
    private Category category;
    private Message message;
    
    public CategoryService(){
        this.message = new Message();
        this.factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Category.class)
                .buildSessionFactory();
    }
    private void initSession(){
        this.session = this.factory.getCurrentSession();
    }
    
    @Override
    public Message registerCategory(Category category){
        this.initSession();
        try{
            this.session.beginTransaction();
            this.session.save(category);
            this.session.getTransaction().commit();
            this.message.setMessageStatus(false);
            this.message.setMessage("Category Registered successfully.");
        }catch(Exception er){
            this.message.setMessageStatus(true);
            this.message.setMessage("Internal Error.!!!");
        }
        return this.message;
    }

    @Override
    public List<Category> getCategory() {
        List<Category> category = new ArrayList<Category>();
        try{
            this.initSession();
            this.session.beginTransaction();
            category = this.session.createQuery("FROM Category c WHERE status=1 ORDER BY c.id DESC").list();
            this.session.getTransaction().commit();
        }catch(Exception er){
            category = null;
        }
        return category;
    }

    @Override
    public Message updateCategory(Category category) {
        System.out.println("Enter update section : ");
        this.initSession();
        this.message = new Message();
        try{
            this.session.beginTransaction();
            this.category = this.session.get(Category.class, category.getId());
            this.category.setCategoryName(category.getCategoryName());
            this.category.setRegDate(category.getRegDate());
            this.session.getTransaction().commit();
            this.message.setMessageStatus(false);
            this.message.setMessage("Category Updated successfully.");
            System.out.println("Update Success.");
        }catch(Exception er){
            System.out.println("Update failed !!! : "+er.getMessage());
            this.message.setMessageStatus(true);
            this.message.setMessage("Internal Error.!!!");
        }
        return this.message;
    }

    @Override
    public Message deleteCategory(Category category) {
        this.initSession();
        this.message = new Message();
        this.category = new Category();
        try{
            this.session.beginTransaction();
            this.category = this.session.get(Category.class, category.getId());
            this.category.setStatus(false);
            this.category.setExpDate(category.getExpDate());
            this.session.getTransaction().commit();
            this.message.setMessageStatus(false);
            this.message.setMessage("Category Deleted Successfull..!!!");
        }catch(Exception er){
            this.message.setMessageStatus(true);
            this.message.setMessage("Internal Error.!!!");
        }
        return this.message;
    }
}
