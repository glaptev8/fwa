package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

public interface UserRepository {
  int save(User user);

  User getByUserName(String userName);
}
