package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.SignInService;
import edu.school21.cinema.services.UserService;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@WebServlet(value = "/home", name = "HomeServlet")
public class HomeServlet extends HttpServlet {

  private SignInService singInService;
  private String imagesPath;

  @Override
  public void init(ServletConfig config) {
    ServletContext context = config.getServletContext();
    ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
    this.singInService = springContext.getBean(SignInService.class);
    this.imagesPath = (String) springContext.getBean("pathToImages");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("user");
    req.setAttribute("user", user);
    File imagesDir = new File(imagesPath + user.getId());
    if (!imagesDir.exists()) {
      imagesDir.mkdir();
    }
    req.setAttribute("pathImages", imagesPath + user.getId());
    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
    dispatcher.forward(req, resp);
  }
}
