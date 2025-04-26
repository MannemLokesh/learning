package com.lokesh;

import java.sql.*;

// This class demonstrates use of multiple inserts within a single SQL
public class JDBCExample14 {

   static final String DB_URL = "jdbc:mysql://localhost/organization";
   static final String USER = "root";
   static final String PASS = "root";

   public static void main(String args[]) {
      try{
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
         stmt.execute("INSERT INTO sampletable(id, name) VALUES(3, 'Sachin'), (4, 'Kishore')");
         System.out.println("----- Successfully inserted into table sampletable ----\n\n");
         System.out.println("Displaying records from sampletable table, showing inserted values");
         System.out.println("---------------------------");
         ResultSet rs = stmt.executeQuery("select * from sampletable");

         while(rs.next()){
            System.out.println("id: " + rs.getInt(1));
            System.out.println("name: " + rs.getString(2));
         }
      }catch(SQLException e){
         e.printStackTrace();
      }
   }
}