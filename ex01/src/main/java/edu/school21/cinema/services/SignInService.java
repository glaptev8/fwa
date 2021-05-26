package edu.school21.cinema.services;

import edu.school21.cinema.models.SignIn;
import edu.school21.cinema.repositories.SignInRepository;

import java.util.List;

public class SignInService {

  private SignInRepository signInRepository;

  public SignInService(SignInRepository signInRepository) {
    this.signInRepository = signInRepository;
  }

  public int saveSignIn(String ip, int userId) {
    return signInRepository.save(userId, ip);
  }

  public List<SignIn> getSignInListByUserId(int id) {
    return signInRepository.getSignInByUserId(id);
  }
}
