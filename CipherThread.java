/*
 * Copyright (C) 2021 Zeronerva
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.exumbra.EEAF;

import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Zeronerva
 */
//cingular class (threading to be implemented)
public class CipherThread extends Thread {
    
    //<editor-fold defaultstate="collapsed" desc="declaring class variables">
    
    //info needed to use 1 set of each main cipher
    public FullPass fp;
    
    //intext
    public String inText;
    
    //outtext
    public String outText;
    
    //current mutation of intext
    public String currentMutation;
    
    //order of ciphers (not implemented yet)
    public int[] order;
    
    
    public Blocks block; 
    public Swaps swap;
    public Shifts shift;
    public int []permute;
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="declaring constructors">
    //use with full pass initalizer
//    public CipherThread( FullPass f) throws IOException {
//        this.fp=f;
//        //String s = fp.getInText();
//        //this.inText=inTxt;
//        this.block= new Blocks(fp.getPermute());
//        //System.out.println("block.getPermute"+Arrays.toString(block.getPermute()));
//        //this.shift = new Shifts (fp.getSingleShift(),f.getSplitShiftEven(),f.getSplitShiftOdd());
//        this.swap = new Swaps(fp.getSwapSeed()); 
//        this.permute=fp.getPermute();
//        
//    }
    
    //use with stages encryption
    public CipherThread(String inText, FullPass f) throws IOException {
        this.fp=f;
        this.inText=inText;
        this.block= new Blocks(inText,fp.getPermute());
        //System.out.println("block.getPermute"+Arrays.toString(block.getPermute()));
        this.shift = new Shifts (inText,fp.getSingleShift(),fp.getSplitShiftEven(),fp.getSplitShiftOdd());
        this.swap = new Swaps(inText,fp.getSwapSeed()); 
        this.permute=fp.getPermute();
        
    }
    

   
    
    

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="declaring get/set methods">
    
    public int[] getPermute() {
        return permute;
    }

    public String getInText() {
        return inText;
    }

    public void setInText(String inText) {
        this.inText = inText;
//        this.block.setInText(inText);
//        this.shift.setInText(inText);
        
    }

    public String getOutText() {
        return outText;
    }

    public void setOutText(String outText) {
        this.outText = outText;
    }

    public FullPass getFp() {
        return fp;
    }
    
    
        
    

    //</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="declaring class methods">


        
        public void encrypt(){
            //long startTime = System.nanoTime();
            block.encrypt();
            //System.out.println("encrypt block.getOutText() = " + block.getOutText());
            swap.setInText(block.getOutText());
            swap.encrypt();
           // System.out.println("encrypt swap.getOutText() = " + swap.getOutText());

            shift.setInText(swap.getOutText());
            shift.encrypt();
            //System.out.println("encrypt shift.getOutText() = " + shift.getOutText());
            
            this.outText=shift.getOutText();
            //long elapsedTime = System.nanoTime() - startTime;
            //System.out.println("Total execution time to encrypt thread"+ elapsedTime/1000000);
        }
        
        public void decrypt(){
            //long startTime = System.nanoTime();
            shift.setInText(inText);
            shift.decrypt();
           // System.out.println("decrypt shift.getOutText() = " + shift.getOutText());
            swap.setInText(shift.getOutText());
            swap.decrypt();
           // System.out.println("decrypt swap.getOutText() = " + swap.getOutText());
            block.setInText(swap.getOutText());
            block.decrypt();
           // System.out.println("decrypt block.getOutText() = " + block.getOutText());           
            this.outText=block.getOutText();
            //long elapsedTime = System.nanoTime() - startTime;
            //System.out.println("Total execution time to decrypt thread"+ elapsedTime/1000000);
        }
        
        

        
        public void threadToString(){
            System.out.println("permute) = " + Arrays.toString(block.getPermute()));
            System.out.println(swap.getSeed()+" " + shift.getSingleShift()+" " +shift.getSplitShiftEven()+" " +shift.getSplitShiftOdd());
        }
        public String toString(){
         
            String s ="(permute) = " + Arrays.toString(block.getPermute())+" "+swap.getSeed()+" " + shift.getSingleShift()+" " +shift.getSplitShiftEven()+" " +shift.getSplitShiftOdd();
            return s;
        }
                
    //</editor-fold>
 
       
}
      