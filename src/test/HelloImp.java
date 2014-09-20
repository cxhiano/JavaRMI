package test;

public class HelloImp implements Hello {
    public void sayHello(int a, char c) {
        System.out.printf("Hello %d %c\n", a, c);
    }
}
