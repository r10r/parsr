package de.entwicklerland.parsr;

import java.io.IOException;
import java.io.OutputStream;

public abstract class ContentHandler {
	
	public abstract void process(Match match, StringBuilder buffer, OutputStream output) throws IOException;
	
}
