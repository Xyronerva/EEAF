/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exumbra.EEAF;
//<editor-fold defaultstate="collapsed" desc="importing class dependencies">

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import org.apache.commons.io.FileUtils;


//</editor-fold>
/**
 *
 * @author Zeronerva
 * 
 * 
 */
public final class KeyGen {
            
        
//<editor-fold defaultstate="collapsed" desc="declaring class variables">
public String charKey;
public static String key;
public  String charSet;
public static char[] utfEight;

public long seed ;
public Random r;

 //</editor-fold>   

//<editor-fold defaultstate="collapsed" desc="declaring constructors">

   public static String fileDirectory="src//main//resources//keys//";
    
      public KeyGen(long seed) throws IOException {
        this.seed =seed;
        r = new Random(seed);
        String content;
        String fileLocation="src//main//resources//eeafcharset.txt";
        InputStream is =getClass().getClassLoader().getResourceAsStream("eeafcharset.txt");
        this.charSet= new String(Files.readAllBytes(Paths.get(fileLocation)));     
        utfEight = charSet.toCharArray();
        System.out.println("utf8.length() = " + charSet.length());
   
    }
      
    

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="declaring get/set">
    public long getSeed() {
        return seed;
    }
    public void setSeed(long seed) {      
        this.seed = seed;
    }

    public String getCharKey() {
        return charKey;
    }
    
     public  String getKey(){
        return key;
    }

    public  void setKey(String key) {
        KeyGen.key = key;
    }

    public  String getUtf8() {
        return charSet;
    }

    public void setCharKey(String charKey) {
        this.charKey = charKey;
    }
    
    


//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="declaring class methods">
    public void generateKey(int l) {
        String s="";
        for(int i = 0;i < l;i++){
            s+= charSet.charAt(generateIndex());
        }
        //System.out.println(s);
        this.charKey= s;
    }
    
    public void generateKeyMinMax(int min,int max,String keyName) throws IOException{
        
        Random r= new Random(System.currentTimeMillis()); 
        long seed=r.nextLong();
        KeyGen kg = new KeyGen(seed);
        int keySize = r.nextInt((max - min) + 1) + min;
        //System.out.println("keySize = " + keySize);
        kg.generateKey(keySize);
        kg.expandKey();
        String writePath=fileDirectory+keyName+".key";
        System.out.println(writePath);
        String charKey =kg.getCharKey();
        String expandedKey= kg.getKey();
        FileUtils.writeStringToFile(new File(writePath), expandedKey);
        System.out.println("finished key");
        
    }
    
    
    
    public void expandKey(){
        String s="";
        for(int i = 0;i < charKey.length();i++){
            int temp =findIndexValue(charKey.charAt(i));
            s+= Integer.toString(temp);             
        }
        this.key=s;
    }
    
    
    //generates the index for a utf 8 character     
   public int findIndexValue(char c){
       int value=0;
        for(int i=0;i <charSet.length();i++){
            if(c==charSet.charAt(i)){
                value=i;
            }
        }
      
        return value;
   };
    
   //generates the index for a utf 8 character     
   public int generateIndex(){
    // System.out.println("index = " + index);
        int min=0;
        int max=charSet.length()-1;
    
        return r.nextInt((max - min) + 1) + min;
   };
    

//</editor-fold>
}