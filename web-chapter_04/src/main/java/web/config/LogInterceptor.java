package web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liuxin
 * @version Id: Log.java, v 0.1 2019-03-06 11:36
 */
public class LogInterceptor implements HandlerInterceptor {
  private final static Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
  private final static ThreadLocal<Long> processor = new ThreadLocal<>();

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    //根据请求信息判断是否需要拦截
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    logger.info("请求信息打印...");
    processor.set(System.currentTimeMillis());
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    Long startTime = processor.get();
    Long endTime = System.currentTimeMillis();
    processor.remove();
    // 处理完请求，返回内容
    logger.info("-----------------方法执行完毕,耗时:{}ms-------------------", (endTime - startTime));
  }
}
