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
public class User implements Serializable{
    
    private String userName;
    private String password;
    private String area;
    
    public User(){
        userName="";
        password="";
        area="";
    }
    
    public User(String user, String pass, String area){
        userName=user;
        password=pass;
        this.area=area;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getArea() {
        return area;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
    
}
