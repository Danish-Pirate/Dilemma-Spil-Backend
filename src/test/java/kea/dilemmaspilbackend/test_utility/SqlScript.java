package kea.dilemmaspilbackend.test_utility;


import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlScript {

    private Connection con;
    private Reader reader;

    public SqlScript(String pathScript) {

        // creates connection
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            con = DriverManager.getConnection("jdbc:h2:mem:db", "sa", "sa");
        } catch (SQLException e) {
            System.out.println("could not connect to database");
            e.printStackTrace();
        }

        // Reader for scl script
        try {
            reader = new BufferedReader(new FileReader(pathScript));
        } catch (FileNotFoundException e) {
            System.out.println("could not find script");
            e.printStackTrace();
        }
    }


    public void run() {
        ScriptRunner runner = new ScriptRunner(con);
        runner.runScript(reader);

        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("could not close");
            e.printStackTrace();
        }
    }


}
