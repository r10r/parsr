package de.entwicklerland.codeblockparser;

import java.io.OutputStream;

public class CodeBlockContentHandler extends ContentHandler {

	@Override
	public void process(Match match, OutputStream output) {
		
		System.out.println(match + ":" + match.getContent());

	}

}
