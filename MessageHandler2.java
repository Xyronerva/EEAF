/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exumbra.EEAF;
//<editor-fold defaultstate="collapsed" desc="importing class dependencies">

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;


//</editor-fold>
/**
 *
 * @author Zeronerva
 * 
 * 
 */
public class MessageHandler2 {
            
        
//<editor-fold defaultstate="collapsed" desc="declaring class variables">
    
    public String currentInMessage;
    public String currentOutMessage; 
    public ArrayList <Message> messages= new ArrayList();
    public Queue <String> readyToSend= new LinkedList<>();
    Queue<String> incoming = new LinkedList<> ();
    Queue<String> outgoing = new LinkedList<> ();
    public long seed;
    public String key;
    
       
 //</editor-fold>   

//<editor-fold defaultstate="collapsed" desc="declaring constructors">
    public MessageHandler2(long seed) throws IOException {
        this.seed=seed;
    }
    
    public MessageHandler2(long seed, String key) throws IOException {
        this.seed=seed;
        this.key=key;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="declaring get/set">

    public String getCurrentInMessage() {
        return currentInMessage;
    }

    public void setCurrentInMessage(String currentInMessage) {
        this.currentInMessage = currentInMessage;
    }

    public String getCurrentOutMessage() {
        return currentOutMessage;
    }

    public void setCurrentOutMessage(String currentOutMessage) {
        this.currentOutMessage = currentOutMessage;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
    
    

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="declaring class methods">
    
    //<editor-fold defaultstate="collapsed" desc="message posting">
    

    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="encryption methods">
    
        public String encryptMessage(String plainText) throws IOException{
            Stage st=new Stage(plainText, seed, key);
            Out out = new Out(plainText,st );
            out.encrypt2();
            String cipherText=out.outText;
            return cipherText;
        }

        public String decryptMessage(String cipherText) throws IOException{
            Stage st=new Stage(cipherText, seed, key);
            In in = new In(cipherText,st );
            in.decrypt2();
            String messageText=in.outText;
            return messageText;
        }       
    
    //</editor-fold>
            
    //<editor-fold defaultstate="collapsed" desc="queue management">
        
     
    
       
    
        

        
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="ansilarry methods">
        
        public String makeTimeStamp(){
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        String currentDateTime = currentDate.toString();
        return currentDateTime;
    } 
        
    //</editor-fold>
            
//</editor-fold>

} 
