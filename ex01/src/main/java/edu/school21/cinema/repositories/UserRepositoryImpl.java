package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepositoryImpl implements UserRepository {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);
  private JdbcTemplate jdbcTemplate;

  public UserRepositoryImpl(DriverManagerDataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public int save(User user) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement("INSERT INTO users (username, password, lastname, phone) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, user.getUserName());
      ps.setString(2, user.getPassword());
      ps.setString(3, user.getLastName());
      ps.setString(4, user.getPhone());
      return ps;
    }, keyHolder);

    return (int) keyHolder.getKeys().get("id");
  }

  @Override
  public User getByUserName(String userName) {
    try {
      return jdbcTemplate.queryForObject("SELECT * FROM users WHERE users.username='" + userName + "'", new UserRowMapper());
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }
}

class UserRowMapper implements RowMapper<User> {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserRowMapper.class);

  @Override
  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    String username = rs.getString("username");
    int id = rs.getInt("id");
    String phone = rs.getString("phone");
    String lastName = rs.getString("lastname");
    String password = rs.getString("password");
    return new User(id, username, password, lastName, phone);
  }
}
