package edu.school21.cinema.servlets;

import edu.school21.cinema.exceptions.ValidationException;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.SignInService;
import edu.school21.cinema.services.UserService;
import edu.school21.cinema.util.Error;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SingInServlet extends HttpServlet {

  private UserService userService;
  private SignInService singInService;
  private String imagesPath;

  @Override
  public void init(ServletConfig config) {
    ServletContext context = config.getServletContext();
    ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
    this.userService = springContext.getBean(UserService.class);
    this.singInService = springContext.getBean(SignInService.class);
    this.imagesPath = (String) springContext.getBean("pathToImages");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/html/signIn.html");
    dispatcher.forward(req, resp);
  }

  @Override
  protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    List<Error> errors;
    String userName = req.getParameter("username");
    String password = req.getParameter("password");
    try {
      User user = userService.signIn(userName, password);
      HttpSession session = req.getSession();
      session.setAttribute("user", user);
      singInService.saveSignIn(req.getRemoteAddr(), user.getId());
      user.setSignInList(singInService.getSignInListByUserId(user.getId()));
      resp.sendRedirect("/home");
    } catch (ValidationException e) {
      errors =  e.getErrors();
      RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/signIn.jsp");
      req.setAttribute("error", errors.get(0).getMessage());
      dispatcher.forward(req, resp);
    }
  }
}