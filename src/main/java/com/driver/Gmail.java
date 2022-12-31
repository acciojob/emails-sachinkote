package com.driver;

import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    private ArrayList<Mail> Inbox= new ArrayList<>();
    private ArrayList<Mail> Trash= new ArrayList<>();
    public Gmail(String emailId, int inboxCapacity) {
    	super(emailId);
    	this.inboxCapacity=inboxCapacity;
    	
    	

    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
      
    	if(Inbox.size()==inboxCapacity)
    	{
    		Mail oldmail= Inbox.get(0);
    		Inbox.remove(0);
    		Trash.add(oldmail);
    	}
    	
    	Inbox.add(new Mail(date,sender,message));
    	
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
    	
       Mail m=null;
        for(int i=0;i<Inbox.size();i++)
        {
        	Mail m1=Inbox.get(i);
        	if(m1.getMessage()==message)
        	{
        		m=m1;
        		break;
        	}
        		
        	
        }
        if(m!=null)
        {
        	Inbox.remove(m);
        	Trash.add(m);
        }
        System.out.println("delete");
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
    	if(Inbox.isEmpty()==true)
    		return null;
    	else
    	{
    		Mail m=Inbox.get(Inbox.size()-1);
    		return m.getMessage();
    	}

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
    	if(Inbox.isEmpty()==true)
    		return null;
    	else
    	{
    		Mail m=Inbox.get(0);
    		return m.getMessage();
    	}

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
       int count=0;
       for(Mail m: Inbox)
       {
    	   Date mailDate=m.getDate();
    	   if(mailDate.compareTo(start)>=0 && mailDate.compareTo(end)<=0)
    		   count++;
       }
       return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
    	return Inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
         return Trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
         Trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
    	return inboxCapacity;
    }
}
