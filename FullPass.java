
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

import java.util.Arrays;

/**
 *
 * @author Zeronerva
 */

//pojo to contain all the values needed to build a CipherThread i.e. values for constructors of one of each cipher subclass
public class FullPass {
    
//<editor-fold defaultstate="collapsed" desc="declaring class variables">
    //Shifting values
    public int singleShift;
    public int splitShiftEven;
    public int splitShiftOdd;
    public int[] allShift;
    //Swaps seed
    public long swapSeed;
    
    //Blocks permute
    public int[] permute;
    
    
    public String inText;
    
 
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="declaring constructors">
    
   
    
    public FullPass(int shiftSingle, int splitShiftEven, int splitShiftOdd, long seed) {
        //this.inText=inText;
        this.singleShift = shiftSingle;
        this.splitShiftEven = splitShiftEven;
        this.splitShiftOdd = splitShiftOdd;
        this.swapSeed = seed;
        //this.permute = permute;
    }
    
    public FullPass(int shiftSingle, int splitShiftEven, int splitShiftOdd, long seed, int [] aShift) {
        //this.inText=inText;
        this.singleShift = shiftSingle;
        this.splitShiftEven = splitShiftEven;
        this.splitShiftOdd = splitShiftOdd;
        this.allShift=allShift;
        this.swapSeed = seed;
        //this.permute = permute;
    }
        
    

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="declaring get/set">    
    public long getSwapSeed() {
        return swapSeed;
    }

    public void setSwapSeed(long swapSeed) { 
        this.swapSeed = swapSeed;
    }

    public int getSingleShift() {
        return singleShift;
    }

    public void setSingleShift(int shiftSingl) {
        this.singleShift = shiftSingl;
    }
    

    public int getSplitShiftEven() {
        return splitShiftEven;
    }

    public void setSplitShiftEven(int splitShiftEven) {
        this.splitShiftEven = splitShiftEven;
    }

    public int getSplitShiftOdd() {
        return splitShiftOdd;
    }

    public void setSplitShiftOdd(int splitShiftOdd) {
        this.splitShiftOdd = splitShiftOdd;
    }

    public String getInText() {
        return inText;
    }

    public void setInText(String inText) {
        this.inText = inText;
    }

    

    public int[] getPermute() {
        return permute;
    }

    public void setPermute(int[] permute) {
        this.permute = permute;
        
    }

    public int[] getAllShift() {
        return allShift;
    }

    public void setAllShift(int[] allShift) {
        this.allShift = allShift;
    }
    

//</editor-fold>   

//<editor-fold defaultstate="collapsed" desc="declaring class methods">
    @Override
    public String toString() {
        return "FullPass{" + "singleShift=" + singleShift + ", splitShiftEven=" + splitShiftEven + ", splitShiftOdd=" + splitShiftOdd + ", swapSeed=" + swapSeed + Arrays.toString(permute)+'}';
    }

    
    
    public void printFullPass() {
        System.out.println("\n FullPass.printFullPass()");
        System.out.println("Arrays.toString(permute) = " + Arrays.toString(permute));
        System.out.println( "FullPass{" + "singleShift=" + singleShift + ", splitShiftEven=" + splitShiftEven + ", splitShiftOdd=" + splitShiftOdd + ", swapSeed=" + swapSeed  + '}');
    }
//</editor-fold>     
}
