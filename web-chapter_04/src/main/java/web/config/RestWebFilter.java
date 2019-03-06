package web.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 通过Servlet注解的方式声明拦截器
 *
 * @author liuxin
 * @version Id: RestWebFilter.java, v 0.1 2019-03-06 10:46
 */
@WebFilter(urlPatterns = "/*", filterName = "RestWebFilter")
public class RestWebFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    long start = System.currentTimeMillis();
    filterChain.doFilter(servletRequest, servletResponse);
    System.out.println("Execute cost=" + (System.currentTimeMillis() - start));
  }
  @Override
  public void destroy() {
  }
}
