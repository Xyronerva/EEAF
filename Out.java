/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exumbra.EEAF;
//<editor-fold defaultstate="collapsed" desc="importing class dependencies">

import java.io.IOException;

import java.util.ArrayList;


//</editor-fold>
/**
 *
 * @author Zeronerva
 * 
 * 
 */
public class Out {
            
        
//<editor-fold defaultstate="collapsed" desc="declaring class variables">
    
    //inText    
    public String inText;
    
    //outText
    public String outText;
    
    //stage to be used when encrypting 
    public Stage st; 
    
    //charSet
    public ArrayList <Character> charSet;
       
 //</editor-fold>   

//<editor-fold defaultstate="collapsed" desc="declaring constructors">
    
    //main const
    public Out(String inText, Stage stage) throws IOException {
        this.inText = inText;
        this.st=stage;
        charSet= new ArrayList();
        for(int i=0;i<st.fpi.charSet.length();i++){
            charSet.add(st.fpi.charSet.charAt(i));
        }
        //encrypt();
    }
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="declaring get/set">
    public String getInText() {
        return inText;
    }

    public void setInText(String inText) {
        this.inText = inText;
    }

    public String getOutText() {
        return outText;
    }

    public void setOutText(String outText,Stage stage) {
        this.outText = outText;
    }
    
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="declaring class methods">
    //testing artifact
     public boolean validateCharacter(char c){
        boolean b=false;

        for(int i=0;i<st.fpi.charSet.length();i++){
            if(charSet.contains(c)){
                b=true;
            }
        }
        return b;
    }
    
     //testing artifact
    public void cleanInText(){
        String cleaned="";
        for(int i=0;i<inText.length();i++){
            if(validateCharacter(inText.charAt(i))){
                cleaned+=inText.charAt(i);
            }else if(validateCharacter(inText.charAt(i))==false){
                System.out.println("removing char "+inText.charAt(i)+" from inText");
            }
        }
        inText=cleaned;
    }
    
    
    
    public void encrypt2() throws IOException{
        //forces netbeans to dispaly all utf8 chars
        //System.setOut(new PrintStream(System.out, true, "UTF8"));
        cleanInText();
        long startTime = System.nanoTime();
        String[] eResults= new String[st.getTotalStages()];
        String encryptOutText;      
        int count =0;
        int stagesDone=1;
        encryptOutText=st.encrypt(inText, st.stages.get(0));
        eResults[count]=st.getOutText();count++;
        for(int i=1;i<st.getTotalStages();i++){
            encryptOutText=st.encrypt(st.getOutText(), st.stages.get(i));
            eResults[count]=st.getOutText();count++;
        }
        outText=encryptOutText;

    }

//</editor-fold>

}