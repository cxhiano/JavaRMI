package test;


public class HelloImp implements Hello {
    public String sayHello(Integer a, Character c) {
        return String.format("Hello %d %c\n", a, c);
    }

    public void sayHello() {
        System.out.println("Hello");
    }

    public int sum(int a, int b) {
        return a + b;
    }

    public Integer sum(Integer a, Integer b) {
        return a + b;
    }
}
