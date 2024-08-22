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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//</editor-fold>
/**
 *
 * @author Zeronerva
 *
 *  ONE STAGE IS EQUALS TO 3*CIPHER COUNT ENCRYPTEIONS PER KEY LENGTH DIFFERENTIATION
 *  THIS EXPANDS AS KEY LENGTH DIFFERS AS SCOPE IS IRRELEVANT MORE LENGTH MORE ENCRYPTION
 */
public class Stage {

//<editor-fold defaultstate="collapsed" desc="declaring class variables">
    //Secret key
    public String key;
    
    //inText whether encrypting or decryption
    public String inText;
    
    //outTextwhether encrypting or decryption
    public String outText;
    
    //Seed to pseudo randomly encrypt/decrypt
    public long seed;
    
    //Random to be used with seed
    public Random r;
    
    //Array of FullPasses (i.e. one blocks, shift, swap of each cipher on one message per(nth) stages )
    public FullPass[] stageInUse;
    
    //key data taken from secret key to facilitate (a full set of ciphers) per text range
    public ArrayList<FullPass[]> stages = new ArrayList();
    
    //FullPassInit converts key to useable fragements to use per cipher cycle(ie stage)
    public FullPassInit fpi;
    
    //on HOLD to be implemented
    //to deprecate this class
    //public CipherManager cm;
    
    //total stages to be preformed to tell FPI(FullPassInit) how to break down key
    public int totalStages;
    
    //how many sets of stages to be used 
    public int passes;
    
    //to be deprecated
    public int currentStage = 0;
    
    //the current state of en/out text
    public String currentMutation;
    
    //array of FullPasses
    public FullPass[] fullPasses;
    
    //FullPasses Used at any given time (most likely depreicated)
    int fullPassesUsed;

    //</editor-fold>  
    
//<editor-fold defaultstate="collapsed" desc="declaring constructors">
    
    //Default const
    public Stage(String inText, long seed, String key) throws IOException {
        this.key=key;
        this.seed = seed;
        this.currentMutation = inText;
        fullPassesUsed=0;
        fpi = new FullPassInit(currentMutation, key, seed,true);
        passes = fpi.getPasses();
        totalStages = passes / 3;
        fullPasses = fpi.getFullPasses();
        buildStages();
    }
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="declaring get/set">
    
    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }
    
    public FullPass[] getFullPasses() {
        return fullPasses;
    }

    public void setFullPasses(FullPass[] fullPasses) {    
        this.fullPasses = fullPasses;
    }

    public int getTotalStages() {
        return totalStages;
    }
    
    public String getInText() {
        return inText;
    }

    public void setInText(String inText) {
        this.inText = inText;
        this.currentMutation=inText;
    }

    public String getOutText() {
        return outText;
    }

    public void setOutText(String outText) {
        this.outText = outText;
    }

    public ArrayList<FullPass[]> getStages() {
        return stages;
    }

    public void setStages(ArrayList<FullPass[]> stages) {
        this.stages = stages;
    }

    public int getPasses() {
        return passes;
    }

    public void setPasses(int passes) {
        this.passes = passes;
    }

    public int getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(int currentStage) {
        this.currentStage = currentStage;
    }

    public String getCurrentMutation() {
        return currentMutation;
    }

    public void setCurrentMutation(String currentMutation) {
        this.currentMutation = currentMutation;
    }
    
    public void setEnryptStage() throws IOException {
        if (currentStage < stages.size() - 1) {
            System.out.println("currentStage = " + currentStage);
            stageInUse = stages.get(currentStage);
        }
    }

    public void setDeryptStage() throws IOException {
        if (currentStage >= 0) {
            
            System.out.println("currentStage = " + currentStage);
            stageInUse = stages.get(currentStage);
        }
    }



//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="declaring class methods">

    //<editor-fold defaultstate="collapsed" desc="encryption methods">
    
    //Main Encryption method
    public String encrypt(String inText, FullPass[] stage) throws IOException {
        
        FullPass fp = stage[0];
        FullPass fp1 = stage[1];
        FullPass fp2 = stage[2];
        CipherThread en = new CipherThread(inText, fp);
        en.setInText(inText);
        en.encrypt();
        String results = en.getOutText();
        CipherThread en1 = new CipherThread(results, fp1);
        en1.encrypt();
        results = en1.getOutText();
        CipherThread en2 = new CipherThread(results, fp2);
        en2.encrypt();
        results = en2.getOutText();
        results=en2.getOutText();
        outText = results;
        currentMutation=results;
        return results;
    }
    //main Decrypting method
    public String decrypt(String inText, FullPass[] stage) throws IOException {
        FullPass fp = stage[0];
        FullPass fp1 = stage[1];
        FullPass fp2 = stage[2];
        CipherThread de2 = new CipherThread(inText, fp2);
        de2.decrypt();
        String results = de2.getOutText();
        CipherThread de1 = new CipherThread(results, fp1);
        de1.decrypt();
        results = de1.getOutText();
        CipherThread de = new CipherThread(results, fp);
        de.decrypt();
        results = de.getOutText();
        outText = results;
        currentMutation=results;
        return results;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="declaring ansilarry methods">

    //buildStages uses FPI to build unique sets of 3*cipher number allowed by key at its length
    //i.e. ergo 10 stages = a message being encrypte 30 times
    public void buildStages() {
        FullPass[] stage = new FullPass[3];       
        for (int p = 0; p < totalStages; p++) {
            //System.out.println("building stage " +p);
            stages.add(buildOneStage());         
        }
    }
    
    //builds 1 stage ie 9 FullPasses
    public FullPass[] buildOneStage(){
        FullPass[] stage = new FullPass[3];
        for (int i = 0; i < stage.length; i++) {
                stage[i] = fullPasses[fullPassesUsed];
                fullPassesUsed++;
        }
        return stage;
    }
    
    //testing artifact
    public void compareStages(){
        for(int i=0;i<stages.size()-2;i++){
            System.out.println(stages.get(i).equals(stages.get(i++)));
        }
    }
    
    //testing artifact
    public void printStages() {

        for(int p=0;p<stages.size()-1;p++){
            
            printStage(p);
        }
    }
    
    //testing artifact
    public void printStage(int index){
        FullPass[] stage=stages.get(index);
        
        for(int i= 0;i<stage.length;i++){
            
            stage[i].printFullPass();
        }
        System.out.println("end stage" +"\n");
    }
    
    //testing artifact
    public void printFullPasses(){
        for(int i=0;i<fullPasses.length;i++){
            printFullPass(i);
        }
    }
    
    

    //testing artifact
    public void printFullPass(int index){
        System.out.println(fpi.fullPasses[index].toString());
    }


    //</editor-fold>

//</editor-fold>3
}
