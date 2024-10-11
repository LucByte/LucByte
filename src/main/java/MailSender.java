
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lucas
 */
public class MailSender {
        public static void main(String[] args) throws Exception{
        //final String recipient = "wongbikes@gmail.com";
        //Mail.sendMail(recipient); //recipient
        //taking list of emails from txt file 
        BufferedReader reader = new BufferedReader(new FileReader("mailingList.txt"));
        String email;
        List<String> list = new ArrayList<String>();
        while((email = reader.readLine()) != null){
            list.add(email);
        }
        String[] recipients = list.toArray(new String[0]);
        reader.close();
        Mail.sendMail(recipients, true, "", "", 0.0, 0.0);
    }
}
