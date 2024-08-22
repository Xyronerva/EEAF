/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exumbra.EEAF;
//<editor-fold defaultstate="collapsed" desc="importing class dependencies">

import java.io.IOException;
import java.io.PrintStream;


//</editor-fold>
/**
 *
 * @author Zeronerva
 * 
 * 
 */
public class In {
            
        
//<editor-fold defaultstate="collapsed" desc="declaring class variables">
    
    //inText
    public String inText;
    
    //outText
    public String outText;
    
    //Stage object for en/de crypting
    public Stage st;
       
 //</editor-fold>   

//<editor-fold defaultstate="collapsed" desc="declaring constructors">
    
    public In(String inText, Stage stage) throws IOException {
        this.inText = inText;
        this.st=stage;
        //decrypt();
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

    public void setOutText(String outText) {
        this.outText = outText;
    }
    
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="declaring class methods">

    //main decrypt  method
    public void decrypt2() throws IOException{
        long startTime = System.nanoTime();
        String[] dResults= new String[st.getTotalStages()];
        String decryptOutText;
        int count =st.getTotalStages()-1;
        int stagesDone=1;
        decryptOutText=st.decrypt(inText,st.stages.get(st.getTotalStages()-1));
        dResults[count]=st.getOutText();count--; 
            for(int i=st.getTotalStages()-2;i>=0;i--){
                decryptOutText=st.decrypt(st.getOutText(), st.stages.get(i));
                dResults[count]=st.getOutText();count--;                          
            }
        long elapsedTime = System.nanoTime() - startTime;
        outText=decryptOutText;

    }
    
    
   
    
    
//</editor-fold>

   
}