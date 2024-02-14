/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.mspace.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mspace
 */
public class DBConnection {

    //establish connection to the database
    private static final String db_url1 = "jdbc:mysql://localhost:3306/dbPrimefaces";
    private static final String db_username1 = "skwaweruke";
    private static final String db_password1 = "skwaweruke";

    private static final String db_url2 = "jdbc:mysql://localhost:3306/dbMSpace";
    private static final String db_username2 = "skwaweruke";
    private static final String db_password2 = "skwaweruke";

    public static Connection getConnection1() {

        Connection con = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(db_url1, db_username1, db_password1);


        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return con;
    }

    public static Connection getConnection2() {

        Connection con = null;

        try {
            try {
                Class.forName("com.mysql.jdbc.Driver"); 
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

            con = DriverManager.getConnection(db_url2, db_username2, db_password2);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;

    }

}
