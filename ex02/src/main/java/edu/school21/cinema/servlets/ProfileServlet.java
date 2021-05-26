package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.services.SignInService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(value = "/profile", name = "ProfileServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
  maxFileSize = 1024 * 1024 * 5,
  maxRequestSize = 1024 * 1024 * 5 * 5)
public class ProfileServlet extends HttpServlet {

  String imagesPath;

  @Override
  public void init(ServletConfig config) {
    ServletContext context = config.getServletContext();
    ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
    this.imagesPath = (String) springContext.getBean("pathToImages");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("user");
    File imagesDir = new File(imagesPath + user.getId());
    for (Part part : req.getParts()) {
      part.write(imagesDir + File.separator + part.getSubmittedFileName());
    }
    req.setAttribute("pathImages", imagesPath + user.getId());
    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
    dispatcher.forward(req, resp);

  }
}
