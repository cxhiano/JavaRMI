package test;

import rmi.core.RemoteException;


public class HelloImp implements Hello {
    public String myName;

    public HelloImp(String name) {
        myName = name;
    }

	@Override
    public String sayHello() {
    	return String.format("Hello! My name is %s\n", myName);
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
		return String.format("Hello %s ! My name is %s\n", hello.getName(), myName);
	}

    @Override
    public String getName() {
        return myName;
    }
}
