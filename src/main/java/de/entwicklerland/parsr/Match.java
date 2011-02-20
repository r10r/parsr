package de.entwicklerland.parsr;

public class Match {
	private final String event;
	private final int startPointer;

	private int endPointer;
	
	public Match(String name, StringBuilder buffer, int startPointer) {
		this(name, startPointer, -1);
	}
	
	public Match(String event, int startPointer, int endPointer) {
		this.event = event;
		this.startPointer = startPointer;
		this.endPointer = endPointer;
	}

	public void setEndPointer(int endPointer) {
		this.endPointer = endPointer;
	}
	
	public String getEvent() {
		return event;
	}
	
	public int getStartPointer() {
		return startPointer;
	}
	
	public int getEndPointer() {
		return endPointer;
	}
	
	public String getContent(StringBuilder buffer) {
		return buffer.substring(startPointer, endPointer);
	}
	
	@Override
	public String toString() {
		return String.format("%s startPointer[%s], endPointer[%s]", event, startPointer, endPointer);
	}
	
	public boolean isContentEmpty() {
		if (startPointer == endPointer) {
			return true;
		}
		return false;
	}
	
}