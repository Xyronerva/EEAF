/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exumbra.EEAF;
//<editor-fold defaultstate="collapsed" desc="importing class dependencies">

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;





//</editor-fold>
/**
 *
 * @author Zeronerva
 * 
 * 
 */
public class TextGen {


            
        
//<editor-fold defaultstate="collapsed" desc="declaring class variables">
    
    //characterSet
    public String charSet;
    
    //random to be used (blah)
    public Random r;
       
 //</editor-fold>   

//<editor-fold defaultstate="collapsed" desc="declaring constructors">
    //main const
    public TextGen() throws IOException {
      this.r=new Random();
      setCharSet();
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="declaring get/set">
    
    //reads charSet
     public final void setCharSet() throws IOException {
         
        String fileLocation="src//main//resources//eeafcharset.txt";
        InputStream is =getClass().getClassLoader().getResourceAsStream("eeafcharset.txt");
        this.charSet= new String(Files.readAllBytes(Paths.get(fileLocation)));
        
    }

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="declaring class methods">
     
       //dedupes if needed
        public String deDupe(){
        String noDuplicates = Arrays.asList(charSet.split(""))
                      .stream()
                      .distinct()
                      .collect(Collectors.joining());
        return noDuplicates;
         }  
    
        public String generateString(int l) {
        String s="";
        for(int i = 0;i < l;i++){
            s+= charSet.charAt(generateIndex());
        }
        System.out.println(s);
        return s;
    }
    
       //generates the index for a utf 8 character NOT CHARSET BUT FULL UTF8    
        public int generateIndex(){
        // System.out.println("index = " + index);
        int min=0;
        int max=charSet.length();
        return r.nextInt((max - min) + 1) + min;
        };
    
//</editor-fold>
}