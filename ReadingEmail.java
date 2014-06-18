// Code by Ashish Belwase <geeknepal.com>

import java.util.Properties;
import javax.mail.*;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import java.util.ArrayList;

public class ReadingEmail {

	public static void main(String args[]) throws Exception {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
			try {
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", "Your mail username", "password");
			System.out.println(store);
			
			Folder inbox = store.getFolder("Inbox");
      			inbox.open(Folder.READ_ONLY);
			int count = inbox.getMessageCount();
			//int count = inbox.getUnreadMessageCount();
			System.out.print(count);
			int x=0;
			while(x!=5){
			 Message msg = inbox.getMessage(count);
			Address[] in = msg.getFrom();
			
			String address="";
			for (Address add : in) {
				 address = add.toString();
			    System.out.println("FROM:"+ address);
			   
			
			}

			Object obj = msg.getContent();
			Multipart mp = (Multipart) msg.getContent();
		     
			BodyPart bp = ((Multipart) msg.getContent()).getBodyPart(0);
			boolean skhanal = address.contains("skhanal@ku.edu.np");//indexOf("Santosh Khanal"); 
			System.out.println(address);			
			System.out.println(skhanal);
			if(skhanal==true){
			System.out.println("SENT DATE:" + msg.getSentDate());
			System.out.println("SUBJECT:" + msg.getSubject());

			//Slice string
			
			String[] parts = bp.getContent().toString().split("You received this"); //returns an array with the 2 parts
			String toDisplay = parts[0]; 
			System.out.println("CONTENT:" + toDisplay);
			}
			x++;
			count--;
		}
			
								
		} catch (NoSuchProviderException e) {
		e.printStackTrace();
		System.exit(1);
		
	}
	}
}


