package com.map.repository;

import com.map.exception.UserAlreadyExistException;
import com.map.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private static final Logger LOG = LoggerFactory.getLogger(UserRepository.class);
    private Connection connection;

    public UserRepository(@Value("${spring.datasource.url}") String url,
                          @Value("${spring.datasource.username}") String user,
                          @Value("${spring.datasource.password}") String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            LOG.info("Connected to the mapa-bravo database successfully");
        } catch (SQLException e) {
            LOG.info("Error while connecting to the database:" + e.getMessage());
        }
    }

    public User addUser(User user) {
        try {
            String query = "insert into map.user (nickname, city, country, zipCode) values (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            checkIfUserExists(user.getNickname());
            ps.setString(1, user.getNickname());
            ps.setString(2, user.getCity());
            ps.setString(3, user.getCountry());
            ps.setString(4, user.getZipCode());
            ps.executeUpdate();
            ps.close();
            LOG.info("User [{}] has been added correctly.", user.getNickname());
        } catch (SQLException e) {
            LOG.info("Error while creating user:" + e.getMessage());
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            String query = "select id, nickname, city, country, zipcode from map.user";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nickname = rs.getString("nickname");
                String city = rs.getString("city");
                String country = rs.getString("country");
                String zipCode = rs.getString("zipCode");
                users.add(new User(id, nickname, city, zipCode, country));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            LOG.info("Error while fetching users:" + e.getMessage());
        }
        return users;
    }

    public User getUserByNickname(String userNickname) {
            User user = new User();
        try {
            PreparedStatement ps = connection.prepareStatement("select nickname from map.user where nickname=?");
            checkIfUserExists(user.getNickname());
            ps.setString(1, userNickname);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setNickname(rs.getString("nickname"));
                user.setCity(rs.getString("city"));
                user.setCountry(rs.getString("country"));
                user.setZipCode(rs.getString("zipCode"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private void checkIfUserExists(String userNickname) {
        try {
            String query = "select nickname from map.user";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String nickname = rs.getString("nickname");
                if (nickname.equals(userNickname)) {
                    throw new UserAlreadyExistException(userNickname);
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            LOG.info("Error while checking users:" + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}