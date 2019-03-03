package app;

import app.entities.POJO;
import app.entities.POJO2;
import app.entities.POJO3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author liuxin
 * @version Id: Application.java, v 0.1 2018/7/5 上午10:47
 */
@SpringBootApplication
@ComponentScan(basePackages = {"app.entities"})
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);

        /**
         * 想这样的类如果要使用,只能依赖Spring注入,然后使用
         * 而如果要想脱离Spring使用那就是不可能的
         * 其主要特点是:
         *  ①: 代码简洁
         *  ②: 必须依赖Spring容器,否则不可使用
         *
         * 如何解决这种办法呢?
         *  其余以下两种都可以解决(开发者都可以通过set或者构造自己注入):
         *  ①: Set injection
         *  ②: Constructor injection
         */
        POJO pojo = applicationContext.getBean(POJO.class);
        pojo.execute("Field injection");

        /**
         *  Setter应该被用来注入可变的依赖。当没有提供依赖时，这个类也应该能够运转。
         *  当实例化对象后，这些依赖也能随时改变。
         *  其实也视情况而变,有时,一个不变的对象是理想状态.
         *  其主要特点是:
         *  ①: 能在运行期间改变对象的属性。
         *  ②: 不强制依赖容器,开发者可以自行调用
         */
        POJO2 pojo2 = applicationContext.getBean(POJO2.class);
        pojo2.execute("Set injection");

        /**
         * 构造器对注入强制性的的依赖是好的。对象需要这些依赖才能正常运转,
         * 通过构造器提供这些依赖就能保证对象初始化后就能被使用。
         * 其主要特点是:
         *  ①: 不强制依赖容器,开发者可以自行调用
         */
        POJO3 pojo3 = applicationContext.getBean(POJO3.class);
        pojo3.execute("Constructor injection");


        /**
         * XXX 总结:
         * Field注入应该尽可能地去避免使用。作为替代,你应该使用构造器注入或Setter注入。
         * 他们都有利有弊,需要视情况而定。当然你也可以在同一个类中使用这两种方法。
         * - 构造器注入更适合强制性的注入旨在不变性。
         * - Setter注入更适合可变性的注入。
         * - 当然如果你是一个有洁癖的人，使用Field注入也无可厚非.不过可能Idea会给你黄线警告,因为Spring Team不建议在拥有很多依赖的情况下
         * 这样做
         *
         */
    }
}
