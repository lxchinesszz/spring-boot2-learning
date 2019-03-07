package config.service;

import org.springframework.stereotype.Service;
import org.turnoff.processor.annotation.TurnoffCommand;

/**
 * @author liuxin
 * @version Id: DogService.java, v 0.1 2019-03-07 22:23
 */
@Service
public class DogService {
    @TurnoffCommand(fallbackMethod = "say2")
    public void say(String message){
//        System.out.println("Dog:"+message);
        throw new RuntimeException();
    }

    public void say2(String message){
        System.out.println("备用Dog:"+message);
    }
}
