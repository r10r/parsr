package de.entwicklerland.codeblockparser;

public class Marker {
	private final String name;
	private final int startBuffer;
	private final int startPointer;
	private int endBuffer;
	private int endPointer;
	
	public Marker(String name, int startBuffer, int startPointer) {
		this(name, startBuffer, startPointer, 0, 0);
	}
	
	public Marker(String name, int startBuffer, int startPointer, int endBuffer, int endPointer) {
		this.name = name;
		this.startBuffer = startBuffer;
		this.startPointer = startPointer;
		this.endBuffer = endBuffer;
		this.endPointer = endPointer;
	}
	
	public void close(int endBuffer, int endPointer) {
		this.endBuffer = endBuffer;
		this.endPointer = endPointer;
	}
	
	public String getName() {
		return name;
	}
	
	public int getStartBuffer() {
		return startBuffer;
	}
	
	public int getStartPointer() {
		return startPointer;
	}
	
	public int getEndBuffer() {
		return endBuffer;
	}
	
	public int getEndPointer() {
		return endPointer;
	}
	
	@Override
	public String toString() {
		return String.format("%s startBuffer[%s], startPointer[%s], endBuffer[%s], endPointer[%s]", 
				name, startBuffer, startPointer, endBuffer, endPointer);
	}
}