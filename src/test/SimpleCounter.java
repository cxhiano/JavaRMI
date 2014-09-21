package test;

public class SimpleCounter implements Counter {
	
	private Integer count;

	public SimpleCounter() {
		count = 0;
	}
	
	@Override
	public void reset() {
		count = 0;
	}

	@Override
	public void bump() {
		++count;
	}

	@Override
	public Integer getCount() {
		return count;
	}

}
