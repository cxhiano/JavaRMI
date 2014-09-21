package test;

public class AsyncCounter implements Counter {
	
	private Integer count;

	public AsyncCounter() {
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
