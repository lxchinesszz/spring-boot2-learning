package asyn.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author liuxin
 * @version Id: UserService.java, v 0.1 2019-03-11 14:59
 */
@Service
public class UserService {

  @Async
  public CompletableFuture<String> say1() throws Exception {
    Thread.sleep(1000);
    return CompletableFuture.completedFuture("say1");
  }

  @Async
  public CompletableFuture<String> say2() throws Exception {
    Thread.sleep(1000);
    return CompletableFuture.completedFuture("say2");
  }

  @Async
  public CompletableFuture<String> say3() throws Exception {
    Thread.sleep(1000);
    return CompletableFuture.completedFuture("say3");
  }
}

