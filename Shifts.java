/*
 * Copyright (C) 2021 Zeronerva
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed out the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.exumbra.EEAF;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Zeronerva
 */
public class Shifts extends Cipher{
  
    //<editor-fold defaultstate="collapsed" desc="declaring class variables">
        
        /**  for now this represents a subset of the utf8 chars read out to an array from start to finish**/
        public char[] normalSet;

        /** all chars of message fed to this as in, or out from last iterated cipher method's return...**/
        public char[] in;

        /**final cipherText returned to user as out final step be it encrypting or decrypting **/
        public  char[] out;

        /** in[] converted to int from plainText
        char based on index not i of in[i] but index so we can launch
        this same code many times and make different out with same key**/
        public  int[] inInt;

        /** how far to shift a set of data can be any value 
        int can store + or -  does not result out same
         text as inInt**/

        public int singleShift;

        /** shifts all even values index(able) by i by this value
            positives  integers fed to splitShiftEven()= right shift while negative amounts shift left**/
        public int splitShiftEven;

        /** shifts all odd values index(able) by i by this value
        positives  integers fed to splitShiftOdd()= right shift while negative amounts shift left**/
        public int splitShiftOdd;
         
        /**shifts each character by a different value**/
        public int[] allShift;

//</editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="declaring constructors">

         public Shifts(String inText,int sShift, int eShift, int oShift)throws IOException{
            this.inText=inText;
            this.in= inText.toCharArray();
            this.singleShift=sShift; 
            this.splitShiftEven=eShift;
            this.splitShiftOdd=oShift;
            this.inInt=new int[inText.length()];
            this.out=new char[inText.length()];
            setCharSet();
            zeroFaultShifts();
//            if(sShift==charSet.length())
//                sShift=0;
//            if(sShift==charSet.length())
//                eShift=0;
//            if(sShift==charSet.length())
//                oShift=0;
            
        }
          public Shifts(String inText,int sShift, int eShift, int oShift,int[] aShift)throws IOException{
            this.inText=inText;
            this.in= inText.toCharArray();
            this.singleShift=sShift; 
            this.splitShiftEven=eShift;
            this.splitShiftOdd=oShift;
            this.allShift=aShift;
            this.inInt=new int[inText.length()];
            this.out=new char[inText.length()];
            setCharSet();
            zeroFaultShifts();
//            if(sShift==charSet.length())
//                sShift=0;
//            if(sShift==charSet.length())
//                eShift=0;
//            if(sShift==charSet.length())
//                oShift=0;
            
        }
         
        //to be deprecated
        public Shifts(String in){
            this.inText=in;
         }
         
     
  //</editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="declaring get/set methods">
        //read charset from file location
        public final void setCharSet() throws IOException {
            String fileLocation="src//main//resources//eeafcharset.txt";
            InputStream is =getClass().getClassLoader().getResourceAsStream("eeafcharset.txt");
            this.charSet= new String(Files.readAllBytes(Paths.get(fileLocation)));
            normalSet=charSet.toCharArray();
        }
        
        
        @Override
        public void setInText(String inText){
            super.inText= inText;
            this.inText= inText;
        }

        public char[] getOut() {
            return out;
        }

        @Override
        public String getInText() {
            return inText;
        }

        @Override
        public String getOutText() {
            return outText;
        }
        
         
        public int getSingleShift(){
            return singleShift;
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

        public int[] getAllShift() {
            return allShift;
        }

        public void setAllShift(int[] allShift) {
            this.allShift = allShift;
        }


        
        

    //</editor-fold>
        
 //<editor-fold defaultstate="expanded" desc="declaring class methods">
        
            //fold contains all methods to convert between char and int arrays
            //<editor-fold defaultstate="collapsed" desc="integer to char conversions">

           /**this method converts char values brought out from 
           constructors to integer values to facilitate shifting
           **/
           //converts chars to integers based on index
           public void toInt(){
               this.inInt=new int[inText.length()];
               for(int i=0;i<inText.length();i++){
                  inInt[i]=charSet.indexOf(inText.charAt(i));
               }

           }

           //converts int to char based on index
           public void toChar(){
               refactor();
               this.out=new char[inText.length()];
               for(int i=0;i<inText.length();i++){
                   out[i]=charSet.charAt(inInt[i]);
                   String s =String.copyValueOf(out);
                   this.outText=s;
               }
           }


        //</editor-fold>
             
            //fold contains all methods to facilitate overshift refactroing
            //on the off chance that shifting is even multiple of list size 
            //or other problematic numbers
            //<editor-fold defaultstate="collapsed" desc="overshift refactoring">
            public int over(int x ){
                while(x>inText.length()||x==inText.length()){
                    x-=charSet.length();
                    if(x==charSet.length()){
                        x--;
                    }
                   
                }
                return x;
            }
            
            public int under(int x){
                while(x<0 ){
                    x+=charSet.length();
                    if(x==charSet.length()){
                        x++;
                    }
                }
                return x;
            }
            
            

            public void refactor(){
               // System.out.println("entering refactor=============================================================== \n");
                    
                    for(int i = 0; i < inInt.length;i++){

                            if(inInt[i]<0){
                               
                                    inInt[i]=under(inInt[i]);

                            }else if (inInt[i]>normalSet.length)
                               
                                inInt[i]=over(inInt[i]);
                       }
 
                    }
            public boolean resolveRefactoring(int x){
                boolean b= false;
                if (charSet.length()%x==0){
                   b=true; 
                }
                return b;
            }
              


                //</editor-fold>
             
            //fold contains actual shifting methods   
            //<editor-fold defaultstate="collapsed" desc="shifting methods">

               //ENCYRPTION:
               /**this method shifts all items out incoming text a single direction 
               defined by + or -**/
               public void singleShift(){

                   int i=0;    
                   while ( i < inInt.length) {
                        // System.out.println("plainTextInt[i] was = " + inInt[i] );
                         inInt[i]=inInt[i]+singleShift; 
                        // System.out.println("plainTextInt[i] is now = " + inInt[i] );
                         i++;
                   }	
                  refactor();
               }

                      /**DECRYPTION: this method shifts all items out incoming text a single direction 
               defined by + or -**/
               public void unSingleShift(){
                   //System.out.println("unShiftSingle called \n\n");
                   int i=0;    
                   while ( i < inInt.length) {
                        // System.out.println("plainTextInt[i] was = " + inInt[i] );
                         inInt[i]=inInt[i]-singleShift; 
                        // System.out.println("plainTextInt[i] is now = " + inInt[i] );
                         i++;
                   }	
                   refactor();                }


                /**ENCRYPT:shifts the inText even index values one direction/value
               and then the odd index values out another direction/value**/
                public void splitShift(){
                    // System.out.println("splitShift called \t\t");
                    for(int i=0;i<inInt.length;i++){
                           if(i%2==0){
                               inInt[i]+=splitShiftEven;
                           }
                           else if(i%2!=0){
                               inInt[i]-=splitShiftOdd;
                           }
                       }
                       refactor();
                }


               //DECRYPTION:
               /**shifts the inText even index values one direction/value
               and then the odd index values out another direction/value**/
                public void unSplitShift(){	
                    for(int i=0;i<inInt.length;i++){
                           if(i%2==0){
                               inInt[i]-=splitShiftEven;
                           }
                           else if(i%2!=0){
                               inInt[i]+=splitShiftOdd;
                           }
                       }
                      refactor();
                }
               
               /**shifts every character of intext by a different value**/
               public void allShift(){
                   for (int i =0;i<allShift.length;i++){
                       inInt[i]+=allShift[i];
                   }

               }
               
                /**UNshifts every character of intext by a different value**/
                public void unAllShift(){
                   for (int i =0;i<allShift.length;i++){
                       inInt[i]-=allShift[i];
                   }

                }

//</editor-fold>
                
            //fold contains main encrypting methods   
            //<editor-fold defaultstate="collapsed" desc="shifting methods">
                
                //the only encryption method to be used
                public void encrypt(){

                    toInt();
                    refactor();
                    singleShift();
                    refactor();
                    splitShift();
                    refactor();
                    allShift();
                    refactor();
                    toChar();
                    }
                
                //the only decryption method to be used
                public void decrypt(){
                    refactor();
                    toInt();
                    unSplitShift();
                    refactor();
                    unSingleShift();
                    refactor();
                    unAllShift();
                    refactor();
                    toChar();
                    outText=String.copyValueOf(out);    
                }
   
             //</editor-fold>
  
        public void zeroFaultShifts(){
            if(singleShift==charSet.length())
                  singleShift=0;
            if(splitShiftEven==charSet.length())
                splitShiftEven=0;
            if(splitShiftOdd==charSet.length())
                splitShiftOdd=0;
            for(int i=0;i<allShift.length;i++){
                if (allShift[i]==charSet.length())
                    allShift[i]=0;
            }
        }
          
          
   
    }           
                






