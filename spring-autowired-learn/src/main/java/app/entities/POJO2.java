package app.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuxin
 * @version Id: POJO.java, v 0.1 2018/7/5 上午10:46
 */
@Component
public class POJO2 {

    private DependencyA dependencyA;

    public void execute(String text){
        dependencyA.a(text);
    }

    @Autowired
    public void setDependencyA(DependencyA dependencyA) {
        this.dependencyA = dependencyA;
    }
}
