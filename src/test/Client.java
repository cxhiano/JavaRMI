package test;

public class Client {
    public static void main(String args[]) {
        Hello h = new HelloImp_stub();
        System.out.println(h.sayHello(10, 'c'));
    }

}
