package task5_7;

public class Main {
  public static void main(String[] args) {
    Person from = new Person();
    from.setName("John");
    from.setAge(30);

    Person to = new Person();
    BeanUtils.assign(to, from);

    System.out.println("Name: " + to.getName()); // Name: John
    System.out.println("Age: " + to.getAge());   // Age: 30
  }
}
