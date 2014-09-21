package test;

public class HelloImp implements Hello {
    public String sayHello(Integer a, Character c) {
        return String.format("Hello %d %c\n", a, c);
    }

    public void sayHello() {
        System.out.println("Hello");
    }
}
