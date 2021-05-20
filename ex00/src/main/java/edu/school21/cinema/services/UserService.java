package edu.school21.cinema.services;

import edu.school21.cinema.exceptions.ValidationException;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.util.Error;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  public int saveUser(User user) throws ValidationException {
    ValidationException validation = new ValidationException();
    if (userRepository.getByUserName(user.getUserName()) == null) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      return userRepository.save(user);
    } else {
      Error error = new Error("username.exist", user.getUserName());
      validation.addError(error);
      throw validation;
    }
  }

  public void saveIpLogIn() {

  }

  public User signIn(String userName, String password) throws ValidationException {
    User byUserName = userRepository.getByUserName(userName);
    if (byUserName == null) {
      throw new ValidationException("user.not.found");
    } else if (passwordEncoder.matches(password, byUserName.getPassword())) {
      return byUserName;
    } else {
      throw new ValidationException("password.incorrect");
    }
  }

  public User getUserByName(String name) {
    return userRepository.getByUserName(name);
  }
}
