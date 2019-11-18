/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.ukdw.Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author WINDOWS
 */
public class Konek {
    private static Connection conn = null;
    
    public static Connection getConnect(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:Wangsadb.db");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Databse tidak ditemukan");
        }
        return conn;
 
    }
}
