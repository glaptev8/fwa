package edu.school21.cinema.repositories;

import edu.school21.cinema.models.SignIn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SignInRepositoryImpl implements SignInRepository {

  private JdbcTemplate jdbcTemplate;
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public SignInRepositoryImpl(DriverManagerDataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public int save(int userId, String ip) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement("INSERT INTO \"signIn\" (date, ip, id_user) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
      ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
      ps.setString(2, ip);
      ps.setInt(3, userId);
      return ps;
    }, keyHolder);

    return (int) keyHolder.getKeys().get("id");
  }

  @Override
  public List<SignIn> getSignInByUserId(int id) {
    List<SignIn> signInList;
    String querySql = "SELECT ip, date FROM \"signIn\" WHERE id_user = " + id;
    return jdbcTemplate.query(querySql,
      (rs, rowNum) -> new SignIn(rs.getString("ip"), LocalDateTime.parse(formatter.format(rs.getTimestamp("date").toLocalDateTime()), formatter)));
  }
}
