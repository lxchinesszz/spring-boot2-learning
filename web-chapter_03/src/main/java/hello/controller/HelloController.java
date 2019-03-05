package hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liuxin
 * @version Id: HelloController.java, v 0.1 2019-03-05 13:49
 */
@Controller
public class HelloController {

  /**
   * http://127.0.0.1:8080/hello
   *
   * @param name
   * @param model
   * @return
   */
  @GetMapping("/hello")
  public String hello(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
    model.addAttribute("name", name);
    return "index";
  }

  /**
   * http://127.0.0.1:8080/hello2?name=MV
   *
   * @param name
   * @param mv
   * @return
   */
  @GetMapping("/hello2")
  public ModelAndView hello2(@RequestParam(name = "name", required = false, defaultValue = "World") String name, ModelAndView mv) {
    mv.setViewName("index");
    mv.addObject("name", name);
    return mv;

  }

  @GetMapping("/hello3")
  public ModelAndView hello3(@RequestParam(name = "name", required = false, defaultValue = "World") String name) {
    ModelAndView mv = new ModelAndView("index");
    mv.addObject("name", name);
    return mv;
  }
}

