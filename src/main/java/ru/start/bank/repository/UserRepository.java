package ru.start.bank.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.start.bank.entity.UserEntity;


import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate recommendationsJdbcTemplate) {
        this.jdbcTemplate = recommendationsJdbcTemplate;
    }

    private static final RowMapper<UserEntity> USER_ROW_MAPPER = (rs, rowNum) -> {
        UserEntity user = new UserEntity();
        user.setId(UUID.fromString(rs.getString("id")));
        user.setUserName(rs.getString("username"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        return user;
    };

    public List<UserEntity> findByUserNameIgnoreCase(String userName) {
        String sql = """
                SELECT id, username, first_name, last_name
                FROM users
                WHERE UPPER(username) = UPPER(?)
                """;
        return jdbcTemplate.query(sql, USER_ROW_MAPPER, userName);
    }
}

