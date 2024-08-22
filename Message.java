
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exumbra.EEAF;
//<editor-fold defaultstate="collapsed" desc="importing class dependencies">

import java.util.Calendar;
import java.util.Date;


//</editor-fold>
/**
 *
 * @author Zeronerva
 * 
 * 
 */
public class Message {
            
        
//<editor-fold defaultstate="collapsed" desc="declaring class variables">

    private String timeStamp;
    private String messageText;
    private String name;
    
 //</editor-fold>   

//<editor-fold defaultstate="collapsed" desc="declaring constructors">
    public Message(String name, String timeStamp, String messageText) {
        this.name= name;
        this.timeStamp = timeStamp;
        this.messageText = messageText;
        
    }
    
    public Message() {

    }
       public Message(String name, String messageText) {

        this.messageText = messageText;
        this.name=name;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="declaring get/set">

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }   
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="declaring class methods">
    

//</editor-fold>

    


}