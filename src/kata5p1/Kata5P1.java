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
        stmt.executeUpdate("drop table if exists emails");
        stmt.executeUpdate("create table emails (Id integer primary key autoincrement, Mail string not null)");
        
        String fileName = "D:/Usuario/Desktop/DAVID/Universidad/IS2/Practica Lab 5/emails.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
        String mail="";
        
        while(((mail = reader.readLine()) != null)) {
            if(!mail.contains("@")) continue;
            stmt.executeUpdate("insert into emails (Mail) values('"+mail+"')");   
        }
        reader.close();
        
        query = "select * from emails";
        rs = stmt.executeQuery(query);
        while (rs.next()){           
            int Id = rs.getInt("Id");
            String mails = rs.getString("Mail");
            System.out.println(Id + "\t" + mails);
        }
        
        query = "select count (*) from emails";
        rs = stmt.executeQuery(query);
        while (rs.next()){           
            
            int num = rs.getInt(1);
            System.out.println(num);
        }
        
    }
}

/*
statement.setQueryTimeout(30);  // set timeout to 30 sec.

      statement.executeUpdate("drop table if exists person");
      statement.executeUpdate("create table person (id integer, name string)");
      statement.executeUpdate("insert into person values(1, 'leo')");
      statement.executeUpdate("insert into person values(2, 'yui')");
      ResultSet rs = statement.executeQuery("select * from person");
*/