/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lucas
 */
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mail {
    //class to send automated email to customers in email_list
    //boolean promo variable indicates whether email should be a promotional email or not
    
   public static void sendMail(String recipient, boolean promo, String customername, String bikeModel, double amountPaid, double fine) throws Exception {
        sendMail(new String[]{recipient}, false, customername, bikeModel, amountPaid, fine);
        //accepts both single email address as a String and multiple email addresses
   } 
   
   public static void sendMail(String[] recipient, boolean promo, String customername, String bikeModel, double amountPaid, double fine) throws Exception{

       Properties properties = System.getProperties();
       //configuring communication protocols
       properties.put("mail.smtp.host", "smtp.gmail.com");
       properties.put("mail.smtp.socketFactory.port", "587");
       properties.put("mail.smtp.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory");
       properties.put("mail.smtp.auth", "true");
       properties.put("mail.smtp.port", "587"); 
       properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
       properties.setProperty("mail.smtp.starttls.enable", "true"); //used to STARTTLS (email handshake protocol)
       properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2"); //TLSV1 and 1.1 are forbidden by java security

       
       String sender = "wongbikes@gmail.com";
       String password = "gyamzohbclvijltx"; //app authentication password to bypass gmail secure login
       //app auth password dependent on device
       
       Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
           @Override
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(sender, password);
           }
       });
       //create message to send with email - format of message cloud
        //message instantiated using session
       session.setDebug(true);
       try{
           MimeMessage message = new MimeMessage(session);
           message.setFrom(new InternetAddress(sender));
           //message.setRecipient(javax.mail.Message.RecipientType.BCC, new InternetAddress(recipient));
           for (int i=0;i<recipient.length;i++){
               message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(recipient[i]));
           }
           if(promo ==true){
            message.setSubject("Road Runner Rentals Bicycle shop promotional offer");
            message.setText("Dear valued customer, \n Thank you for shopping at Road Runner's."
                    + "\n We believe in providing you with the best cycling experience possible."
                    + "As such, we are excited to offer you a special promotional discount for your next bike rental."
                    + "For a limited time only, you can revieve 20% off your rental"
                    + "available at all locations. Simply show this email to the person in charge."
                    +"Whether you're a seasoned cyclist or just starting out, our extensive range of bikes caters to all levels of experience"
                    + "We always have the perfect bike for your adventure."
                    + "\n Thank you for choosing Road Runner Rentals, and we look forward to seeing you at our next ride."
                    + "Best regards, \n The Road Runners Rental team.");
           }
           else{
               message.setSubject("Thank you for shopping at Road Runner Rentals.");
               message.setText("Dear "+ customername+"\nThank you for returning your rental bike to our store. \n We hope you had a great time riding it and we look forward to seeing you again soon."
               + "\nPlease find your invoice details below:"
               + "\nBike Model: "+bikeModel
               + "\nRental Cost: "+amountPaid
               + "\nLate Fee: "+fine
               +"\nYou can pay the invoice amount via cash or card at our store."
               +"\nThank you for choosing our bike service.");
           }
           Transport.send(message);
       } catch (Exception ex) {
           Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
           ex.printStackTrace();
       }
   }
}