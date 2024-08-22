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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;


/**
 *
 * @author Zeronerva
 */
public class Swaps extends Cipher {
    
    //<editor-fold defaultstate="collapsed" desc="declaring class variables">
    
    
    public long seed;
    //a standard random to be used to randomize charSet
    public Random r;
    //swap set
    public String swapSet;
    //</editor-fold>
    

    
    public Swaps(String in, long seed) throws IOException {
        //System.setOut(new PrintStream(System.out, true, "UTF8"));
        //use above line to force netbeans so output all utf8 chars to output log
        this.seed = seed;
        this.inText=in;
        this.r=new Random(seed);
        setCharSet();
        
        swapSet = shuffleString(charSet);
    }
    //constructor requires a seed of long type
    public Swaps(long seed) throws IOException {
        //uncomment next line to force netbeans to show all utf8 chars in output
        //System.setOut(new PrintStream(System.out, true, "UTF8"));

        this.seed = seed;
        
        this.r=new Random(seed);
        setCharSet();
        
        swapSet = shuffleString(charSet);
        
      
        

        
    }

  
    //</editor-fold>
       
    //<editor-fold defaultstate="collapsed" desc="declaring get/set methods">
    //set charSet equal to raw char data 
    public final void setCharSet() throws IOException {
        String fileLocation="src//main//resources//eeafcharset.txt";
        InputStream is =getClass().getClassLoader().getResourceAsStream("eeafcharset.txt");
        this.charSet= new String(Files.readAllBytes(Paths.get(fileLocation)));
    }
    
    public String getOutText() {
        return outText;
    }
    
    public long getSeed(){
        return seed; 
    }
    
      public String getSwapSet(){
        return swapSet; 
    }
      
      public int getSwapSetLength(){
          return swapSet.length();
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    @Override
    public String getInText() {
        return inText;
    }

    @Override
    public void setInText(String inText) {
        this.inText = inText;
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="main encrypt methods">
    
    //THIS IS THE ONLY SUCH METHOD TO BE USED 
    public void encrypt(){
        encryptPlainText(findEncryptIndicies());
        System.out.println("outText = " + outText);

        
    }
    //THIS IS THE ONLY SUCH METHOD TO BE USED 
    public void decrypt(){
        
        
        decryptCipherText(findDecryptIndicies());
       
        
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="secondary encrypt methods">
    //encrypts plainText
    public void encryptPlainText(int[] indicies){
        char[] out;
        out = new  char [inText.length()];
        for(int i=0;i<inText.length();i++){
            char c =swapSet.charAt(indicies[i]);
            char p =out[i];
            out[i]=swapSet.charAt(indicies[i]);
        }
        String s=new String(out);
       this.outText= s;    
    }
    
    //decrypts cipherText
    public void decryptCipherText(int[] indicies){
        char[] out=new char [inText.length()];
        for(int i=0;i<out.length;i++){
            out[i]=super.charSet.charAt(indicies[i]);
        }
        String s=new String(out);
       this.outText= s;
    
    }
    //</editor-fold>
  
    //<editor-fold defaultstate="collapsed" desc="index searching">

    
    public int findCharIndex(char c,String s){
        int index=s.indexOf(c);
        return index;
      }
    

    //finds integer of character values by index depending on randomization of project shuffle build values
    public int[] findEncryptIndicies(){
        int[] indicies=new int[inText.length()];
        for(int i=0;i<indicies.length;i++){
            indicies[i]=findCharIndex(inText.charAt(i),charSet);
        }

    return indicies;
    }
    
    //finds character of integer values by index depending on randomization of project shuffle build values
    public int[] findDecryptIndicies(){
        //System.out.println("\n======================finding ciphertext indicies===================== \n");
        int[] indicies=new int[inText.length()];
        for(int i=0;i<indicies.length;i++){
            indicies[i]=findCharIndex(inText.charAt(i),swapSet);
            
        }

    return indicies;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="build swap set">
    
    
    //shuffles a string determinstaclly randomly 
    public String shuffleString(String s)
    {
        ArrayList<Character> chars
        = new ArrayList<>(
                 charSet.chars()
                .mapToObj(e -> (char) e)
                .collect(
                        Collectors.toList()
                )
        ); 
        
      Collections.shuffle(chars,r);
      String shuffled ;
        shuffled = chars.stream()
                  .map(e->e.toString())
                  .collect(Collectors.joining());

      return shuffled;
    }
    //dedupes charset (unused)
    public String deDupe(){
       String noDuplicates = Arrays.asList(charSet.split(""))
                     .stream()
                     .distinct()
                     .collect(Collectors.joining());
       return noDuplicates;
    }
    
    //verifys charset length matches swapset
    public void verifyCharSetLengths(){
        boolean b=false;
        if (charSet.length()==swapSet.length()){
            System.out.println("charSet length - swapSet length = " + charSet.length()+"-"+swapSet.length());
            b=true;
        } 
    }
    //compare 2 bytes
    public boolean compareBytes(char a, char b){
        boolean c =false;
        String temp1=""+1;
            String temp2=""+2;
            byte[]bytesA=temp1.getBytes();
            byte[]bytesB=temp2.getBytes();
            if (Arrays.equals(bytesA, bytesB)){
                c=true;
                System.out.println("match ="+b);
            }
        return c;
    }
     
     //</editor-fold>
    
//end class    


    
  
    
 }
    
    


