/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import javaBeans.User;
import java.sql.*;

/**
 *
 * @author jose
 */
public class UserDB {
 
    public static int insert(User user) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO USERS VALUES (?, ?, ?)";
        try {

            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getArea());
            
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    
    
    public static boolean userExists(String usuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT USERNAME FROM USERS WHERE USERNAME = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            boolean res = rs.next();
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean checkPassword(String user, String pass) {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT PASSWORD FROM USERS WHERE USERNAME = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            rs.next();
            if(rs.getString("PASSWORD").equals(pass)) {
                return true;
            } else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    public static User getUser(String user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM USERS WHERE USERNAME = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            User usuario = null;
            if (rs.next()) {
                usuario = new User();
                usuario.setUserName(rs.getString("USERNAME"));
                usuario.setPassword(rs.getString("PASSWORD"));
                usuario.setArea(rs.getString("AREA"));
            }
            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return usuario;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static int delete(String user) {
        
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "DELETE FROM USERS WHERE USERNAME = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user);
            int res = ps.executeUpdate();
            ps.close();
            pool.freeConnection(connection);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
}
