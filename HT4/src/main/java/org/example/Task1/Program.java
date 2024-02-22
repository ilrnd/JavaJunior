package org.example.Task1;

import org.example.models.Course;

import java.sql.*;
import java.util.*;

public class Program {
    public static void main(String[] args) throws SQLException {
        final int COUNT = new Random().nextInt(5,10);
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "password";
        Connection connection = DriverManager.getConnection(url, user, password);
        createDB(connection);
        System.out.println("DB created. Done");
        useDB(connection);
        System.out.println("Use DB. Done");
        createTable(connection);
        System.out.println("Table create. Done");

        for (int i = 0; i < COUNT; i++){
            insertData(connection,Course.createCourse());
        }
        System.out.println("Insert data. Done");

        Collection<Course> courseCollection = readData(connection);
        for (Course course: courseCollection) {
            System.out.println(course);
        }
        System.out.println("Read data. Done");

        for (Course course: courseCollection) {
            course.updateTitle();
            course.updateDuration();
            updateData(connection, course);
        }
        System.out.println("Update data. Done");

        /*
        Удаление БД закомментировано, чтобы не "опустошать" базу при запуске
         */

        for (Course course: courseCollection)
            deleteData(connection, course.getId());
        System.out.println("Delete data. Done");
    }

    private static void createDB(Connection connection) throws SQLException{
        String createDataBaseSQL = "CREATE DATABASE IF NOT EXISTS schoolDB";
        try (PreparedStatement statement = connection.prepareStatement(createDataBaseSQL)){
            statement.execute();
        }
    }

    private static void useDB(Connection connection) throws SQLException {
        String useDatabaseSQL =  "USE schoolDB;";
        try (PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)) {
            statement.execute();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS courses (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), duration INT);";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
        }
    }


    private static void insertData(Connection connection, Course course) throws SQLException {
        String insertDataSQL = "INSERT INTO courses (title, duration) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
            statement.setString(1, course.getTitle());
            statement.setInt(2, course.getDuration());
            statement.executeUpdate();
        }
    }
    private static Collection<Course> readData(Connection connection) throws SQLException {
        ArrayList<Course> courses = new ArrayList<>();
        String readDataSQL = "SELECT * FROM courses;";
        try (PreparedStatement statement = connection.prepareStatement(readDataSQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int duration = resultSet.getInt("duration");
                courses.add(new Course(id, title, duration));
            }
            return courses;
        }
    }

    private static void updateData(Connection connection, Course course) throws SQLException {
        String updateDataSQL = "UPDATE courses SET title=?, duration=? WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(updateDataSQL)) {
            statement.setString(1, course.getTitle());
            statement.setInt(2, course.getDuration());
            statement.setInt(3, course.getId());
            statement.executeUpdate();
        }
    }

    private static void deleteData(Connection connection, int id) throws SQLException {
        String deleteDataSQL = "DELETE FROM courses WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(deleteDataSQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }}