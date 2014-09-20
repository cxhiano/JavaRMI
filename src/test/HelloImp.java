package test;

public class HelloImp implements Hello {
    public void sayHello(Integer a, Character c) {
        System.out.printf("Hello %d %c\n", a, c);
    }
}
