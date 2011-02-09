package de.entwicklerland.codeblockparser;

public class Match {
	private final String event;
	private final StringBuilder buffer;
	private final int startPointer;

	private int endPointer;
	
	public Match(String name, StringBuilder buffer, int startPointer) {
		this(name, buffer, startPointer, -1);
	}
	
	public Match(String event, StringBuilder buffer, int startPointer, int endPointer) {
		this.event = event;
		this.buffer = buffer;
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
	
	public String getContent() {
		return buffer.substring(startPointer, endPointer);
	}
	
	@Override
	public String toString() {
		return String.format("%s startPointer[%s], endPointer[%s]", event, startPointer, endPointer);
	}
}