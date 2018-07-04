import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;
import tools.Console;
import java.util.Arrays;

/**
 * @author liuxin
 * @version Id: Application.java, v 0.1 2018/6/20 下午11:17
 */
@ComponentScan(value = {"/", "/rest"})
@EnableWebFlux
@SpringBootApplication
public class WebFluxApplication01 {
    public static void main(String[] args) {
        Arrays.stream(SpringApplication.run(WebFluxApplication01.class, args)
                .getBeanDefinitionNames()).forEach(x -> Console.log(x));
    }
}
