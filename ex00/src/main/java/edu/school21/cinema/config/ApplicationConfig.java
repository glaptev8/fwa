package edu.school21.cinema.config;

import edu.school21.cinema.repositories.SignInRepository;
import edu.school21.cinema.repositories.SignInRepositoryImpl;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.repositories.UserRepositoryImpl;
import edu.school21.cinema.services.SignInService;
import edu.school21.cinema.services.UserService;
import edu.school21.cinema.validation.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;

@Configuration
@PropertySource("../application.properties")
public class ApplicationConfig {

  @Value("${spring.datasource.driver-class-name}")
  private String driverClassName;
  @Value("${spring.datasource.url}")
  private String url;
  @Value("${spring.datasource.username}")
  private String userName;
  @Value("${spring.datasource.password}")
  private String password;
  @Value("${images.path}")
  private String imagesPath;

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  public DriverManagerDataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(driverClassName);
    dataSource.setUrl(url);
    dataSource.setUsername(userName);
    dataSource.setPassword(password);
    return dataSource;
  }

  @Bean
  public UserRepository userRepository(DriverManagerDataSource dataSource) {
    return new UserRepositoryImpl(dataSource);
  }

  @Bean
  public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return new UserService(userRepository, passwordEncoder);
  }

  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("i18n/messages");
    messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
    return messageSource;
  }

  @Bean
  public MessageService messageService() {
    return new MessageService();
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SignInRepository signInRepository(DriverManagerDataSource dataSource) {
    return new SignInRepositoryImpl(dataSource);
  }

  @Bean
  public SignInService signInService(SignInRepository signInRepository) {
    return new SignInService(signInRepository);
  }

  @Bean("pathToImages")
  public String pathToImages() {
    return imagesPath;
  }
}
