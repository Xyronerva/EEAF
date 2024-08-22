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


import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Zeronerva
 */
public class CipherManager  {
    
    //<editor-fold defaultstate="collapsed" desc="declaring class variables">
            
        //how many passes per block
        public int passes;
        
        public String inText;
        
        public String outText;

        public ArrayList <CipherThread> ciphers= new ArrayList();
        
        public CipherThread[] ctarr;
        
        public int iterations=0;
        
        public String[] eResults;
        public String[] deResults;
        
        public String currentMutation;
        
    //</editor-fold>
    
//<editor-fold defaultstate = "collapsed" desc="declaring constructors">
    public CipherManager(String in,int passes, CipherThread[] ct) {
        
        this.passes = passes;
        this.inText= in;
        for(int i =0; i <ct.length;i++){
            ciphers.add(ct[i]);
        }
        
        eResults=new String[50];
        deResults=new String[50];
        
     ciphers.get(0).setInText(inText);
     ciphers.get(0).block.setInText(inText);
    //ciphers.get(ciphers.size()-1).shift.setInText(inText);
     ctarr=ct;
     
             
    }
//</editor-fold>

     
	

       
//<editor-fold defaultstate =  cv fcx"collapsed" desc="declaring get/set methods">
    
    
    public int getPasses() {
        return passes;
    }

    public void setPasses(int passes) {
        this.passes = passes;
    }

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

    public ArrayList<CipherThread> getCiphers() {
        return ciphers;
    }

    public void setCiphers(ArrayList<CipherThread> ciphers) {
        this.ciphers = ciphers;
    }
    

    
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="declaring class methods">
        
        //<editor-fold defaultstate="collapsed" desc="base encryption methods">
//        public void encryptOne(int index){
//              ciphers.get(index).setInText(ciphers.get(index-1).getOutText());
//            ciphers.get(index).encrypt();
//        }
//
//        public void decryptOne(int index){
//              ciphers.get(index).setInText(ciphers.get(index+1).getOutText());
//            ciphers.get(index).decrypt();
//        }
//        
//        public void sameTime(){
//            checkPermutes();
//            int count = 0;
//                ciphers.get(0).encrypt();
//                System.out.println("encrypting cipher =  " + count);count++;
//              for(int i=1; i<ctarr.length;i++){
//                encryptOne(i);
//                decryptOne(i);
//                System.out.println("encrypting cipher =  " + count);count++;
//              }
//            outText=ciphers.getLast().getOutText();
//            System.out.println("outText = " + outText);
//            outText=ciphers.getFirst().getOutText();
//            System.out.println("outText = " + outText);
//        }
//
//        public void encryptAll(){
//            checkPermutes();
//            int count = 0;
//                ciphers.get(0).encrypt();
//                System.out.println("encrypting cipher =  " + count);count++;
//               // Systemout.println("iterations = " + iterations);
//              for(int i=1; i<ciphers.size()-1;i++){
//                encryptOne(i);
//                System.out.println("encrypting cipher =  " + count);count++;
//              }
//            outText=ciphers.get(ctarr.length-1).getOutText();
//        }
//
//        public void decryptAll(){
//            int count=0;
//            ciphers.getLast().decrypt();
//            System.out.println("decrypting cipher =  " + count);count++;
//                //System.out.println("iterations = " + iterations);
//              for(int i=ciphers.size()-2; i>=0;i--){
//                decryptOne(i);
//                System.out.println("decrypting cipher =  " + count);count++;
//
//               // iterations++;
//                //System.out.println("iterations = " + iterations);
//              }
//            outText=ciphers.get(0).getOutText();
//        }
//        //</editor-fold>
    
    
    
 
        public void encrypt1(){
            ciphers.get(0).block.setInText(inText);
            ciphers.get(0 ).encrypt();currentMutation=ciphers.get(0 ).getOutText();
            ciphers.get(1).setInText(currentMutation);
            ciphers.get(1 ).encrypt();currentMutation=ciphers.get(1 ).getOutText();
            outText=ciphers.get(1).getOutText();
            
        }
        
        public void decrypt1(){
            ciphers.get(1).shift.setInText(inText);
            ciphers.get(1).decrypt();currentMutation=ciphers.get(1).getOutText();
            ciphers.get(0).setInText(currentMutation);
            ciphers.get(0).decrypt();currentMutation=ciphers.get(0).getOutText();
            outText=ciphers.get(0).getOutText();
        }
    
    
        public String encrypt2(){
            ciphers.get(0).block.setInText(inText);
            ciphers.get(0 ).encrypt();currentMutation=ciphers.get(0 ).getOutText();eResults[0]=currentMutation;
            for(int i=1;i<50;i++){   
                ciphers.get(i ).setInText(currentMutation);ciphers.get(i ).encrypt();currentMutation=ciphers.get(i ).getOutText();eResults[i]=currentMutation;
            }
            outText=ciphers.get(49).getOutText();
            return outText;
        }
        
        public String decrypt2(){
            ciphers.get(49).shift.setInText(inText);
            ciphers.get(49).decrypt();currentMutation=ciphers.get(49).getOutText();deResults[49]=currentMutation;
            for(int i=48;i>=0;i--){
                ciphers.get(i).setInText(currentMutation);ciphers.get(i).decrypt();currentMutation=ciphers.get(i).getOutText();deResults[i]=currentMutation;
            }
            outText=ciphers.get(0).getOutText();
            return outText;
        }
        
    
    
     

        public void encrypt(){
            ciphers.get(0).block.setInText(inText);
            System.out.println("ciphers.get(0).block.getIntext() = " + ciphers.get(0).block.getInText());
            ciphers.get(0 ).encrypt();currentMutation=ciphers.get(0 ).getOutText();
            ciphers.get(1 ).setInText(currentMutation);ciphers.get(1 ).encrypt();currentMutation=ciphers.get(1 ).getOutText();eResults[1 ]=currentMutation;												                                               
            ciphers.get(2 ).setInText(currentMutation);ciphers.get(2 ).encrypt();currentMutation=ciphers.get(2 ).getOutText();eResults[2 ]=currentMutation;					                                               
            ciphers.get(3 ).setInText(currentMutation);ciphers.get(3 ).encrypt();currentMutation=ciphers.get(3 ).getOutText();eResults[3 ]=currentMutation;					                                               
            ciphers.get(4 ).setInText(currentMutation);ciphers.get(4 ).encrypt();currentMutation=ciphers.get(4 ).getOutText();eResults[4 ]=currentMutation;					                                               
            ciphers.get(5 ).setInText(currentMutation);ciphers.get(5 ).encrypt();currentMutation=ciphers.get(5 ).getOutText();eResults[5 ]=currentMutation;					                                               
            ciphers.get(6 ).setInText(currentMutation);ciphers.get(6 ).encrypt();currentMutation=ciphers.get(6 ).getOutText();eResults[6 ]=currentMutation;					                                               
            ciphers.get(7 ).setInText(currentMutation);ciphers.get(7 ).encrypt();currentMutation=ciphers.get(7 ).getOutText();eResults[7 ]=currentMutation;					                                               
            ciphers.get(8 ).setInText(currentMutation);ciphers.get(8 ).encrypt();currentMutation=ciphers.get(8 ).getOutText();eResults[8 ]=currentMutation;					                                               
            ciphers.get(9 ).setInText(currentMutation);ciphers.get(9 ).encrypt();currentMutation=ciphers.get(9 ).getOutText();eResults[9 ]=currentMutation;					                                               
            ciphers.get(10).setInText(currentMutation);ciphers.get(10).encrypt();currentMutation=ciphers.get(10).getOutText();eResults[10]=currentMutation;					                                               
            ciphers.get(11).setInText(currentMutation);ciphers.get(11).encrypt();currentMutation=ciphers.get(11).getOutText();eResults[11]=currentMutation;					                                               
            ciphers.get(12).setInText(currentMutation);ciphers.get(12).encrypt();currentMutation=ciphers.get(12).getOutText();eResults[12]=currentMutation;					                                               
            ciphers.get(13).setInText(currentMutation);ciphers.get(13).encrypt();currentMutation=ciphers.get(13).getOutText();eResults[13]=currentMutation;					                                               
            ciphers.get(14).setInText(currentMutation);ciphers.get(14).encrypt();currentMutation=ciphers.get(14).getOutText();eResults[14]=currentMutation;					                                               
            ciphers.get(15).setInText(currentMutation);ciphers.get(15).encrypt();currentMutation=ciphers.get(15).getOutText();eResults[15]=currentMutation;					                                               
            ciphers.get(16).setInText(currentMutation);ciphers.get(16).encrypt();currentMutation=ciphers.get(16).getOutText();eResults[16]=currentMutation;					                                               
            ciphers.get(17).setInText(currentMutation);ciphers.get(17).encrypt();currentMutation=ciphers.get(17).getOutText();eResults[17]=currentMutation;					                                               
            ciphers.get(18).setInText(currentMutation);ciphers.get(18).encrypt();currentMutation=ciphers.get(18).getOutText();eResults[18]=currentMutation;					                                               
            ciphers.get(19).setInText(currentMutation);ciphers.get(19).encrypt();currentMutation=ciphers.get(19).getOutText();eResults[19]=currentMutation;					                                               
            ciphers.get(20).setInText(currentMutation);ciphers.get(20).encrypt();currentMutation=ciphers.get(20).getOutText();eResults[20]=currentMutation;					                                               
            ciphers.get(21).setInText(currentMutation);ciphers.get(21).encrypt();currentMutation=ciphers.get(21).getOutText();eResults[21]=currentMutation;					                                               
            ciphers.get(22).setInText(currentMutation);ciphers.get(22).encrypt();currentMutation=ciphers.get(22).getOutText();eResults[22]=currentMutation;					                                               
            ciphers.get(23).setInText(currentMutation);ciphers.get(23).encrypt();currentMutation=ciphers.get(23).getOutText();eResults[23]=currentMutation;					                                               
            ciphers.get(24).setInText(currentMutation);ciphers.get(24).encrypt();currentMutation=ciphers.get(24).getOutText();eResults[24]=currentMutation;					                                               
            ciphers.get(25).setInText(currentMutation);ciphers.get(25).encrypt();currentMutation=ciphers.get(25).getOutText();eResults[25]=currentMutation;					                                               
            ciphers.get(26).setInText(currentMutation);ciphers.get(26).encrypt();currentMutation=ciphers.get(26).getOutText();eResults[26]=currentMutation;					                                               
            ciphers.get(27).setInText(currentMutation);ciphers.get(27).encrypt();currentMutation=ciphers.get(27).getOutText();eResults[27]=currentMutation;					                                               
            ciphers.get(28).setInText(currentMutation);ciphers.get(28).encrypt();currentMutation=ciphers.get(28).getOutText();eResults[28]=currentMutation;					                                               
            ciphers.get(29).setInText(currentMutation);ciphers.get(29).encrypt();currentMutation=ciphers.get(29).getOutText();eResults[29]=currentMutation;					                                               
            ciphers.get(30).setInText(currentMutation);ciphers.get(30).encrypt();currentMutation=ciphers.get(30).getOutText();eResults[30]=currentMutation;					                                               
            ciphers.get(31).setInText(currentMutation);ciphers.get(31).encrypt();currentMutation=ciphers.get(31).getOutText();eResults[31]=currentMutation;					                                               
            ciphers.get(32).setInText(currentMutation);ciphers.get(32).encrypt();currentMutation=ciphers.get(32).getOutText();eResults[32]=currentMutation;					                                               
            ciphers.get(33).setInText(currentMutation);ciphers.get(33).encrypt();currentMutation=ciphers.get(33).getOutText();eResults[33]=currentMutation;					                                               
            ciphers.get(34).setInText(currentMutation);ciphers.get(34).encrypt();currentMutation=ciphers.get(34).getOutText();eResults[34]=currentMutation;					                                               
            ciphers.get(35).setInText(currentMutation);ciphers.get(35).encrypt();currentMutation=ciphers.get(35).getOutText();eResults[35]=currentMutation;					                                               
            ciphers.get(36).setInText(currentMutation);ciphers.get(36).encrypt();currentMutation=ciphers.get(36).getOutText();eResults[36]=currentMutation;					                                               
            ciphers.get(37).setInText(currentMutation);ciphers.get(37).encrypt();currentMutation=ciphers.get(37).getOutText();eResults[37]=currentMutation;					                                               
            ciphers.get(38).setInText(currentMutation);ciphers.get(38).encrypt();currentMutation=ciphers.get(38).getOutText();eResults[38]=currentMutation;					                                               
            ciphers.get(39).setInText(currentMutation);ciphers.get(39).encrypt();currentMutation=ciphers.get(39).getOutText();eResults[39]=currentMutation;					                                               
            ciphers.get(40).setInText(currentMutation);ciphers.get(40).encrypt();currentMutation=ciphers.get(40).getOutText();eResults[40]=currentMutation;					                                               
            ciphers.get(41).setInText(currentMutation);ciphers.get(41).encrypt();currentMutation=ciphers.get(41).getOutText();eResults[41]=currentMutation;					                                               
            ciphers.get(42).setInText(currentMutation);ciphers.get(42).encrypt();currentMutation=ciphers.get(42).getOutText();eResults[42]=currentMutation;					                                               
            ciphers.get(43).setInText(currentMutation);ciphers.get(43).encrypt();currentMutation=ciphers.get(43).getOutText();eResults[43]=currentMutation;					                                               
            ciphers.get(44).setInText(currentMutation);ciphers.get(44).encrypt();currentMutation=ciphers.get(44).getOutText();eResults[44]=currentMutation;					                                               
            ciphers.get(45).setInText(currentMutation);ciphers.get(45).encrypt();currentMutation=ciphers.get(45).getOutText();eResults[45]=currentMutation;					                                               
            ciphers.get(46).setInText(currentMutation);ciphers.get(46).encrypt();currentMutation=ciphers.get(46).getOutText();eResults[46]=currentMutation;					                                               
            ciphers.get(47).setInText(currentMutation);ciphers.get(47).encrypt();currentMutation=ciphers.get(47).getOutText();eResults[47]=currentMutation;					                                               
            ciphers.get(48).setInText(currentMutation);ciphers.get(48).encrypt();currentMutation=ciphers.get(48).getOutText();eResults[48]=currentMutation;					                                               
            ciphers.get(49).setInText(currentMutation);ciphers.get(49).encrypt();currentMutation=ciphers.get(49).getOutText();eResults[49]=currentMutation;					                                               

            outText=ciphers.get(49).getOutText();
        }
        public void decrypt(){
            ciphers.get(49).shift.setInText(inText);
            System.out.println("ciphers.get(0).shift.getIntext() = " + ciphers.get(0).shift.getInText());
            ciphers.get(49).decrypt();currentMutation=ciphers.get(49).getOutText();
            ciphers.get(48).setInText(currentMutation);ciphers.get(48).decrypt();currentMutation=ciphers.get(48).getOutText();deResults[48]=currentMutation;                                                               
            ciphers.get(47).setInText(currentMutation);ciphers.get(47).decrypt();currentMutation=ciphers.get(47).getOutText();deResults[47]=currentMutation;                                                               
            ciphers.get(46).setInText(currentMutation);ciphers.get(46).decrypt();currentMutation=ciphers.get(46).getOutText();deResults[46]=currentMutation;                                                               
            ciphers.get(45).setInText(currentMutation);ciphers.get(45).decrypt();currentMutation=ciphers.get(45).getOutText();deResults[45]=currentMutation;                                                               
            ciphers.get(44).setInText(currentMutation);ciphers.get(44).decrypt();currentMutation=ciphers.get(44).getOutText();deResults[44]=currentMutation;                                                               
            ciphers.get(43).setInText(currentMutation);ciphers.get(43).decrypt();currentMutation=ciphers.get(43).getOutText();deResults[43]=currentMutation;                                                               
            ciphers.get(42).setInText(currentMutation);ciphers.get(42).decrypt();currentMutation=ciphers.get(42).getOutText();deResults[42]=currentMutation;                                                               
            ciphers.get(41).setInText(currentMutation);ciphers.get(41).decrypt();currentMutation=ciphers.get(41).getOutText();deResults[41]=currentMutation;                                                               
            ciphers.get(40).setInText(currentMutation);ciphers.get(40).decrypt();currentMutation=ciphers.get(40).getOutText();deResults[40]=currentMutation;                                                               
            ciphers.get(39).setInText(currentMutation);ciphers.get(39).decrypt();currentMutation=ciphers.get(39).getOutText();deResults[39]=currentMutation;                                                               
            ciphers.get(38).setInText(currentMutation);ciphers.get(38).decrypt();currentMutation=ciphers.get(38).getOutText();deResults[38]=currentMutation;                                                               
            ciphers.get(37).setInText(currentMutation);ciphers.get(37).decrypt();currentMutation=ciphers.get(37).getOutText();deResults[37]=currentMutation;                                                               
            ciphers.get(36).setInText(currentMutation);ciphers.get(36).decrypt();currentMutation=ciphers.get(36).getOutText();deResults[36]=currentMutation;                                                               
            ciphers.get(35).setInText(currentMutation);ciphers.get(35).decrypt();currentMutation=ciphers.get(35).getOutText();deResults[35]=currentMutation;                                                               
            ciphers.get(34).setInText(currentMutation);ciphers.get(34).decrypt();currentMutation=ciphers.get(34).getOutText();deResults[34]=currentMutation;                                                               
            ciphers.get(33).setInText(currentMutation);ciphers.get(33).decrypt();currentMutation=ciphers.get(33).getOutText();deResults[33]=currentMutation;                                                               
            ciphers.get(32).setInText(currentMutation);ciphers.get(32).decrypt();currentMutation=ciphers.get(32).getOutText();deResults[32]=currentMutation;                                                               
            ciphers.get(31).setInText(currentMutation);ciphers.get(31).decrypt();currentMutation=ciphers.get(31).getOutText();deResults[31]=currentMutation;                                                               
            ciphers.get(30).setInText(currentMutation);ciphers.get(30).decrypt();currentMutation=ciphers.get(30).getOutText();deResults[30]=currentMutation;                                                               
            ciphers.get(29).setInText(currentMutation);ciphers.get(29).decrypt();currentMutation=ciphers.get(29).getOutText();deResults[29]=currentMutation;                                                               
            ciphers.get(28).setInText(currentMutation);ciphers.get(28).decrypt();currentMutation=ciphers.get(28).getOutText();deResults[28]=currentMutation;                                                               
            ciphers.get(27).setInText(currentMutation);ciphers.get(27).decrypt();currentMutation=ciphers.get(27).getOutText();deResults[27]=currentMutation;                                                               
            ciphers.get(26).setInText(currentMutation);ciphers.get(26).decrypt();currentMutation=ciphers.get(26).getOutText();deResults[26]=currentMutation;                                                               
            ciphers.get(25).setInText(currentMutation);ciphers.get(25).decrypt();currentMutation=ciphers.get(25).getOutText();deResults[25]=currentMutation;                                                               
            ciphers.get(24).setInText(currentMutation);ciphers.get(24).decrypt();currentMutation=ciphers.get(24).getOutText();deResults[24]=currentMutation;                                                               
            ciphers.get(23).setInText(currentMutation);ciphers.get(23).decrypt();currentMutation=ciphers.get(23).getOutText();deResults[23]=currentMutation;                                                               
            ciphers.get(22).setInText(currentMutation);ciphers.get(22).decrypt();currentMutation=ciphers.get(22).getOutText();deResults[22]=currentMutation;                                                               
            ciphers.get(21).setInText(currentMutation);ciphers.get(21).decrypt();currentMutation=ciphers.get(21).getOutText();deResults[21]=currentMutation;                                                               
            ciphers.get(20).setInText(currentMutation);ciphers.get(20).decrypt();currentMutation=ciphers.get(20).getOutText();deResults[20]=currentMutation;                                                               
            ciphers.get(19).setInText(currentMutation);ciphers.get(19).decrypt();currentMutation=ciphers.get(19).getOutText();deResults[19]=currentMutation;                                                               
            ciphers.get(18).setInText(currentMutation);ciphers.get(18).decrypt();currentMutation=ciphers.get(18).getOutText();deResults[18]=currentMutation;                                                               
            ciphers.get(17).setInText(currentMutation);ciphers.get(17).decrypt();currentMutation=ciphers.get(17).getOutText();deResults[17]=currentMutation;                                                               
            ciphers.get(16).setInText(currentMutation);ciphers.get(16).decrypt();currentMutation=ciphers.get(16).getOutText();deResults[16]=currentMutation;                                                               
            ciphers.get(15).setInText(currentMutation);ciphers.get(15).decrypt();currentMutation=ciphers.get(15).getOutText();deResults[15]=currentMutation;                                                               
            ciphers.get(14).setInText(currentMutation);ciphers.get(14).decrypt();currentMutation=ciphers.get(14).getOutText();deResults[14]=currentMutation;                                                               
            ciphers.get(13).setInText(currentMutation);ciphers.get(13).decrypt();currentMutation=ciphers.get(13).getOutText();deResults[13]=currentMutation;                                                               
            ciphers.get(12).setInText(currentMutation);ciphers.get(12).decrypt();currentMutation=ciphers.get(12).getOutText();deResults[12]=currentMutation;                                                               
            ciphers.get(11).setInText(currentMutation);ciphers.get(11).decrypt();currentMutation=ciphers.get(11).getOutText();deResults[11]=currentMutation;                                                               
            ciphers.get(10).setInText(currentMutation);ciphers.get(10).decrypt();currentMutation=ciphers.get(10).getOutText();deResults[10]=currentMutation;                                                               
            ciphers.get(9 ).setInText(currentMutation);ciphers.get(9 ).decrypt();currentMutation=ciphers.get(9 ).getOutText();deResults[9 ]=currentMutation;                                                               
            ciphers.get(8 ).setInText(currentMutation);ciphers.get(8 ).decrypt();currentMutation=ciphers.get(8 ).getOutText();deResults[8 ]=currentMutation;                                                               
            ciphers.get(7 ).setInText(currentMutation);ciphers.get(7 ).decrypt();currentMutation=ciphers.get(7 ).getOutText();deResults[7 ]=currentMutation;                                                               
            ciphers.get(6 ).setInText(currentMutation);ciphers.get(6 ).decrypt();currentMutation=ciphers.get(6 ).getOutText();deResults[6 ]=currentMutation;                                                               
            ciphers.get(5 ).setInText(currentMutation);ciphers.get(5 ).decrypt();currentMutation=ciphers.get(5 ).getOutText();deResults[5 ]=currentMutation;                                                               
            ciphers.get(4 ).setInText(currentMutation);ciphers.get(4 ).decrypt();currentMutation=ciphers.get(4 ).getOutText();deResults[4 ]=currentMutation;                                                               
            ciphers.get(3 ).setInText(currentMutation);ciphers.get(3 ).decrypt();currentMutation=ciphers.get(3 ).getOutText();deResults[3 ]=currentMutation;                                                               
            ciphers.get(2 ).setInText(currentMutation);ciphers.get(2 ).decrypt();currentMutation=ciphers.get(2 ).getOutText();deResults[2 ]=currentMutation;                                                               
            ciphers.get(1 ).setInText(currentMutation);ciphers.get(1 ).decrypt();currentMutation=ciphers.get(1 ).getOutText();deResults[1 ]=currentMutation;                                                               
            ciphers.get(0 ).setInText(currentMutation);ciphers.get(0 ).decrypt();currentMutation=ciphers.get(0 ).getOutText();deResults[0 ]=currentMutation;                                                               
			
            outText=ciphers.get(0).getOutText();


        }
    
        public void encryptOne(int index){
              ciphers.get(index).setInText(ciphers.get(index-1).getOutText());
            ciphers.get(index).encrypt();
        }

        public void decryptOne(int index){
              ciphers.get(index).setInText(ciphers.get(index+1).getOutText());
            ciphers.get(index).decrypt();
        }
        
        public void sameTime(){
            checkPermutes();
            int count = 0;
                ciphers.get(0).encrypt();
                ciphers.get(9);
              for(int i=1; i<ctarr.length;i++){
                encryptOne(i);
                decryptOne(i);
                System.out.println("encrypting cipher =  " + count);count++;
              }
            outText=ciphers.get(0).getOutText();
            System.out.println("outText = " + outText);
            outText=ciphers.get(0).getOutText();
            System.out.println("outText = " + outText);
        }

        public void encryptAll(){
            checkPermutes();
            int count = 0;
                ciphers.get(0).encrypt();
                System.out.println("encrypting cipher =  " + count);count++;
               // Systemout.println("iterations = " + iterations);
              for(int i=1; i<ciphers.size()-1;i++){
                encryptOne(i);
                System.out.println("encrypting cipher =  " + count);count++;
              }
            outText=ciphers.get(ctarr.length-1).getOutText();
        }

        public void decryptAll(){
            int count=0;
            ciphers.get(ciphers.size()-1).decrypt();
            System.out.println("decrypting cipher =  " + count);count++;
                //System.out.println("iterations = " + iterations);
              for(int i=ciphers.size()-1; i>=0;i--){
                decryptOne(i);
                System.out.println("decrypting cipher =  " + count);count++;

               // iterations++;
                //System.out.println("iterations = " + iterations);
              }
            outText=ciphers.get(0).getOutText();
        }
        //</editor-fold>
    
    public void setNewInText(String s){
        ciphers.get(0).block.setInText(s);
    }
    
    public void setNewOutText(String s){
        ciphers.get(ciphers.size()-1).shift.setInText(s);
    }
    
      public void checkPermutes(){
          for(int i=0;i<ctarr.length;i++){

              System.out.println("Arrays.toString(fp    Permute()) = " + Arrays.toString(ctarr[i].fp.getPermute()));
              System.out.println("Arrays.toString(block Permute()) = " + Arrays.toString(ctarr[i].block.getPermute()));
          
          }
      
      }

    //</editor-fold>
    }
    
