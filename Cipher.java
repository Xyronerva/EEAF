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


/**
 *
 * @author Zeronerva
 */
//base abstract class all ciphers should inherit from
public abstract class Cipher {
    
//<editor-fold defaultstate="collapsed" desc="declaring variables">
   
    //all possible chars this cipher will use
    public String charSet = new String();
    
    //standard in text for all ciphers
    public String inText= new String();
    
    //standard out text for all ciphers
    public String outText = new String();
    
    
//</editor-fold>
    
    
//<editor-fold defaultstate="collapsed" desc="constructors">
    public String getInText() {
        return inText;
    }

    public void setInText(String in) {
        this.inText = in;
    }

    public String getOutText() {
        return outText;
    }


    public void setOutText(String outText) {
        this.outText = outText;
    }
    
//</editor-fold>
    
}
