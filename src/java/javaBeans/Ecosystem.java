/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaBeans;

import java.io.Serializable;

/**
 *
 * @author jose
 */
public class Ecosystem implements Serializable {
    private String ecosystemName;
    
    public Ecosystem(){
        ecosystemName="";
    }
    public Ecosystem(String ecosystemName){
        this.ecosystemName=ecosystemName;
    }
    
    public String getEcosystem(){
        return ecosystemName;
    }
    public void setEcosystem(String eco){
        ecosystemName=eco;
    }
}
