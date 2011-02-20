package de.entwicklerland.parsr.codeblock;

import java.io.IOException;
import java.io.OutputStream;

import de.entwicklerland.parsr.ContentHandler;
import de.entwicklerland.parsr.Match;

public class NOPContentHandler extends ContentHandler {

	@Override
	public void process(Match match, StringBuilder buffer, OutputStream output)
			throws IOException {
	}

}
