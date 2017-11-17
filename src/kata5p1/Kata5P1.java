package kata5p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Kata5P1 {

    
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        
        Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:D:/Usuario/Desktop/DAVID/Universidad/IS2/Practica Lab 5/SQLiteDatabaseBrowserPortable/Data/KATA5.db");
        String query = "select * from PEOPLE";
        Statement stmt = null;
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            String name = rs.getString("Name");
            int Id = rs.getInt("Id");
            String apellidos = rs.getString("Apellidos");
            String departamento = rs.getString("Departamento");
            System.out.println(Id + "\t" + name + "\t" + apellidos + "\t" + departamento);
        }
    }
}
