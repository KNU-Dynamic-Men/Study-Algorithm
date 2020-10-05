package elevator;

public class Call {
	private int id = 0;
	private int timestamp = 0;
	private int start = 0;
	private int end = 0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	public int getDirection() {
		if (this.end > this.start)
			return 1;
		else 
			return -1;
	}
	
}
