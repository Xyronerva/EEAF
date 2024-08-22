/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.exumbra.EEAF;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Nerxy
 */
public class EEAFTEST {
    
    public static String fails="";
    
    public static void main(String[] args) throws IOException {
        //String test = new String(Files.readAllBytes(Paths.get("C:\\Users\\Nerxy\\Documents\\entorpytestkey.txt")));
        Path path = Paths.get("C:\\Users\\Nerxy\\Documents\\callofcthuluhalf.txt");
        //String test = Files.readString(path, StandardCharsets.UTF_8);
        //String test = Files.readString(path);
        String test="abcdefghijklmnopqrstuvwxyz";
        String test2="䀯䀰䀱䀲䀳䀴䀵䀶䀷䀸䀹䀺䀻䀼䀽䀾䀿䁀䁁䁂䁃䁄䁅䁆䁇";
        long seed=7777777;
        EEAF eeaf = new EEAF(seed,"test.key");
        //eeaf.sendMessage(test);
        String results=eeaf.sendMessage(test);
        System.out.println("encrypt results = " + results);
        results=eeaf.recieveMessage(results);
         
        System.out.println("decrypt results = " + results);
        
//        System.out.println("changing intext length with new intext");
//        
//        eeaf.handler.newOutgoingMessage(test2);
//        System.out.println(eeaf.handler.messages.size());
//        
//        String results2 = eeaf.handler.messages.get(1).getMessageText();
//        System.out.println("encrypt results = " + results2);
//        eeaf.handler.newIncomingMessage(results2);
//        results= eeaf.handler.messages.get(1).getMessageText();
//        System.out.println("test2           = " + test2);
//        System.out.println("decrypt results = " + results);
        
        
        
           if(test.equals(results)){
               System.out.println("success. match!");
           }else if(test.equals(results)==false){
               System.out.println("Failure");
               //fails+=findFailedCharacters(test,results);
               
           }
        
        
        //eeaf.sm.testRun();
        //eeaf.cm.checkPermutes();
        
    }
//    public static boolean checkCharBytes(char a, char b){
//        boolean c =false;
//        String s=""+a; String t=""+b;
//        c=compareStringBytes(s,t);
//        return c;
//    }
//    public static String findFailedCharacters(String inText, String outText){
//        
//        for(int i=0;i<inText.length();i++){
//           if(checkCharBytes(inText.charAt(i),outText.charAt(i))==false){
//               fails+=outText.charAt(i);
//           }
//        }
//        return fails;
//    }
}
