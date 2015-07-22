package com.openhr.factories;

import static java.lang.Class.forName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PayrollSettingsConnection {

    private static Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public static void init() {
        if (connection == null) {
            new PayrollSettingsConnection();
        }
    }

    public PayrollSettingsConnection() {
        try {
            forName("com.mysql.jdbc.Driver");
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/freehrms", "root", "root");
                statement = connection.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static final String UPDATE_OPERATION = "update";
    public static final String INSERT_OPERATION = "insert";

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        PayrollSettingsConnection.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

}
