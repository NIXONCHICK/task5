package task5_3;

public class Person {

  private String name;
    private int age;

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  private String privateMethod() {
    return "This is a private method";
  }
}
