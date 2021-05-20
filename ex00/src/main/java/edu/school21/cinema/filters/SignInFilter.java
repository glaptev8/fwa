package edu.school21.cinema.filters;


  import javax.servlet.Filter;
  import javax.servlet.FilterChain;
  import javax.servlet.ServletException;
  import javax.servlet.ServletRequest;
  import javax.servlet.ServletResponse;
  import javax.servlet.annotation.WebFilter;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;
  import javax.servlet.http.HttpSession;
  import java.io.IOException;

@WebFilter("/*")
public class SignInFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpSession session = ((HttpServletRequest) servletRequest).getSession();
    String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
    if (requestURI.startsWith("/signIn") || requestURI.startsWith("/signUp")) {
      if (session.getAttribute("user") != null) {
        ((HttpServletResponse)servletResponse).sendRedirect("/home");
        return;
      }
    }
    else {
      if (session.getAttribute("user") == null) {
        ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN);
        return;
      }
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
