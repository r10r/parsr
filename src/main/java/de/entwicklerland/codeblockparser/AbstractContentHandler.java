package de.entwicklerland.codeblockparser;


public abstract class AbstractContentHandler {
	
	AbstractParser parser;
	
	/**
	 * The event notification should be executed after
	 * the next character has been added to the input.
	 * 
	 * @param event
	 */
	public abstract void processEvent(Marker marker);

	private final int bufferSize;
	
	protected int pointer;
	
	protected char[] input;

	public void setParser(AbstractParser parser) {
		this.parser = parser;
	}
	
	public AbstractContentHandler(int bufferSize) {
		this.bufferSize = bufferSize;
		this.input = new char[bufferSize];
	}
	
	public int getBufferSize() {
		return bufferSize;
	}
}
