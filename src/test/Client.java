package test;

public class Client {
    public static void main(String args[]) {
        Hello h = new HelloImp_stub();

        h.sayHello(10, 'c');
    }

}
