package endpoint.service;

/**
 * @author liuxin
 * @version Id: Info.java, v 0.1 2019-03-08 18:29
 */
public class Info {
  String name;
  String age;

  public Info(String name, String age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }
}
