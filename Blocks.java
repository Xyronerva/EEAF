/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exumbra.EEAF;
//<editor-fold defaultstate="collapsed" desc="importing class dependencies">


//</editor-fold>
/**
 *
 * @author Zeronerva
 * 
 * 
 */
public class Blocks extends Cipher {
            
        
//<editor-fold defaultstate="collapsed" desc="declaring class variables">
       
    //permute to randomize text with
    public int[] permute; 
    
    //inText
    public char[] in;
    
    //outText
    public char[] out;
       
 //</editor-fold>   
    
//<editor-fold defaultstate="collapsed" desc="declaring constructors">
    
    //consturctor 1
    public Blocks(String inText,int[] pMute ) {
        super.inText = inText;
        this.permute=pMute;
    } 
    
    //super.inText = inText;
    public Blocks(int[] pMute ) {
        this.permute=pMute;
        
    } 

    public Blocks() {
    }

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="declaring get/set">
    public void setIn(char[] in) {    
        this.in = in;
    }
    
     
    public int[] getPermute(){
        return permute;
 
    }

    public void setPermute(int[] permute) {
        this.permute = permute;
    }

    public char[] getIn() {
        return in;
    }

    public char[] getOut() {
        return out;
    }

    public void setOut(char[] out) {
        this.out = out;
    }
    
    

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="declaring class methods">
    //MainEncryption method
    public void encrypt() {
        int i=0;
        int p=0;
   
        in=inText.toCharArray();
        out=new char[in.length];
        while(i<out.length){
            out[permute[p]] = in[i];
            i++; p++;
        }
        outText=String.valueOf(out);
    }
    
    //main Decryption method
    public char[] decrypt(){
      int i=0;
        int p=inText.length();
        p--;
        in=inText.toCharArray();
        out=new char[in.length];
        char[] reverse = new char[inText.length()];
           while(i <in.length){ 	
               out[i] = in[permute[p]];
                p--; i++;
            }
            p=0;
            for(i=in.length-1;i>=0; i--) {
                  reverse[p] = out[i]; 
                         p++;
        }
           out=reverse;
           outText=String.valueOf(reverse);
           return reverse;
        }
    
//</editor-fold>
    
}
