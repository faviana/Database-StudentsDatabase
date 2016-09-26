package com.ironyard.services;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by favianalopez on 9/26/16.
 */
public class Database {
    public void init() throws SQLException {
        Server.createWebServer().start();
        Statement stmt = getConnection().createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS students (id IDENTITY, name VARCHAR, age INT)");

    }

    public void clear() throws SQLException{
        Statement stmt = getConnection().createStatement();
        stmt.execute("DELETE FROM students");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:./main", "sa", null);
    }

}
