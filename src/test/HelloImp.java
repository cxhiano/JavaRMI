package test;

import rmi.core.RemoteException;


public class HelloImp implements Hello {
	@Override
    public String sayHello(Integer a, Character c) {
        return String.format("Hello %d %c", a, c);
    }

	@Override
    public String sayHello() {
    	return "Hello";
    }

	@Override
    public int sum(int a, int b) {
        return a + b;
    }

	@Override
    public Integer sum(Integer a, Integer b) {
        return a + b + 1000000;
    }

	@Override
	public String sayHello(Hello hello) throws RemoteException {
		return "Hello" + hello.sayHello();
	}
}
