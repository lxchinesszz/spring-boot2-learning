package hello.controller;

import hello.entities.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 语法演示控制器
 *
 * @author liuxin
 * @version Id: SyntaxDemoController.java, v 0.1 2019-03-05 14:57
 */
@Controller
public class SyntaxDemoController {

  @GetMapping("/book")
  public String index(Model model) {
    Book book = new Book("颈椎病康复指南", "项目经理", new Date(), 23.95,"CG");
    model.addAttribute("book", book);
    model.addAttribute("title", "语法演示");
    model.addAttribute("books", loadBooks());
    model.addAttribute("myMap",loadMap());
    return "book";
  }

  public Map<String,String> loadMap(){
    Map<String,String>map=new ConcurrentHashMap<>();
    map.put("医生","C");
    map.put("教授","B");
    map.put("演员","C");
    return map;
  }


  public List<Book> loadBooks() {
    List<Book> books = new ArrayList<>();
    books.add(new Book("Java开发指南", "Jay", new Date(), 99.2,"PT"));
    books.add(new Book("Python从入门到放弃", "Lin", new Date(), 43.8,"CX"));
    books.add(new Book("NodeJs实战", "XD ", new Date(), 234.23,"*"));
    return books;
  }
}
