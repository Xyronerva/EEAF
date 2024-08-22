/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exumbra.EEAF;
//<editor-fold defaultstate="collapsed" desc="importing class dependencies">


import java.io.IOException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 *
 * @author Zeronerva
 * 
 * 
 */
public class EEAF {
            
        
//<editor-fold defaultstate="collapsed" desc="declaring class variables">

    public long seed;
    private final String key;
    public String inText;
    public FullPassInit fpi;
    public String outText;
    public MessageHandler2 handler;
    public String keyName;
    //public StageManager sm;
   

    
       
 //</editor-fold>   

//<editor-fold defaultstate="collapsed" desc="declaring constructors">
//    //normal eeaf constructor
//    public EEAF(String inTxt,long seed) throws IOException {
//           this.inText=inTxt;
//           String keyLocation = "C:\\Users\\Nerxy\\Documents\\eeaftestkey.txt";
//           this.key = readFile(keyLocation);
//           this.fpi=new FullPassInit(inText,key,seed);
//           
//           //Stage stage =new Stage(inText,seed);
//           //System.out.println(stage.getTotalStages()+" "+fpi.getPasses());
//           handler  =new MessageHandler(seed);
//  
//    }
    
    //no intext eeaf constructor
//    public EEAF(long seed) throws IOException {
//           this.inText="Initalizing EEAF";
//           String keyLocation = "src//main//resources//keys//test.key";
//           this.key = new String(Files.readAllBytes(Paths.get(keyLocation)));
//           //this.fpi=new FullPassInit(inText,key,seed);
//           handler  =new MessageHandler2(seed);
//  
//    }
    
    //no intext eeaf constructor
    public EEAF(long seed, String keyName) throws IOException {
            System.out.println("keyName = " + keyName);
           this.inText="Initalizing EEAF";
           String keyLocation = "src//main//resources//keys//"+keyName;
           
        InputStream is =getClass().getClassLoader().getResourceAsStream("keyName"+".key");
        this.key= new String(Files.readAllBytes(Paths.get(keyLocation)));
           System.out.println("key ="+ key);
           //this.fpi=new FullPassInit(inText,key,seed);
           handler  =new MessageHandler2(seed,key);
  
    }
    
    
   
    
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="declaring get/set">
  

    public String getInText() {
        return inText;
    }

    public String getOutText() {
        return outText;
    }

    public void setInText(String inText) {
        this.inText = inText;
    }


//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="declaring class methods">
    
 public String sendMessage(String plainText) throws IOException{
 
   return handler.encryptMessage(plainText);

 }
 
  public String recieveMessage(String cipherText) throws IOException{
 
   return handler.decryptMessage(cipherText);
   
 }
 
  
 
  
  
 
 



    
 
    
  
   
   
   

//</editor-fold>

   
}