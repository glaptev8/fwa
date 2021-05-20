package edu.school21.cinema.repositories;

import edu.school21.cinema.models.SignIn;

import java.util.List;

public interface SignInRepository {
  int save(int userId, String ip);

  List<SignIn> getSignInByUserId(int id);
}
