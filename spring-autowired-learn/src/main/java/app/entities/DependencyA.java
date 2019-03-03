package app.entities;

import org.springframework.stereotype.Component;
import tools.Console;

/**
 * @author liuxin
 * @version Id: DependencyA.java, v 0.1 2018/7/5 上午10:46
 */
@Component
public class DependencyA {
    public void a(String text){
        Console.customerNormal(text,"成功");
    }
}
