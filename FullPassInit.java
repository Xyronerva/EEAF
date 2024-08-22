/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exumbra.EEAF;
//<editor-fold defaultstate="collapsed" desc="importing class dependencies">


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;



//</editor-fold>
/**
 *
 * @author Zeronerva
 * 
 * 
 */
public class FullPassInit {
            
        
//<editor-fold defaultstate="collapsed" desc="declaring class variables">
    String inText;
    public String key;
    public String remainingKey;
    public String outKey;
    public FullPass []fullPasses;
    public int digitsRemaining;
    public String charSet;
    public int digitsUsed=0;
    public int digitsCount=0;
    public long seed;
    public Random r;
    public int passes;
    public int perPass=85;
    public Permutes p;
    public ArrayList <Integer> bannedNumbers= new ArrayList();
       
 //</editor-fold>   
   
//<editor-fold defaultstate="collapsed" desc="declaring constructors">
    //use for using permuteGenerator

         
        
    public FullPassInit(String in,String key, long seed) throws IOException {
        
        String content;
        String fileLocation="src//main//resources//eeafcharset.txt";
        InputStream is =getClass().getClassLoader().getResourceAsStream("eeafcharset.txt");
        this.charSet= new String(Files.readAllBytes(Paths.get(fileLocation)));
        this.inText=in;
        this.key = key;
        this.seed=seed;
        this.remainingKey=key;
        this.passes=(int)Math.floor((key.length()-200)/perPass);
        
        fullPasses = new FullPass[passes];
        p = new Permutes(inText, seed);
        p.populateArrayList(passes);
        //System.out.println("\n print permutes");
        //p.printPermutes();
        initFP();
        initBannedNumbers();
    }
    
            
    public FullPassInit(String in,String key, long seed,boolean aShift) throws IOException {
        
        String content;
        String fileLocation="src//main//resources//eeafcharset.txt";
        InputStream is =getClass().getClassLoader().getResourceAsStream("eeafcharset.txt");
        this.charSet= new String(Files.readAllBytes(Paths.get(fileLocation)));
        this.inText=in;
        this.key = key;
        this.seed=seed;
        this.remainingKey=key;
        this.passes=(int)Math.floor((key.length()-2000)/perPass);
        
        fullPasses = new FullPass[passes];
        p = new Permutes(inText, seed);
        p.populateArrayList(passes);
        //System.out.println("\n print permutes");
        //p.printPermutes();
        if (aShift==true){
             initFP2();
        initBannedNumbers();
        }
       
    }
    
    


         
 

 
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="declaring get/set">
 
    public void setInText(String inText) {
        this.inText = inText;
    }

    public String getInText() {
        return inText;
    }

    public String getInKey() {
        return key;
    }

    public String getCurrentKey() {
        return remainingKey;
    }

    public String getOutKey() {
        return outKey;
    }

    public FullPass[] getFullPasses() {
        return fullPasses;
    }

    

    public int getDigitsRemaining() {
        return digitsRemaining;
    }

    public int getPasses() {
        return passes;
    }

    public void setPasses(int passes) {
        this.passes = passes;
    }
    
    

    

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="declaring class methods">
        public void initBannedNumbers() {
            double dbl = 999999999 / charSet.length();
           bannedNumbers = new ArrayList();
           bannedNumbers.add(charSet.length());
            int currentMultiple = charSet.length();
            while (currentMultiple < 999999999) {
                currentMultiple += charSet.length();
                bannedNumbers.add(currentMultiple);
            }
        }
        
        public boolean validateInteger(int validate){
            boolean b=false;
            if ((bannedNumbers.contains(validate))!=true){
                b=true;
                //int is invalid
            }
            else if((validate % charSet.length() != 0)){
                b=true;
                //int is valid
            }
            return b;  
        }
        

        
        
    
        public String pullDigits(int digits){
            digitsCount += digits;
            String s = remainingKey.substring(0, digits);
            this.remainingKey= remainingKey.substring(digits +1, remainingKey.length());
            this.outKey=remainingKey;
            this.digitsRemaining=remainingKey.length();
            return s;
        }
        
   
        
        public int findInteger(){
            //an int can hold no more than 10 digits
            
            String s= pullDigits(9);
            //digitsCount+=9;
            //System.out.println("digitsPulled = " + s.length());
           
            int l = Integer.parseInt(s);
            
            return l;
        }
        
        public int findValidInteger(){
            int l = findInteger();
            while(validateInteger(l)==false){
                l=findInteger();
            }
            return l;
        }
        
        public long findLong(){
            //an long can hold no more than 19 digits
            //int i=0;
            //pulling 18 to avoid errors when parcing it to
            //digitsCount+=18;
            String s= pullDigits(18);
            Long l = Long.valueOf(s);

            return l;
        }

        public void initFP(){
            //System.out.println("digitsUsed= " + digitsUsed+"\n");
            //System.out.println("total passes =" +passes);
            for(int i=0;i<fullPasses.length;i++){
                //System.out.println("building pass"+i);
                fullPasses[i]=nextFP();
                fullPasses[i].setPermute(p.pullFromList(i));
                //fullPasses[i].printFullPass();
            }
            System.out.println("total passes built=" +fullPasses.length);
            //printFullPasses();
        }   
        
              public void initFP2(){
            //System.out.println("digitsUsed= " + digitsUsed+"\n");
            //System.out.println("total passes =" +passes);
            for(int i=0;i<fullPasses.length;i++){
                //System.out.println("building pass"+i);
                fullPasses[i]=nextFP2();
                fullPasses[i].setPermute(p.pullFromList(i));
                //fullPasses[i].printFullPass();
            }
            System.out.println("total passes built=" +fullPasses.length);
            //printFullPasses();
        } 
            
            public int[] findAllShift(){
                int[] shiftAll = new int[inText.length()];
                for (int i =0;i<inText.length();i++){
                    shiftAll[i]=findValidInteger();
                }
               return shiftAll; 
            }
        
            public FullPass nextFP(){
            //FullPass f=new FullPass();
            
                FullPass f= new FullPass(findValidInteger(),findValidInteger(),findValidInteger(),findLong());
       
                //System.out.println("\n fpi.nextFP() f.toString() = " + f.toString());
                //System.out.println("Arrays.toString(f.getPermute()) = " + Arrays.toString(f.getPermute()));                
                //System.out.println("digitsCount this pass= " + digitsCount+"\n");
                digitsUsed+=digitsCount;
                digitsCount=0;     
                
            return f;
        }
            
        public FullPass nextFP2(){
            //FullPass f=new FullPass();
            
                FullPass f= new FullPass(findValidInteger(),
                        findValidInteger(),findValidInteger(),
                        findLong(),findAllShift());
       
                //System.out.println("\n fpi.nextFP() f.toString() = " + f.toString());
                //System.out.println("Arrays.toString(f.getPermute()) = " + Arrays.toString(f.getPermute()));                
                //System.out.println("digitsCount this pass= " + digitsCount+"\n");
                digitsUsed+=digitsCount;
                digitsCount=0;     
                
            return f;
        }
        

        
        public void printFullPasses(){
            int totalPrinted=0;
            System.out.println("\nprintFullPasses()+ passes"+passes);
            for (int i =0;i<fullPasses.length;i++) {
                fullPasses[i].printFullPass();
            }
            System.out.println("totalPrinted ="+ totalPrinted+"\n");
            
        }
        
//</editor-fold>
}