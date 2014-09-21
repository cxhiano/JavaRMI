package test;

public class SyncCounter implements Counter {

	private Integer count;

	public SyncCounter() {
		count = 0;
	}

	@Override
	synchronized public void reset() {
		count = 0;
	}

	@Override
	synchronized public void bump() {
		++count;
	}

	@Override
	synchronized public Integer getCount() {
		return count;
	}

}
