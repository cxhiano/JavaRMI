package test;

import rmi.core.RemoteException;

public class HelloImpl implements Hello {
	public String myName;

	public HelloImpl(String name) {
		myName = name;
	}

	@Override
	public String getName() {
		return myName;
	}

	@Override
	public String sayHello() {
		return String.format("Hello! My name is %s", myName);
	}

	@Override
	public String sayHello(Hello hello) throws RemoteException {
		return String.format("Hello %s ! My name is %s", hello.getName(),
				myName);
	}
}