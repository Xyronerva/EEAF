/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exumbra.EEAF;
//<editor-fold defaultstate="collapsed" desc="importing class dependencies">

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;




//</editor-fold>
/**
 *
 * @author Zeronerva
 * 
 * 
 */
//provides unique permutes from key
public class PermuteGenerator {
            
        
//<editor-fold defaultstate="collapsed" desc="declaring class variables">
    
    ////current permute being used
    public int[] currentPermute;
    
    //inital permute befor ransomization
    public int[] permuteBase; 
    
    //seed for random   
    public long seed;
    
    //random to be used with seed
    public Random r;
    
    //inText for determining permutes length
    public String inText;
  

    
    
 //</editor-fold>   

//<editor-fold defaultstate="collapsed" desc="declaring constructors">
    
    //standard const
    public PermuteGenerator(String inText,long seed ) {
        this.seed = seed;
        this.inText=inText;
        r=new Random(seed);
        initPermute();
        
    }   

//</editor-fold>
   
//<editor-fold defaultstate="collapsed" desc="declaring get/set">
    public void setInText(String inText) {
        this.inText = inText;
    }
    public String getInText() {
        return inText;
    }
    public int[] getCurrentPermute() {
        return currentPermute;
    }

    public void setCurrentPermute(int[] currentPermute) {
        this.currentPermute = currentPermute;
    }

    public int[] getPermuteBase() {
        return permuteBase;
    }

    public void setPermuteBase(int[] permuteBase) {
        this.permuteBase = permuteBase;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="declaring class methods">
    
    //makes array 1-inText.length
    public void initPermute(){
    //adding indicies in ascending order
      
        permuteBase=new int[inText.length()];
        for(int i=0;i<permuteBase.length;i++){
            permuteBase[i]=i;
        } 
        //System.out.println("initPermute PG");
        currentPermute=permuteBase;
    }
    
    //returns the next unused permute
    public int[] nextPermute(){

        //permute to array list
        ArrayList <Integer> rando= new ArrayList<>();
        for(int i=0;i<currentPermute.length;i++){
            rando.add(i);
        }
        Collections.shuffle(rando,r);
        for(int i=0;i<currentPermute.length;i++){
            currentPermute[i]=rando.get(i);
        }
        return currentPermute;
    }        
   
    
//</editor-fold>





  
}