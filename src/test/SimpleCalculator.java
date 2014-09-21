package test;

public class SimpleCalculator implements Calculator {

	@Override
	public int add(int a, int b) {
		return a + b;
	}

	@Override
	public Integer add(Integer a, Integer b) {
		return a + b;
	}

	@Override
	public int minus(int a, int b) {
		return a - b;
	}

	@Override
	public Integer minus(Integer a, Integer b) {
		return a - b;
	}

	@Override
	public int multiply(int a, int b) {
		return a * b;
	}

	@Override
	public Integer multiply(Integer a, Integer b) {
		return a * b;
	}

	@Override
	public int divide(int a, int b) {
		return a / b;
	}

	@Override
	public Integer divide(Integer a, Integer b) {
		return a / b;
	}

}
