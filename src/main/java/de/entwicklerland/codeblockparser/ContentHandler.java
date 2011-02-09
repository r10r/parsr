package de.entwicklerland.codeblockparser;

import java.io.IOException;
import java.io.OutputStream;


public abstract class ContentHandler {
	
	public abstract void process(Match match, OutputStream output) throws IOException;
	
}
