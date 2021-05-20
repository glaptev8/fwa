package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import org.apache.commons.io.FileUtils;
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
import java.util.Base64;

@WebServlet(value = "/images/*", name = "ImageServlet")
public class ImageServlet extends HttpServlet {

  String pathToImages;

  @Override
  public void init(ServletConfig config) {
    ServletContext context = config.getServletContext();
    ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
    this.pathToImages = (String) springContext.getBean("pathToImages");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("user");
    byte[] fileContent = FileUtils.readFileToByteArray(new File(pathToImages + user.getId() + req.getPathInfo()));
    String encodedString = Base64.getEncoder().encodeToString(fileContent);
    req.setAttribute("image", encodedString);
    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/image.jsp");
    dispatcher.forward(req, resp);
  }
}
