
package com.furnisoft.services;
import com.furnisoft.pojos.Admin;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Mailer {
    String host="smtp.gmail.com";
    String port="587";
    String userName="e42emart@gmail.com";
    String passWord="Dangerous@123";
    
    private int code;
    private Admin admin;
    private SessionFactory factory;
    private org.hibernate.Session session;
    private com.furnisoft.pojos.Message message;
    
    public com.furnisoft.pojos.Message checkEmail(String email){
        this.factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Admin.class)
                .buildSessionFactory();
        this.session = this.factory.getCurrentSession();
        this.message = new com.furnisoft.pojos.Message();
        
        try{
            this.code = (int)(Math.random()*99999999+10000000);
            this.session.beginTransaction();
            String HQL  = "FROM Admin WHERE email =:varEmail";
            Query query = this.session.createQuery(HQL);
            query.setParameter("varEmail", email);
            this.admin = new Admin();
            this.admin = (Admin)query.uniqueResult();
            
            if(this.admin!=null){
                this.admin.setInvalidCount(0);
                this.admin.setPass(new BCryptPasswordEncoder(12).encode(Integer.toString(this.code)));
                this.message.setMessageStatus(false);
                this.message.setMessage("Check your Email.");
            }
            else{
                this.message.setMessageStatus(true);
                this.message.setMessage("Invlid Email..!!!!");
            }
            this.session.getTransaction().commit();
        }catch(Exception er){
            System.out.println("Error : "+er.getMessage());
            this.message.setMessageStatus(true);
            this.message.setMessage("Internal Error.!!!");
        }
        return this.message;
    }
    public void sendEmail(String email) throws AddressException, MessagingException {
		
    	String toAddress=email;
    	String subject="Account Recovery";
        String message="Your Email is : "+email+" <br> Your Password is : "+this.code;
    	// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, passWord);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(message);

		// sends the e-mail
		Transport.send(msg);
	}
}
