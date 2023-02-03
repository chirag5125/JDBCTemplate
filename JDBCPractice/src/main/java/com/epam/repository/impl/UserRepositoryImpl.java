package com.epam.repository.impl;

import com.epam.DTO.Users;
import com.epam.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UsersRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    final String QUERY = "SELECT DISTINCT id, firstname, friends, COUNT(postid) as likes, MAX(Likes.timestamp) as timestamp " +
            "FROM " +
            "(SELECT Users.id, Users.firstname, COUNT(Friendships.userid2) as friends FROM Users " +
            "JOIN Friendships ON Users.id=Friendships.userid1 " +
            "GROUP BY Users.id " +
            "Having friends > 100 " +
            "ORDER BY Users.id) AS friendGrouped " +
            "LEFT JOIN Likes ON Likes.userid = friendGrouped.id " +
            "GROUP BY id " +
            "HAVING likes > 100 AND timestamp > \"2025-01-01 00:00:00\"" +
            "ORDER BY id;";

    @Override
    public void count() {
        jdbcTemplate.query(QUERY, (rs, rowNum) -> new Users(rs.getInt("id"),
                        rs.getString("firstname"))).forEach(user -> System.out.println(user.getName()));
    }
}
