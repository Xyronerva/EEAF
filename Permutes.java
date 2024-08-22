/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exumbra.EEAF;
//<editor-fold defaultstate="collapsed" desc="importing class dependencies">

import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

//</editor-fold>
/**
 *
 * @author Zeronerva
 * 
 * 
 */

//pojo makes permutations for a key and its length
public class Permutes {
            
        
//<editor-fold defaultstate="collapsed" desc="declaring class variables">
    
    //lists all permutes for every called "blocks"
    public ArrayList <Integer[]> permutes =new ArrayList();
    
    //inText
    public String inText;
    
    //classes seed to random
    public long seed;
    
    //generates new Permutes
    public PermuteGenerator pg;
    
    //to be deprecated
    public int passes=50;
    
    
       
 //</editor-fold>   

//<editor-fold defaultstate="collapsed" desc="declaring constructors">
    
    //main const
    public Permutes(String inText, long seed) {
        this.inText = inText;
        this.seed = seed;
        pg = new PermuteGenerator(inText,seed);
        
    }
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="declaring get/set">
        
   public ArrayList<Integer[]> getPermutes(){
        return permutes;
    }

    public void setPermutes(ArrayList<Integer[]> permutes) {
        this.permutes = permutes;
    }

    public String getInText() {
        return inText;
    }

    public void setInText(String inText) {
        this.inText = inText;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public int getPasses() {
        return passes;
    }

    public void setPasses(int passes) {
        this.passes = passes;
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="declaring class methods">
    

    
    public void addArrayToList(int[] intArr) {
        Integer[] newArray = ArrayUtils.toObject(intArr);
        permutes.add(newArray);
    }
    
    //pulls permutes 1 at a time
    public int[] pullFromList(int i){
        Integer[] objectArray = permutes.get(i);
        int[] intArray = new int[objectArray.length];

        for(int ctr = 0; ctr < objectArray.length; ctr++) {
            intArray[ctr] = objectArray[ctr].intValue(); // returns int value
        }
        return intArray;
    }
    
    //adds array to arraylist(dupeish of addArrayToList)
    public void populateArrayList(int passes){
        for(int i=0;i<passes;i++){
            addArrayToList(pg.nextPermute());
        }
    }
    
    //testing artifact
    public void printPermutes(){
        int[] intArr;
        for(int i=0;i<permutes.size()-1;i++){
            intArr=pullFromList(i);
            System.out.println("intArr = " + Arrays.toString(intArr));
        }
    }
    
    
//</editor-fold>

}